package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.calendar.LunisolarDate;
import io.github.ethanlamtt.vietchrono.format.views.*;
import io.github.ethanlamtt.vietchrono.holiday.HolidayId;
import io.github.ethanlamtt.vietchrono.sexagenary.AuspiciousHour;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryCycle;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryDateTime;
import io.github.ethanlamtt.vietchrono.calendar.DailySolarTerm;
import io.github.ethanlamtt.vietchrono.calendar.LunarDate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.*;

/**
 * Provides a lunisolar date view format.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class LunisolarDateViewFormatter {

    private static final LunarDateDisplayNameProvider LUNAR_DATE_PROVIDER =
            LunarDateDisplayNameProvider.getInstance();
    private static final SolarTermDisplayNameProvider SOLAR_TERM_PROVIDER =
            SolarTermDisplayNameProvider.getInstance();
    private static final SexagenaryDisplayNameProvider SEXAGENARY_PROVIDER =
            SexagenaryDisplayNameProvider.getInstance();
    private static final HolidayDisplayNameProvider HOLIDAY_PROVIDER =
            HolidayDisplayNameProvider.getInstance();

    private LunisolarDateViewFormatter() {}

    public static LunisolarDateView format(LunisolarDate lunisolarDate, ZoneId zoneId, Locale locale) {
        Objects.requireNonNull(zoneId, "zoneId");

        SolarDateView solarDate = formatSolarDate(lunisolarDate.toSolarDate(), locale);
        LunarDateView lunarDate = formatLunarDate(lunisolarDate.toLunarDate(), locale);
        DailySolarTermView solarTermInfo = formatSolarTermInfo(lunisolarDate.solarTermInfo(), zoneId, locale);
        SexagenaryDateTimeView sexagenaryDate = formatSexagenaryDateTime(lunisolarDate.toSexagenaryDateTime(), locale);
        List<AuspiciousHourView> auspiciousHours =  formatAuspiciousHours(lunisolarDate.toSexagenaryDateTime().toSexagenaryDate().auspiciousHours(), locale);
        List<HolidayView> holidays = getHolidays(lunisolarDate.holidayIds(), locale);

        return new LunisolarDateView(solarDate, lunarDate, solarTermInfo, sexagenaryDate, auspiciousHours, holidays);
    }


    private static SolarDateView formatSolarDate(LocalDate solarDate, Locale locale) {
        int year = solarDate.getYear();
        int month = solarDate.getMonthValue();
        int day = solarDate.getDayOfMonth();
        int dayOfWeekValue = solarDate.getDayOfWeek().getValue();

        String dayOfWeek = solarDate.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, locale);

        return new SolarDateView(year, month, day, dayOfWeekValue, dayOfWeek);
    }

    private static LunarDateView formatLunarDate(LunarDate lunarDate, Locale locale) {
        String lunarMonth = LUNAR_DATE_PROVIDER.getMonthName(lunarDate, locale);
        String lunarDay = LUNAR_DATE_PROVIDER.getDayName(lunarDate, locale);

        return new LunarDateView(lunarDate, lunarMonth, lunarDay);
    }

    private static DailySolarTermView formatSolarTermInfo(DailySolarTerm dailySolarTerm, ZoneId zoneId, Locale locale) {
        String termOfDay = SOLAR_TERM_PROVIDER.getName(dailySolarTerm.termOfDay(), locale);
        ZonedDateTime transition = dailySolarTerm.transition()
                .map(m -> m.toInstant().atZone(zoneId))
                .orElse(null);

        return new DailySolarTermView(termOfDay, transition);
    }

    private static SexagenaryDateTimeView formatSexagenaryDateTime(SexagenaryDateTime sexagenaryDateTime, Locale locale) {
        String year = getSexagenaryCycle(sexagenaryDateTime.year(), locale);
        String month = getSexagenaryCycle(sexagenaryDateTime.month(), locale);
        String day = getSexagenaryCycle(sexagenaryDateTime.day(), locale);
        String hour = getSexagenaryCycle(sexagenaryDateTime.hour(), locale);

        return new SexagenaryDateTimeView(year, month, day, hour);
    }

    private static String getSexagenaryCycle(SexagenaryCycle sexagenary, Locale locale) {
        String stem = SEXAGENARY_PROVIDER.getStemName(sexagenary.stem(), locale);
        String branch = SEXAGENARY_PROVIDER.getBranchName(sexagenary.branch(), locale);

        return stem + " " + branch;
    }

    private static List<AuspiciousHourView> formatAuspiciousHours(List<AuspiciousHour> hours, Locale locale) {
        List<AuspiciousHourView> auspiciousHours = new ArrayList<>();

        for (AuspiciousHour hour : hours) {
            String branchName = SEXAGENARY_PROVIDER.getBranchName(hour.doubleHour().branch(), locale);
            String starTime = hour.doubleHour().startTime().toString();
            String endTime = hour.doubleHour().endTime().toString();

            auspiciousHours.add(new AuspiciousHourView(branchName, starTime, endTime));
        }

        return auspiciousHours;
    }

    private static List<HolidayView> getHolidays(List<HolidayId> ids, Locale locale) {
        List<HolidayView> holidays = new ArrayList<>();

        for (HolidayId id : ids) {
            String holidayName = HOLIDAY_PROVIDER.getName(id.id(), locale);
            holidays.add(HolidayView.of(holidayName, id.type()));
        }

        return holidays;
    }

}
