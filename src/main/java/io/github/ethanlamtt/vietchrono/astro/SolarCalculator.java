package io.github.ethanlamtt.vietchrono.astro;

/**
 * Provides solar astronomical calculations.
 *
 * @author ethanlamtt
 * @since 1.0.0
 * @see Vsop87SolarCalculator
 * @see MeeusSolarCalculator
 */
interface SolarCalculator {

    /**
     * Returns apparent geocentric ecliptic longitude of the Sun.
     *
     * @param jde the Julian Ephemeris Day (TT) representing the moment of observation.
     * @return apparent geocentric ecliptic longitude of the Sun which is normalized to
     * range [0, 360] degrees.
     */
    double apparentLongitude(double jde);
}
