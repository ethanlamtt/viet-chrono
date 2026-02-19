package io.github.ethanlamtt.vietchrono.holiday;

import io.github.ethanlamtt.vietchrono.calendar.DailySolarTerm;
import io.github.ethanlamtt.vietchrono.calendar.LunarDate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents for a holiday rule context.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class HolidayRuleContext {

    private final LocalDate solarDate;
    private final LunarDate lunarDate;
    private final DailySolarTerm dailySolarTerm;

    private HolidayRuleContext(LocalDate solarDate, LunarDate lunarDate, DailySolarTerm dailySolarTerm) {
        this.solarDate = Objects.requireNonNull(solarDate, "solarDate cannot be null");
        this.lunarDate = Objects.requireNonNull(lunarDate, "lunarDate cannot be null");
        this.dailySolarTerm = Objects.requireNonNull(dailySolarTerm, "dailySolarTerm cannot be null");
    }

    public static HolidayRuleContext of(LocalDate solarDate, LunarDate lunarDate, DailySolarTerm dailySolarTerm) {
        return new HolidayRuleContext(solarDate, lunarDate, dailySolarTerm);
    }

    public LocalDate solarDate() {
        return solarDate;
    }

    public LunarDate lunarDate() {
        return lunarDate;
    }

    public int solarDay() {
        return solarDate.getDayOfMonth();
    }

    public int solarMonth() {
        return solarDate.getMonthValue();
    }

    public int lunarMonth() {
        return lunarDate.monthValue();
    }

    public int lunarDay() {
        return lunarDate.dayOfMonth();
    }

    public DailySolarTerm solarTermInfo() {
        return dailySolarTerm;
    }
}