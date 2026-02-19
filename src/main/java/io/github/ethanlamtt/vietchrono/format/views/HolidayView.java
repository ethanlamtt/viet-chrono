package io.github.ethanlamtt.vietchrono.format.views;

import io.github.ethanlamtt.vietchrono.holiday.HolidayType;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents for a holiday view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class HolidayView implements Comparable<HolidayView> {

    private final String name;
    private final HolidayType holidayType;

    private HolidayView(String name, HolidayType holidayType) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.holidayType = Objects.requireNonNull(holidayType, "calendarType cannot be null");
    }

    public static HolidayView of(String name, HolidayType holidayType) {
        return new HolidayView(name, holidayType);
    }

    public String name() {
        return name;
    }

    public HolidayType holidayType() {
        return holidayType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof HolidayView other))
            return false;

        return holidayType.equals(other.holidayType) && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + holidayType.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Holiday(name=%s, calendarType=%s)", name, holidayType);
    }

    @Override
    public int compareTo(HolidayView other) {
        return Comparator.comparing(HolidayView::holidayType)
                .thenComparing(HolidayView::name)
                .compare(this, other);
    }
}
