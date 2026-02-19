package io.github.ethanlamtt.vietchrono.astro;

/**
 * Static utilities for astronomical and calendrical calculations.
 *
 * <p>This class provides common mathematical functions used in astronomical and calendrical calculations.</p>
 * <p>This class cannot be instantiated.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class AstroUtils {

    /**
     * Prevents object creation.
     */
    private AstroUtils() {
        throw new AssertionError();
    }

    /**
     * Keep an angle within the range of 0 to 360 degrees.
     *
     * <p>If the angle is 360 or more, it wraps around. If it's negative, it adds 360.</p>
     *
     * @param value the angle in degrees.
     * @return the normalized angle.
     */
    public static double normalizeAngle(double value) {

        while (Double.compare(value, 360) >= 0)
            value -= 360;

        while (Double.compare(value, 0.0) < 0)
            value += 360;

        return value;
    }
}
