package io.github.ethanlamtt.vietchrono.astro;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static io.github.ethanlamtt.vietchrono.astro.AstroUtils.normalizeAngle;

/**
 * Provides solar astronomical calculations implementation using
 * the high-precision algorithms of VSOP87 data.
 *
 * <p>The reference link:
 * <a href="https://github.com/ctdk/vsop87">
 *     VSOP87
 * </a>
 * </p>
 * <p>This class uses
 *  <a href="https://github.com/ctdk/vsop87/blob/master/VSOP87D.ear">
 *      VSOP87D
 *  </a>
 *  as default
 * </p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
final class Vsop87SolarCalculator implements SolarCalculator {

    /**
     * The singleton instance of this class.
     */
    private static final Vsop87SolarCalculator INSTANCE = new Vsop87SolarCalculator();

    /**
     * Constructs a {@code Vsop87SolarCalculator}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     */
    private Vsop87SolarCalculator() {}

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code SolarCalculator} instance.
     */
    public static SolarCalculator getInstance() {
        return INSTANCE;
    }

    /**
     * This class loads VSOP87 files to get periodic terms of the Earth.
     */
    private static final class Loader {

        /**
         * Periodic terms of the Earth.
         *
         * <p>The first dimension array holds the series index from L0 to L5.
         * The second dimension array holds terms in the series.
         * The third dimension array holds coefficients of each term (A, B, C).
         * </p>
         */
        static final double[][][] EARTH_TERMS = new double[6][][];

        static {
            String fileName = "data/VSOP87D_Earth.txt";

            @SuppressWarnings("unchecked")
            List<double[]>[] tempLists = new ArrayList[6];

            for (int i = 0; i < 6; i++) {
                tempLists[i] = new ArrayList<>();
            }

            try (InputStream is = Loader.class.getClassLoader().getResourceAsStream(fileName)){
                if (is == null)
                    throw new IllegalStateException("Resource not found: " + fileName);

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8),
                        6 * 1024
                )) {

                    int index = -1;
                    String line;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty())
                            continue;

                        if (line.contains("*T**")) {
                            index++;
                            continue;
                        }

                        if (index < 0 || index >= 6) {
                            continue;
                        }

                        String[] parts = line.split("\\s+");
                        int len = parts.length;
                        double A = Double.parseDouble(parts[len - 3]);
                        double B = Double.parseDouble(parts[len - 2]);
                        double C = Double.parseDouble(parts[len - 1]);

                        tempLists[index].add(new double[] {
                                A, B, C
                        });
                    }

                    for (int i = 0; i < 6; i++) {
                        EARTH_TERMS[i] = tempLists[i].toArray(new double[tempLists[i].size()][]);
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public double apparentLongitude(double jde) {
        jde = jde - JulianEpoch.J2000.value();

        double t = jde / 365250;
        double[][][] series = Loader.EARTH_TERMS;

        double l = 0.0;

        for (int i = 0; i < series.length; i++) {
            double sumTerms = 0.0;
            for (double[] row : series[i]) {
                double A = row[0];
                double B = row[1];
                double C = row[2];

                sumTerms += A * Math.cos(B + C * t);
            }

            if (i == 0) {
                l += sumTerms;
            } else {
                l += sumTerms * Math.pow(t, i);
            }
        }

        // Heliocentric to Geometric geocentric longitude
        l = Math.toDegrees(l) + 180;

        double T = jde / 36525;

        double omega = 125.04 - 1934.136 * T;
        double lambda = l - 0.00569 - 0.00478 * Math.sin(Math.toRadians(omega));

        return normalizeAngle(lambda);
    }
}
