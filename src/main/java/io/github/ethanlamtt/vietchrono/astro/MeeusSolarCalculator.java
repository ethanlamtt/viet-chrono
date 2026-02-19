package io.github.ethanlamtt.vietchrono.astro;

import static io.github.ethanlamtt.vietchrono.astro.AstroUtils.normalizeAngle;

/**
 * Provides solar astronomical calculations implementation using
 * the low-precision algorithms of Meeus.
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
final class MeeusSolarCalculator implements SolarCalculator {

    /**
     * The singleton instance of this class.
     */
    private static final MeeusSolarCalculator INSTANCE = new MeeusSolarCalculator();

    /**
     * Constructs a {@code MeeusSolarCalculator}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     */
    private MeeusSolarCalculator() {}

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code SolarCalculator} instance.
     */
    public static SolarCalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public double apparentLongitude(double jde) {
        jde = jde - JulianEpoch.J2000.value();

        double t = jde / 36525;
        double l0 = 280.46646 + 36000.76983*t + 0.0003032*Math.pow(t, 2);
        double m = 357.52911 + 35999.05029*t - 0.0001537*Math.pow(t, 2);

        double mRad = Math.toRadians(normalizeAngle(m));

        double C = (1.914602 - 0.004817 * t - 0.000014 * t * t) * Math.sin(mRad)
                + (0.019993 - 0.000101 * t) * Math.sin(2 * mRad)
                + 0.000289 * Math.sin(3 * mRad);

        double l = l0 + C;

        double omega = 125.04 - 1934.136 * t;
        double lambda = l - 0.00569 - 0.00478 * Math.sin(Math.toRadians(omega));

        return normalizeAngle(lambda);
    };
}
