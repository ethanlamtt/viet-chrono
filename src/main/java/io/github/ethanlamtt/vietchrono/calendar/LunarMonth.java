package io.github.ethanlamtt.vietchrono.calendar;

import java.util.Comparator;

/**
 * Represents for a lunar month.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public class LunarMonth implements Comparable<LunarMonth> {

    private static final Comparator<LunarMonth> NATURAL_ORDER = Comparator
            .comparingInt(LunarMonth::value)
            .thenComparing(LunarMonth::isLeapMonth);

    /**
     * The month value.
     */
    private final int month;

    /**
     * The current month is leap or not.
     */
    private final boolean isLeapMonth;

    /**
     * Constructs a {@code LunarMonth} with a specified month value and a leap month flag.
     *
     * @param month the month value
     * @param isLeapMonth the leap month flag
     * @throws IllegalArgumentException if {@code dayOfMonth} is out of range [1, 13).
     */
    private LunarMonth(int month, boolean isLeapMonth) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid month month");
        this.month = month;
        this.isLeapMonth = isLeapMonth;
    }

    /**
     * Returns a {@code LunarMonth} with a specified month value and a leap month flag.
     *
     * @param month the month value
     * @param isLeapMonth the leap month flag
     * @return a {@code LunarMonth} instance.
     */
    public static LunarMonth of(int month, boolean isLeapMonth) {
        return new LunarMonth(month, isLeapMonth);
    }

    /**
     * Obtains the month value.
     *
     * @return the month value
     */
    public int value() {
        return month;
    }

    /**
     * Obtains the leap month flag.
     *
     * @return the leap month flag
     */
    public boolean isLeapMonth() {
        return isLeapMonth;
    }

    /**
     * Compares this instance with the specified object for equality.
     *
     * <p>Two {@code LunarMonth} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof LunarMonth other))
            return false;

        return month == other.month && isLeapMonth == other.isLeapMonth;
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + month;
        result = 31 * result + (isLeapMonth ? 1 : 0);
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("LunarMonth(month=%d, isLeapMonth=%b)", month, isLeapMonth);
    }

    /**
     * Compares two {@code LunarMonth} instances.
     *
     * @param other the other {@code LunarMonth} to compare
     * @return the value {@code 0} if {@code x == y}; a value less
     *         than {@code 0} if {@code x < y} as unsigned values; and
     *         a value greater than {@code 0} if {@code x > y} as
     *         unsigned values
     */
    @Override
    public int compareTo(LunarMonth other) {
        return NATURAL_ORDER.compare(this, other);
    }
}
