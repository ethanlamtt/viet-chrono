package io.github.ethanlamtt.vietchrono.sexagenary;

import java.util.*;

/**
 * Represents for a sexagenary date.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class SexagenaryDate {

    /**
     * The cyclic year component.
     */
    private final SexagenaryCycle year;

    /**
     * The cyclic month component.
     */
    private final SexagenaryCycle month;

    /**
     * The cyclic day component.
     */
    private final SexagenaryCycle day;

    /**
     * Constructs a {@code SexagenaryDate} with a specified cyclic year, month, day.
     *
     * @param year the cyclic year component
     * @param month the cyclic month component
     * @param day the cyclic day component
     * @throws NullPointerException if {@code year} is  null
     * @throws NullPointerException if {@code month} is  null
     * @throws NullPointerException if {@code day} is  null
     */
    private SexagenaryDate(SexagenaryCycle year, SexagenaryCycle month, SexagenaryCycle day) {
        this.year = Objects.requireNonNull(year, "year");
        this.month = Objects.requireNonNull(month, "month");
        this.day = Objects.requireNonNull(day, "day");
    }

    /**
     * Returns an {@code SexagenaryDate} with a specified cyclic year, month, day.
     *
     * @param year the cyclic year component
     * @param month the cyclic month component
     * @param day the cyclic day component
     * @return an {@code SexagenaryDate} instance
     */
    public static SexagenaryDate of(SexagenaryCycle year, SexagenaryCycle month, SexagenaryCycle day) {
        return new SexagenaryDate(year, month, day);
    }

    /**
     * Obtains the cyclic year of this date
     * @return the cyclic year of this date
     */
    public SexagenaryCycle year() {
        return year;
    }

    /**
     * Obtains the cyclic month of this date
     * @return the cyclic month of this date
     */
    public SexagenaryCycle month() {
        return month;
    }

    /**
     * Obtains the cyclic day of this date
     * @return the cyclic day of this date
     */
    public SexagenaryCycle day() {
        return day;
    }


    /**
     * Returns a list of auspicious hours of this cyclic day.
     *
     * @return auspiciousHours a list of auspicious hours of this cyclic day.
     */
    public List<AuspiciousHour> auspiciousHours() {
        return AuspiciousHour.from(day.branch());
    }

    /**
     * Compares this instance with the specified object for equality.
     *
     * <p>Two {@code SexagenaryDate} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SexagenaryDate other))
            return false;

        return year.equals(other.year) && month.equals(other.month)
                && day.equals(other.day);
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + year.hashCode();
        result = 31 * result + month.hashCode();
        result = 31 * result + day.hashCode();
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("SexagenaryDate(year=%s, month=%s, day=%s)", year, month, day);
    }
}
