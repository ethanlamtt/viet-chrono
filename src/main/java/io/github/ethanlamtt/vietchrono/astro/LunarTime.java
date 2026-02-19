package io.github.ethanlamtt.vietchrono.astro;

import java.time.Instant;

import static io.github.ethanlamtt.vietchrono.astro.LunarPhase.MEAN_SYNODIC_MONTH;

/**
 * Provides Lunar position calculations.
 *
 * <p>This class is instance-controlled, immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class LunarTime {

    /**
     * The singleton instance of this class.
     */
    private static final LunarTime INSTANCE = new LunarTime();

    /**
     * Constructs a {@code LunarTime}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     */
    private LunarTime() {}

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code LunarTime} instance.
     */
    public static LunarTime getInstance() {
        return INSTANCE;
    }

    /**
     * Mean new moon near J2000 epoch, occurred on January 6, 2000.
     */
    private static final double MEAN_NEW_MOON_BASE = 2451550.09765;

    /**
     * Returns the moment of the specified lunar phase occurring before the given anchor moment.
     *
     * @param anchorMoment the anchor moment which the phase is searched.
     * @param phase the lunar phase to compute.
     * @return a {@code Moment} of the specified phase preceding the anchor moment.
     */
    public Moment before(Moment anchorMoment, LunarPhase phase) {
        double anchor = anchorMoment.toEphemeris()
                .value();

        long n = (long) Math.ceil((anchor - MEAN_NEW_MOON_BASE) / MEAN_SYNODIC_MONTH);

        double jde = phase.atLunation(n);

        while (jde >= anchor) {
            n--;
            jde = phase.atLunation(n);
        }

        return JulianDay.ofEphemeris(jde)
                .toMoment();
    }

    /**
     * Returns the moment of the specified lunar phase occurring after the given anchor moment.
     *
     * @param anchorMoment the anchor moment which the phase is searched.
     * @param phase the lunar phase to compute.
     * @return a {@code Moment} of the specified phase after the anchor moment.
     */
    public Moment after(Moment anchorMoment, LunarPhase phase) {
        double anchor = anchorMoment.toEphemeris()
                .value();

        long n = (long) Math.floor((anchor - MEAN_NEW_MOON_BASE) / MEAN_SYNODIC_MONTH);

        double jde = phase.atLunation(n);

        while (jde <= anchor) {
            n++;
            jde = phase.atLunation(n);
        }

        return JulianDay.ofEphemeris(jde)
                .toMoment();
    }

}
