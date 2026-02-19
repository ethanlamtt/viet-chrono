package io.github.ethanlamtt.vietchrono.astro;

import io.github.ethanlamtt.vietchrono.scale.TimeScale;

import java.util.Objects;

import static io.github.ethanlamtt.vietchrono.scale.TimeScale.TT;
import static io.github.ethanlamtt.vietchrono.scale.TimeScale.UTC;

/**
 * Represents for continuous count of days since noon UT on January 1, 4713 BC.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class JulianDay {

    /**
     * Number of days since JD epoch.
     * <p>The integer part represents the count of full days, and the fractional part
     * represents the time elapsed since the preceding noon.</p>
     */
    private final double value;
    /**
     * Timescale used for this JulianDay.
     * @see TimeScale
     */
    private final TimeScale timeScale;

    /**
     * Constructs a {@code JulianDay} with the specified value and timescale.
     *
     * <p>This constructor is private to prevent the initialization directly.</p>
     * @param value the days from epoch.
     * @param timeScale the timescale which Julian Day based on.
     * @throws NullPointerException if {@code timescale} is null.
     */
    private JulianDay(double value, TimeScale timeScale) {
        this.value = value;
        this.timeScale = Objects.requireNonNull(timeScale, "timeScale");
    }

    /**
     * Returns a JulianDay with the specified value and timeScale.
     * @param value the days from epoch.
     * @param timeScale the timeScale which Julian Day based on.
     * @return a {@code JulianDay} instance.
     */
    public static JulianDay of(double value, TimeScale timeScale) {
        return new JulianDay(value, timeScale);
    }

    /**
     * Returns a {@code JulianDay} with the specified value in the Terrestrial Time scale (TT).
     * @param value the days from epoch.
     * @return a {@code JulianDay} instance in scale TT
     */
    public static JulianDay ofEphemeris(double value) {
        return of(value, TT);
    }

    /**
     * Returns a {@code JulianDay} with the specified value in the Universal Time scale (UT).
     * @param value the days from epoch.
     * @return a {@code JulianDay} instance in scale UT
     */
    public static JulianDay ofUniversal(double value) {
        return of(value, UTC);
    }

    /**
     * Returns the number of days of this Julian Day count since epoch.
     * @return the number of days of this Julian Day
     */
    public double value() {
        return value;
    }

    /**
     * Returns the time scale which this JulianDay based on.
     * @return the time scale of this Julian Day.
     */
    public TimeScale timescale() {
        return timeScale;
    }

    /**
     * Returns a {@code JulianDay} with the specified number of days,
     * keeping the same time scale as this instance.
     *
     * @param value the specified number of days.
     * @return the {@code JulianDay} instance with the new value.
     */
    public JulianDay withValue(double value) {
        return of(value, timeScale);
    }

    /**
     * Returns a {@code JulianDay} with the specified time scale,
     * keeping the same number of days as this instance.
     *
     * @param timeScale the specified time scale.
     * @return the {@code JulianDay} instance with the new time scale.
     */
    public JulianDay withTimeScale(TimeScale timeScale) {
        return of(value, timeScale);
    }

    /**
     * Returns a {@code JulianDay} with the specified number of days added,
     * keeping the same time scale as this instance.
     *
     * @param daysToPlus the number of days to add.
     * @return the {@code JulianDay} instance with the days added.
     */
    public JulianDay plusDays(double daysToPlus) {
        return of(this.value + daysToPlus, timeScale);
    }

    /**
     * Returns a {@code JulianDay} with the specified number of days subtracted,
     * keeping the same time scale as this instance.
     *
     * @param daysToMinus the number of days to subtract.
     * @return the {@code JulianDay} instance with the days subtracted.
     */
    public JulianDay minusDays(double daysToMinus) {
        return of(this.value - daysToMinus, timeScale);
    }

    /**
     * Converts this instance to a {@code Moment} representation.
     * <p>This equivalent to {@code Moment.ofJulianDay(this)}</p>
     *
     * @return a {@code Moment} instance representing this Julian Day.
     * @see Moment#ofJulianDay(JulianDay)
     */
    public Moment toMoment() {
        return Moment.ofJulianDay(this);
    }

    /**
     * Compares this Julian Day with the specified object for equality.
     *
     * <p>Two {@code JulianDay} instances are considered equal if they have
     * the exact same value and are in the same time scale.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof JulianDay other))
            return false;

        return Double.compare(this.value, other.value) == 0 && timeScale == other.timeScale;
    }

    /**
     * Returns a hash code of this object. The hash code value is based on the hash code
     * of number of days and the time scale of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(value);
        result = 31 * result + timeScale.hashCode();
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("JulianDay(days=%f, timeScale=%s)", value, timeScale);
    }
}
