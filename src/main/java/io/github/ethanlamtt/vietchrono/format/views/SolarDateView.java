package io.github.ethanlamtt.vietchrono.format.views;

import java.util.Objects;

/**
 * Represents for solar date view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class SolarDateView {

    private final int year;
    private final int month;
    private final int day;
    private final int dayOfWeekValue;
    private final String dayOfWeek;

    public SolarDateView(int year, int month, int day, int dayOfWeekValue, String dayOfWeek) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeekValue = dayOfWeekValue;
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "dayOfWeek cannot be null");
    }

    public int year() {
        return year;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int dayOfWeekValue() {
        return dayOfWeekValue;
    }

    public String dayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d (%s)", year, month, day, dayOfWeek);
    }
}
