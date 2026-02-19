package io.github.ethanlamtt.vietchrono.astro;

import io.github.ethanlamtt.vietchrono.scale.TimeScale;

import java.util.Objects;

import static io.github.ethanlamtt.vietchrono.scale.TimeScale.TT;
import static io.github.ethanlamtt.vietchrono.scale.TimeScale.UTC;

/**
 * Represents a Julian epoch defined by a reference {@link JulianDay} in specific {@link TimeScale}.
 * <p>This class is immutable and thread-safe</p>.
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class JulianEpoch {

    /**
     * Reference epoch at 1900-01-00 12:00:00 in TT.
     */
    public static final JulianEpoch J1900 = new JulianEpoch(JulianDay.of(2415021.0, TT));
    /**
     * Reference epoch at 2000-01-01 12:00:00 in TT.
     */
    public static final JulianEpoch J2000 = new JulianEpoch(JulianDay.of(2451545.0, TT));
    /**
     * Reference epoch at 1970-01-01 00:00:00 in UTC.
     */
    public static final JulianEpoch UNIX = new JulianEpoch(JulianDay.of(2440587.5, UTC));

    /**
     * A Julian day reference.
     */
    private final JulianDay julianDay;


    /**
     * Constructs a {@code JulianDay} with the specified reference {@code JulianDay}.
     *
     * <p>This constructor is private to prevent the initialization directly.</p>
     * @param julianDay the reference {@code JulianDay}.
     */
    private JulianEpoch(JulianDay julianDay) {
        this.julianDay = Objects.requireNonNull(julianDay, "julianDay must be not null");
    }

    /**
     * Returns a {@code JulianEpoch} with the specified reference {@code JulianDay}.
     *
     * @param julianDay
     * @return a {@code JulianEpoch} instance.
     */
    public static JulianEpoch of(JulianDay julianDay) {
        if (J1900.julianDay.equals(julianDay)) return J1900;
        if (J2000.julianDay.equals(julianDay)) return J2000;
        if (UNIX.julianDay.equals(julianDay)) return UNIX;

        return new JulianEpoch(julianDay);
    }

    /**
     * Returns the number of days elapsed since the Julian epoch.
     *
     * @return the number of days  elapsed since the Julian epoch.
     */
    public double value() {
        return julianDay.value();
    }

    /**
     * Returns a view of this epoch as an {@code JulianDay}.
     *
     * @return a {@code JulianDay} view. Not null
     */
    public JulianDay asJulianDay() {
        return julianDay;
    }

    /**
     * Compares this Julian Epoch with the specified object for equality.
     *
     * <p>Two {@code JulianDay} instances are considered equal if their
     * reference {@code JulianDay} are equal</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof JulianEpoch other))
            return false;

        return this.julianDay.equals(other.julianDay);
    }

    /**
     * Returns a hash code of this object. The hash code value is based on
     * the hash code value of its reference {@code JulianDay}.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        return julianDay.hashCode();
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("JulianEpoch[value=%f]", value());
    }
}
