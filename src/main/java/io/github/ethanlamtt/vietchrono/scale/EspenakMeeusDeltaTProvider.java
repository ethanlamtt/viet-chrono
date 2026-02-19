package io.github.ethanlamtt.vietchrono.scale;

/**
 * Provides deltaT algorithm provider implementation using
 * the high-precision algorithms.
 *
 * <p>The reference link:
     * <a href="https://eclipse.gsfc.nasa.gov/SEcat5/deltatpoly.html">
 *     Five Millennium Canon of Solar Eclipses [Espenak and Meeus]
     * </a>
 * </p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
final class EspenakMeeusDeltaTProvider implements DeltaTProvider {

    /**
     * The singleton instance of this class.
     */
    private static final DeltaTProvider INSTANCE = new EspenakMeeusDeltaTProvider();

    /**
     * Constructs a {@code EspenakMeeusDeltaTProvider}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     */
    private EspenakMeeusDeltaTProvider() {}

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code DeltaTProvider} instance.
     */
    public static DeltaTProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public double estimate(double year) {
        double y = year;

        if (y < -500) {
            double u = (y - 1820) / 100;
            return -20 + 32 * Math.pow(u, 2);
        }

        if (y < 500) {
            double u = y / 100;
            return 10583.6 - 1014.41 * u + 33.78311 * Math.pow(u, 2) - 5.952053 * Math.pow(u, 3)
                    - 0.1798452 * Math.pow(u, 4) + 0.022174192 * Math.pow(u, 5)
                    + 0.0090316521 * Math.pow(u, 6);
        }

        if (y < 1600) {
            double u = (y - 1000) / 100;
            return 1574.2 - 556.01 * u + 71.23472 * Math.pow(u, 2) + 0.319781 * Math.pow(u, 3)
                    - 0.8503463 * Math.pow(u, 4) - 0.005050998 * Math.pow(u, 5)
                    + 0.0083572073 * Math.pow(u, 6);
        }

        if (y < 1700) {
            double t = y - 1600;
            return 120 - 0.9808 * t - 0.01532 * Math.pow(t, 2) + Math.pow(t, 3) / 7129;
        }

        if (y < 1800) {
            double t = y - 1700;
            return 8.83 + 0.1603 * t - 0.0059285 * Math.pow(t, 2) + 0.00013336 * Math.pow(t, 3)
                    - Math.pow(t, 4) / 1174000;
        }

        if (y < 1860) {
            double t = y - 1800;
            return 13.72 - 0.332447 * t + 0.0068612 * Math.pow(t, 2) + 0.0041116 * Math.pow(t, 3)
                    - 0.00037436 * Math.pow(t, 4) + 0.0000121272 * Math.pow(t, 5)
                    - 0.0000001699 * Math.pow(t, 6) + 0.000000000875 * Math.pow(t, 7);
        }

        if (y < 1900) {
            double t = y - 1860;
            return 7.62 + 0.5737 * t - 0.251754 * Math.pow(t, 2) + 0.01680668 * Math.pow(t, 3)
                    -0.0004473624 * Math.pow(t, 4) + Math.pow(t, 5) / 233174;
        }

        if (y < 1920) {
            double t = y - 1900;
            return -2.79 + 1.494119 * t - 0.0598939 * Math.pow(t, 2) + 0.0061966 * Math.pow(t, 3)
                    - 0.000197 * Math.pow(t, 4);
        }

        if (y < 1941) {
            double t = y - 1920;
            return 21.20 + 0.84493*t - 0.076100 * Math.pow(t, 2) + 0.0020936 * Math.pow(t, 3);
        }

        if (y < 1961) {
            double t = y - 1950;
            return 29.07 + 0.407*t - Math.pow(t, 2)/233 + Math.pow(t, 3) / 2547;
        }

        if (y < 1986) {
            double t = y - 1975;
            return 45.45 + 1.067*t - Math.pow(t, 2)/260 - Math.pow(t, 3) / 718;
        }

        if (y < 2005) {
            double t = y - 2000;
            return 63.86 + 0.3345 * t - 0.060374 * Math.pow(t, 2) + 0.0017275 * Math.pow(t, 3)
                    + 0.000651814 * Math.pow(t, 4) + 0.00002373599 * Math.pow(t, 5);
        }

        if (y < 2050) {
            double t = y - 2000;
            return 62.92 + 0.32217 * t + 0.005589 * Math.pow(t, 2);
        }

        if (y < 2150) {
            return -20 + 32 * Math.pow((y-1820) / 100, 2) - 0.5628 * (2150 - y);
        }

        double u = (y - 1820) / 100;
        return -20 + 32 * Math.pow(u, 2);
    }
}
