package io.github.ethanlamtt.vietchrono.format.views;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents for a lunisolar date view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class LunisolarDateView {

    private final SolarDateView solarDate;
    private final LunarDateView lunarDate;
    private final DailySolarTermView solarTermInfo;
    private final SexagenaryDateTimeView sexagenaryDateTime;
    private final List<AuspiciousHourView> auspiciousHours;
    private final List<HolidayView> holidays;

    public LunisolarDateView(SolarDateView solarDate, LunarDateView lunarDate,
                             DailySolarTermView solarTermInfo,SexagenaryDateTimeView sexagenaryDateTime,
                             List<AuspiciousHourView> auspiciousHours, List<HolidayView> holidays) {
        this.solarDate = Objects.requireNonNull(solarDate, "solarDate");
        this.lunarDate = Objects.requireNonNull(lunarDate, "lunarDate");
        this.solarTermInfo = Objects.requireNonNull(solarTermInfo, "solarTermInfo");
        this.sexagenaryDateTime = Objects.requireNonNull(sexagenaryDateTime, "sexagenaryDateTime");
        this.auspiciousHours = Objects.requireNonNull(auspiciousHours, "auspiciousHours");
        this.holidays = Objects.requireNonNull(holidays, "holidays");
    }

    public SolarDateView solarDate() {
        return solarDate;
    }

    public LunarDateView lunarDate() {
        return lunarDate;
    }

    public DailySolarTermView solarTermInfo() {
        return solarTermInfo;
    }

    public SexagenaryDateTimeView sexagenaryDateTime() {
        return sexagenaryDateTime;
    }

    public List<AuspiciousHourView> auspiciousHours() {
        return auspiciousHours;
    }

    public List<HolidayView> holidays() {
        return holidays;
    }

    @Override
    public String toString() {

        String holidayListStr = holidays.isEmpty()
                ? ""
                : " | ★ " + holidays.stream()
                .map(HolidayView::name)
                .collect(Collectors.joining(", "));

        String auspiciousHoursListStr = auspiciousHours.isEmpty()
                ? ""
                : " | ♥ " + auspiciousHours.stream()
                .map(AuspiciousHourView::toString)
                .collect(Collectors.joining(", "));

        return String.format(
                "%s | Âm: %s | %s | %s%s| %s",
                solarDate,
                lunarDate,
                sexagenaryDateTime,
                solarTermInfo,
                auspiciousHoursListStr,
                holidayListStr
        );
    }
}
