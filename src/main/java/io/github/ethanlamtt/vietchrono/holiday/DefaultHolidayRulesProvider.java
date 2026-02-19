package io.github.ethanlamtt.vietchrono.holiday;

import io.github.ethanlamtt.vietchrono.calendar.DailySolarTerm;
import io.github.ethanlamtt.vietchrono.calendar.SolarTerm;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;

/**
 * Provides holiday rules provider implementation.
 *
 * @author ethanlamtt
 * @since 1.0
 */
final class DefaultHolidayRulesProvider implements HolidayRulesProvider {

    private static final HolidayRulesProvider INSTANCE = new DefaultHolidayRulesProvider();

    private static final List<HolidayRule> RULES = List.of(
            HolidayRule.fixedSolar("New_Years_Day", 1, 1),
            HolidayRule.fixedSolar("April_Fools_Day", 4, 1),
            HolidayRule.fixedSolar("Valentines_Day", 2, 14),
            HolidayRule.fixedSolar("International_Womens_Day", 3, 8),
            HolidayRule.fixedSolar("Liberation_Day", 4, 30),
            HolidayRule.fixedSolar("International_Workers_Day", 5, 1),
            HolidayRule.fixedSolar("President_Ho_Chi_Minh_Birthday", 5, 19),
            HolidayRule.fixedSolar("Vietnam_National_Day", 9, 2),
            HolidayRule.fixedSolar("Vietnamese_Womens_Day", 10, 20),
            HolidayRule.fixedSolar("Halloween", 10, 31),
            HolidayRule.fixedSolar("International_Mens_Day", 11, 19),
            HolidayRule.fixedSolar("World_Toilet_Day", 11, 19),
            HolidayRule.fixedSolar("Vietnamese_Teachers_Day", 11, 20),
            HolidayRule.fixedSolar("Christmas", 12, 25),
            HolidayRule.fixedSolar("New_Years_Eve", 12, 31),
            HolidayRule.fixedLunar("Lunar_New_Year", 1, 1),
            HolidayRule.fixedLunar("Lantern_Festival", 1, 15),
            HolidayRule.fixedLunar("Hung_King_Festival", 3, 10),
            HolidayRule.fixedLunar("Vesak_Day", 4, 15),
            HolidayRule.fixedLunar("Mid_Year_Festival", 5, 5),
            HolidayRule.fixedLunar("Vu_Lan_Festival", 7, 15),
            HolidayRule.fixedLunar("Mid_Autumn", 8, 15),
            HolidayRule.fixedLunar("Kitchen_Gods_Day", 12, 23),
            HolidayRule.of(
                    HolidayId.of("Programmers_Day", HolidayType.SOLAR),
                    context -> context.solarDate().getDayOfYear() == 256
            ),
            HolidayRule.of(
                    HolidayId.of("Black_Friday", HolidayType.SOLAR),
                    context -> {
                        LocalDate solarDate = context.solarDate();
                        if (solarDate.getMonthValue() != 11)
                            return false;

                        LocalDate thanksgiving = LocalDate
                                .of(solarDate.getYear(), 11, 1)
                                .with(dayOfWeekInMonth(4, DayOfWeek.THURSDAY));

                        return solarDate.equals(thanksgiving.plusDays(1));
                    }
            ),
            HolidayRule.of(
                    HolidayId.of("Friday_The_13th", HolidayType.SOLAR),
                    context -> {
                        LocalDate date = context.solarDate();
                        return date.getDayOfMonth() == 13
                                && date.getDayOfWeek() == DayOfWeek.FRIDAY;
                    }
            ),
            HolidayRule.of(
                    HolidayId.of("Tomb_Sweeping_Day", HolidayType.SOLAR),
                    context -> {
                        DailySolarTerm info = context.solarTermInfo();
                        return info.termOfDay() == SolarTerm.PURE_BRIGHTNESS
                                && info.transition().isPresent();
                    }
            )
    );


    private DefaultHolidayRulesProvider(){}

    public static HolidayRulesProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public List<HolidayRule> getRules() {
        return RULES;
    }
}
