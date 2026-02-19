package io.github.ethanlamtt.vietchrono.holiday;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import io.github.ethanlamtt.vietchrono.calendar.DailySolarTerm;
import io.github.ethanlamtt.vietchrono.calendar.LunarDate;
import io.github.ethanlamtt.vietchrono.calendar.SolarTerm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HolidayRuleContextTest {

    @Test
    void testOf() {
        LocalDate localDate = LocalDate.of(1970, 1, 1);
        LunarDate lunarDate = LunarDate.of(1970, 8, 15);
        DailySolarTerm dailySolarTerm = DailySolarTerm.of(SolarTerm.WINTER_SOLSTICE, Moment.of(0));
        HolidayRuleContext a = HolidayRuleContext.of(localDate, lunarDate, dailySolarTerm);

        assertEquals(1, a.solarDay());
        assertEquals(1, a.solarMonth());
        assertEquals(15, a.lunarDay());
        assertEquals(8, a.lunarMonth());
        assertEquals(SolarTerm.WINTER_SOLSTICE, a.solarTermInfo().termOfDay());
        assertEquals(Moment.of(0), a.solarTermInfo().transition().get());

        Executable nullLocalDate = () -> HolidayRuleContext.of(null, lunarDate, dailySolarTerm);
        Executable nullLunarDate = () -> HolidayRuleContext.of(localDate, null, dailySolarTerm);
        Executable nullSolarTerm = () -> HolidayRuleContext.of(localDate, lunarDate, null);
        assertThrows(NullPointerException.class, nullLocalDate);
        assertThrows(NullPointerException.class, nullLunarDate);
        assertThrows(NullPointerException.class, nullSolarTerm);
    }
}
