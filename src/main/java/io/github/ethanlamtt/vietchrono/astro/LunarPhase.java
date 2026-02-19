package io.github.ethanlamtt.vietchrono.astro;

/**
 * Represents for the lunar phases.
 *
 * <p>Each phase has angle fraction value representing its position within the lunar cycle (0.0–1.0)</p>
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public enum LunarPhase {
    /**
     * Represents for a new moon phase.
     */
    NEW_MOON(0.0) {
        @Override
        double periodicCorrection(double m, double m1, double f, double omega) {
            return -0.40720 * Math.sin(m1) + 0.17241 * Math.sin(m) + 0.01608 * Math.sin(2 * m1)
                    + 0.01039 * Math.sin(2 * f) + 0.00739 * Math.sin(m1 - m)
                    - 0.00514 * Math.sin(m1 + m) + 0.00208 * Math.sin(2 * m)
                    - 0.00111 * Math.sin(m1 - 2 * f) - 0.00057 * Math.sin(m1 + 2 * f)
                    + 0.00056 * Math.sin(2 * m1 + m) - 0.00042 * Math.sin(3 * m1)
                    + 0.00042 * Math.sin(m + 2 * f) + 0.00038 * Math.sin(m - 2 * f)
                    - 0.00024 * Math.sin(2 * m1 - m) - 0.00017 * Math.sin(omega)
                    - 0.00007 * Math.sin(m1 + 2 * m) + 0.00004 * Math.sin(2 * m1 - 2 * f)
                    + 0.00004 * Math.sin(3 * m) + 0.00003 * Math.sin(m1 + m - 2 * f)
                    + 0.00003 * Math.sin(2 * m1 + 2 * f) - 0.00003 * Math.sin(m1 + m + 2 * f)
                    + 0.00003 * Math.sin(m1 - m + 2 * f) - 0.00002 * Math.sin(m1 - m - 2 * f)
                    - 0.00002 * Math.sin(3 * m1 + m) + 0.00002 * Math.sin(4 * m1);
        }
    };

    /**
     * Mean synodic month of the Moon, in days.
     */
    public static final double MEAN_SYNODIC_MONTH = 29.530588853;

    /**
     * The angle fraction of the phase, represents for its position.
     */
    private final double angleFraction;

    /**
     * Constructs a {@code LunarPhase} with the specified angle fraction.
     *
     * <p>This constructor is private to prevent the initialization directly.</p>
     * @param angleFraction the angle fraction.
     */
    LunarPhase(double angleFraction) {
        this.angleFraction = angleFraction;
    }

    /**
     * Obtains the angle fraction of this phase in lunar cycle.
     *
     * @return the angle fraction of this phase.
     */
    public double angleFraction() {
        return angleFraction;
    }

    /**
     * Returns the Julian Ephemeris Day of this phase for the given lunation number.
     * @param n number of lunations elapsed since epoch.
     * @return the Julian Ephemeris Day of this phase.
     */
    double atLunation(long n) {
        double k = n + angleFraction();
        double t = k / (36525 / MEAN_SYNODIC_MONTH);

        double meanTime = 2_451_550.09766 + 29.530588853 * k
                + 0.000_133_7 * Math.pow(t, 2) - 0.000_000_150 * Math.pow(t, 3)
                + 0.000_000_000_73 * Math.pow(t, 4);
        double m = 2.5534 + 29.10535669 * k - 0.0000218 * Math.pow(t, 2)
                - 0.00000011 * Math.pow(t, 3);
        double m1 = 201.5643 + 385.81693528 * k + 0.0107438 * Math.pow(t, 2)
                + 0.00001239 * Math.pow(t, 3) - 0.000000058 * Math.pow(t, 4);
        double f = 160.7108 + 390.67050274 * k - 0.0016341 * Math.pow(t, 2)
                - 0.00000227 * Math.pow(t, 3) + 0.000000011 * Math.pow(t, 4);
        double omega = 124.7746 - 1.56375588 * k + 0.0020672 * Math.pow(t, 2)
                + 0.00000215 * Math.pow(t, 3);
        double mRad = Math.toRadians(m);
        double m1Rad = Math.toRadians(m1);
        double fRad = Math.toRadians(f);
        double omegaRad = Math.toRadians(omega);
        
        double periodicValue = periodicCorrection(mRad, m1Rad, fRad, omegaRad);
        double planetaryValue =
                0.000325 * Math.sin(Math.toRadians(299.77 + 0.107408 * k - 0.009173 * t * t))
                        + 0.000165 * Math.sin(Math.toRadians(251.88 + 0.016321 * k))
                        + 0.000164 * Math.sin(Math.toRadians(251.83 + 0.016322 * k))
                        + 0.000126 * Math.sin(Math.toRadians(349.42 + 0.009173 * k))
                        + 0.000110 * Math.sin(Math.toRadians(84.66 + 0.019302 * k))
                        + 0.000062 * Math.sin(Math.toRadians(141.74 + 0.005614 * k))
                        + 0.000060 * Math.sin(Math.toRadians(207.14 + 0.017201 * k))
                        + 0.000056 * Math.sin(Math.toRadians(154.84 + 0.002783 * k))
                        + 0.000047 * Math.sin(Math.toRadians(34.52 + 0.002429 * k))
                        + 0.000042 * Math.sin(Math.toRadians(207.19 + 0.017203 * k))
                        + 0.000040 * Math.sin(Math.toRadians(291.34 + 0.016400 * k))
                        + 0.000037 * Math.sin(Math.toRadians(161.72 + 0.001666 * k))
                        + 0.000035 * Math.sin(Math.toRadians(239.56 + 0.001439 * k))
                        + 0.000023 * Math.sin(Math.toRadians(331.55 + 0.000400 * k));

        double correction = periodicValue + planetaryValue;

        return meanTime + correction;
    }

    /**
     * Returns the periodic correction for this lunar phase.
     *
     * @param m mean anomaly of the Sun in radians
     * @param m1 mean anomaly of the Moon in radians
     * @param f Moon's argument of latitude in radians
     * @param omega longitude of the ascending node of the Moon's orbit in radians
     * @return periodic correction in days to be added to the mean phase time
     */
    abstract double periodicCorrection(double m, double m1, double f, double omega);
}
