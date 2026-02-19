package io.github.ethanlamtt.vietchrono.astro;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Provides solar position calculations.
 *
 * <p>This class is instance-controlled, immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class SolarTime {

    /**
     * The singleton instance of this class.
     */
    private static final SolarTime INSTANCE;

    static {
        ServiceLoader<SolarCalculator> loader = ServiceLoader.load(SolarCalculator.class);
        SolarCalculator calculator = loader.findFirst()
                .orElse(Vsop87SolarCalculator.getInstance());

        INSTANCE = new SolarTime(calculator);
    }

    /**
     * The solar position algorithm calculator.
     */
    private final SolarCalculator calculator;

    /**
     * Constructs a {@code SolarTime}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     *
     * @param calculator the solar position calculation.
     * @throws NullPointerException if {@code calculator} is null
     */
    private SolarTime(SolarCalculator calculator) {
        this.calculator = Objects.requireNonNull(calculator);
    }

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code SolarTime} instance
     */
    public static SolarTime getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the apparent geocentric ecliptic longitude at a specified moment of observation.
     *
     * @param moment the moment of observation
     * @return the apparent geocentric ecliptic longitude
     */
    public double apparentLongitudeAt(Moment moment) {
        double jde = moment.toEphemeris()
                .value();
        return calculator.apparentLongitude(jde);
    }

    /**
     * Returns the moment at which the apparent ecliptic longitude reaches
     * the given value near the specified anchor moment.
     *
     * @param longitude target longitude in degrees
     * @param anchor the initial guess moment
     * @return moment at which reaches target longitude
     */
    public Moment atLongitude(double longitude, Moment anchor) {
        double epsilon = 1e-5;
        double sunMeanVelocity = 0.98564736;
        double jde = anchor.toEphemeris()
                .value();

        for (int i = 0; i < 10; i++) {
            double residual = calculator.apparentLongitude(jde) - longitude;

            residual = residual % 360;

            if (residual > 180)
                residual -= 360;

            if (residual < -180)
                residual += 360;

            if (Math.abs(residual) < epsilon)
                break;

            double c = residual / sunMeanVelocity;
            jde -= c;
        }

        return JulianDay.ofEphemeris(jde)
                .toMoment();
    }
}
