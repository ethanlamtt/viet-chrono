package io.github.ethanlamtt.vietchrono.calendar;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents for a lunar date.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class LunarDate implements Comparable<LunarDate> {

    private static final Comparator<LunarDate> NATURAL_ORDER = Comparator
            .comparingInt(LunarDate::year)
            .thenComparing(LunarDate::month)
            .thenComparingInt(LunarDate::dayOfMonth);

    /**
     * The lunar year.
     */
    private final int year;

    /**
     * The lunar month.
     */
    private final LunarMonth month;

    /**
     * The lunar day.
     */
    private final int dayOfMonth;

    /**
     * Constructs a {@code LunarDate} with the specified lunar year, lunar month and lunar day.
     *
     * @param year the lunar year
     * @param month the lunar month
     * @param dayOfMonth the lunar day
     * @throws NullPointerException if {@code month} is null.
     * @throws IllegalArgumentException if {@code dayOfMonth} is out of range [1, 31).
     */
    private LunarDate(int year, LunarMonth month, int dayOfMonth) {
        Objects.requireNonNull(month, "month");
        if (dayOfMonth < 1 || dayOfMonth > 30)
            throw new IllegalArgumentException("dayOfMonth must start from 1 to 30");
        this.month = month;
        this.year = year;
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * Returns a LunarDate with the specified lunar year, lunar month and lunar day.
     *
     * @param year the lunar year
     * @param month the lunar month
     * @param dayOfMonth the lunar day
     * @return a {@code LunarDate} instance.
     */
    public static LunarDate of(int year, int month, int dayOfMonth) {
        return new LunarDate(year, LunarMonth.of(month, false), dayOfMonth);
    }

    /**
     * Returns a LunarDate with the specified lunar year, lunar month and lunar day.
     *
     * @param year the lunar year
     * @param month the lunar month
     * @param dayOfMonth the lunar day
     * @return a {@code LunarDate} instance.
     */
    public static LunarDate of(int year, LunarMonth month, int dayOfMonth) {
        return new LunarDate(year, month, dayOfMonth);
    }

    /**
     * Obtains the lunar year.
     * @return the lunar year
     */
    public int year() {
        return year;
    }

    /**
     * Obtains the lunar month object.
     * @return the lunar month object
     */
    public LunarMonth month() {
        return month;
    }

    /**
     * Obtains the lunar day.
     * @return the lunar day
     */
    public int dayOfMonth() {
        return dayOfMonth;
    }

    /**
     * Obtains the lunar month.
     * @return the lunar month
     */
    public int monthValue() {
        return month.value();
    }

    /**
     * Compares this instance with the specified object for equality.
     *
     * <p>Two {@code LunarDate} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof LunarDate other))
            return false;

        return year == other.year && month.equals(other.month) && dayOfMonth == other.dayOfMonth;
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + year;
        result = 31 * result + month.hashCode();
        result = 31 * result + dayOfMonth;
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("LunarDate(year=%d, month=%d, day=%d, isLeapYear=%b)",
                year, month.value(), dayOfMonth, month.isLeapMonth());
    }

    /**
     * Compares two {@code LunarDate} instances.
     *
     * @param other the other {@code LunarDate} to compare
     * @return the value {@code 0} if {@code x == y}; a value less
     *         than {@code 0} if {@code x < y} as unsigned values; and
     *         a value greater than {@code 0} if {@code x > y} as
     *         unsigned values
     */
    @Override
    public int compareTo(LunarDate other) {
        return NATURAL_ORDER.compare(this, other);
    }
}
