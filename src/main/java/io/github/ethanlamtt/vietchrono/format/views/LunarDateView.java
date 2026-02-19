package io.github.ethanlamtt.vietchrono.format.views;

import io.github.ethanlamtt.vietchrono.calendar.LunarDate;

import java.util.Objects;

/**
 * Represents for a lunar date view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class LunarDateView {

    private final int lunarYearValue;
    private final int lunarMonthValue;
    private final int lunarDayValue;
    private final boolean isLeapMonth;
    private final String lunarMonth;
    private final String lunarDay;

    public LunarDateView(LunarDate lunarDate, String lunarMonth, String lunarDay) {
        Objects.requireNonNull(lunarDate, "lunarDate");
        Objects.requireNonNull(lunarMonth, "lunarMonth");
        Objects.requireNonNull(lunarDay, "lunarDay");

        this.lunarYearValue = lunarDate.year();
        this.lunarMonthValue = lunarDate.monthValue();
        this.lunarDayValue = lunarDate.dayOfMonth();
        this.isLeapMonth = lunarDate.month().isLeapMonth();
        this.lunarMonth = lunarMonth;
        this.lunarDay = lunarDay;
    }

    public int lunarYearValue() {
        return lunarYearValue;
    }

    public int lunarMonthValue() {
        return lunarMonthValue;
    }

    public int lunarDayValue() {
        return lunarDayValue;
    }

    public boolean isLeapMonth() {
        return isLeapMonth;
    }

    public String lunarMonth() {
        return lunarMonth;
    }

    public String lunarDay() {
        return lunarDay;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d (%s %s)", lunarYearValue, lunarMonthValue, lunarDayValue, lunarDay, lunarMonth);
    }
}
