package io.github.ethanlamtt.vietchrono.format.views;

import java.util.Objects;

/**
 * Represents for a sexagenary date time view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class SexagenaryDateTimeView {

    private final String year;
    private final String month;
    private final String day;
    private final String hour;

    public SexagenaryDateTimeView(String year, String month, String day, String hour) {
        this.year = Objects.requireNonNull(year, "year");
        this.month = Objects.requireNonNull(month, "month");
        this.day = Objects.requireNonNull(day, "day");
        this.hour = Objects.requireNonNull(hour, "hour");
    }

    public String year() {
        return year;
    }

    public String month() {
        return month;
    }

    public String day() {
        return day;
    }

    public String hour() {
        return hour;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", year, month, day, hour);
    }
}
