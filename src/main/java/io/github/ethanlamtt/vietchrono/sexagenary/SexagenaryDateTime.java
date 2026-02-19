package io.github.ethanlamtt.vietchrono.sexagenary;

import java.util.Objects;

/**
 * Represents for a sexagenary date time.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class SexagenaryDateTime {

    /**
     * The sexagenary date component.
     */
    private final SexagenaryDate date;

    /**
     * The cyclic hour component.
     */
    private final SexagenaryCycle hour;

    /**
     * Constructs a {@code SexagenaryDateTime} with a specified sexagenary date and a cyclic hour.
     *
     * @param date the sexagenary date component
     * @param hour the cyclic hour component
     * @throws NullPointerException if {@code date} is  null
     * @throws NullPointerException if {@code hour} is  null
     */
    private SexagenaryDateTime(SexagenaryDate date, SexagenaryCycle hour) {
        this.date = Objects.requireNonNull(date, "date");
        this.hour = Objects.requireNonNull(hour, "hour");
    }

    /**
     * Returns an {@code SexagenaryDateTime} with a specified sexagenary date and a cyclic hour.
     *
     * @param date the sexagenary date component
     * @param hour the cyclic hour component
     * @return an {@code SexagenaryDateTime} instance
     */
    public static SexagenaryDateTime of(SexagenaryDate date, SexagenaryCycle hour) {
        return new SexagenaryDateTime(date, hour);
    }

    /**
     * Returns an {@code SexagenaryDateTime} with a specified cyclic year, month, day, hour.
     *
     * @param year the cyclic year component
     * @param month the cyclic month component
     * @param day the cyclic day component
     * @param hour the cyclic hour component
     * @return an {@code SexagenaryDateTime} instance
     */
    public static SexagenaryDateTime of(SexagenaryCycle year, SexagenaryCycle month,
                                        SexagenaryCycle day , SexagenaryCycle hour) {
        return new SexagenaryDateTime(SexagenaryDate.of(year, month, day), hour);
    }

    /**
     * Obtains the cyclic year of this date
     * @return the cyclic year of this date
     */
    public SexagenaryCycle year() {
        return date.year();
    }

    /**
     * Obtains the cyclic month of this date
     * @return the cyclic month of this date
     */
    public SexagenaryCycle month() {
        return date.month();
    }

    /**
     * Obtains the cyclic day of this date
     * @return the cyclic day of this date
     */
    public SexagenaryCycle day() {
        return date.day();
    }

    /**
     * Obtains the cyclic hour of this date
     * @return the cyclic hour of this date
     */
    public SexagenaryCycle hour() {
        return hour;
    }

    /**
     * Converts this instance to {@code SexagenaryDay}.
     *
     * @return a {@code SexagenaryDate} instance
     */
    public SexagenaryDate toSexagenaryDate() {
        return date;
    }

    /**
     * Compares this instance with the specified object for equality.
     *
     * <p>Two {@code SexagenaryDateTime} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SexagenaryDateTime other))
            return false;

        return date.equals(other.date) && hour.equals(other.hour);
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + date.hashCode();
        result = 31 * result + hour.hashCode();
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("SexagenaryDateTime(date=%s, hour=%s)", date, hour);
    }
}
