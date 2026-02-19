package io.github.ethanlamtt.vietchrono.holiday;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import io.github.ethanlamtt.vietchrono.calendar.DailySolarTerm;
import io.github.ethanlamtt.vietchrono.calendar.LunarDate;
import io.github.ethanlamtt.vietchrono.calendar.SolarTerm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayRuleTest {
    @Test
    void testOf() {
        HolidayRule a = HolidayRule.of(
                HolidayId.of("New_Years_Day", HolidayType.SOLAR),
                context -> context.solarMonth() == 1 && context.solarDay() == 1
        );
        assertEquals(HolidayId.of("New_Years_Day", HolidayType.SOLAR), a.id());
        assertEquals("New_Years_Day", a.id().id());

        LocalDate localDate = LocalDate.of(1970, 1, 1);
        LunarDate lunarDate = LunarDate.of(1970, 1, 1);
        DailySolarTerm dailySolarTerm = DailySolarTerm.of(SolarTerm.WINTER_SOLSTICE, Moment.of(0));
        assertTrue(a.matches(HolidayRuleContext.of(localDate, lunarDate, dailySolarTerm)));

        Executable nullId = () -> HolidayRule.of(
                null,
                context -> context.solarMonth() == 1 && context.solarDay() == 1
        );
        Executable nullType = () -> HolidayRule.of(
                HolidayId.of("New_Years_Day", HolidayType.SOLAR),
                null
        );
        assertThrows(NullPointerException.class, nullId);
        assertThrows(NullPointerException.class, nullType);
    }

    @Test
    void testFixedSolar() {
        HolidayRule a = HolidayRule.fixedSolar("New_Years_Day", 1, 1);
        assertEquals(HolidayId.of("New_Years_Day", HolidayType.SOLAR), a.id());
        assertEquals("New_Years_Day", a.id().id());

        LocalDate localDate = LocalDate.of(1970, 1, 1);
        LunarDate lunarDate = LunarDate.of(1970, 1, 1);
        DailySolarTerm dailySolarTerm = DailySolarTerm.of(SolarTerm.WINTER_SOLSTICE, Moment.of(0));
        assertTrue(a.matches(HolidayRuleContext.of(localDate, lunarDate, dailySolarTerm)));

        Executable nullId = () -> HolidayRule.of(
                null,
                context -> context.solarMonth() == 1 && context.solarDay() == 1
        );
        Executable nullType = () -> HolidayRule.of(
                HolidayId.of("New_Years_Day", HolidayType.SOLAR),
                null
        );
        assertThrows(NullPointerException.class, nullId);
        assertThrows(NullPointerException.class, nullType);
    }

    @Test
    void testFixedLunar() {
        HolidayRule a = HolidayRule.fixedLunar("Mid_Autumn", 8, 15);
        assertEquals(HolidayId.of("Mid_Autumn", HolidayType.LUNAR), a.id());
        assertEquals("Mid_Autumn", a.id().id());

        LocalDate localDate = LocalDate.of(1970, 1, 1);
        LunarDate lunarDate = LunarDate.of(1970, 8, 15);
        DailySolarTerm dailySolarTerm = DailySolarTerm.of(SolarTerm.WINTER_SOLSTICE, Moment.of(0));
        assertTrue(a.matches(HolidayRuleContext.of(localDate, lunarDate, dailySolarTerm)));

        Executable nullId = () -> HolidayRule.of(
                null,
                context -> context.lunarMonth() == 8 && context.lunarDay() == 15
        );
        Executable nullType = () -> HolidayRule.of(
                HolidayId.of("Mid_Autumn", HolidayType.LUNAR),
                null
        );
        assertThrows(NullPointerException.class, nullId);
        assertThrows(NullPointerException.class, nullType);
    }
}
