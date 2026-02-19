package io.github.ethanlamtt.vietchrono.holiday;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import io.github.ethanlamtt.vietchrono.calendar.DailySolarTerm;
import io.github.ethanlamtt.vietchrono.calendar.LunarDate;
import io.github.ethanlamtt.vietchrono.calendar.SolarTerm;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HolidayRulesTest {
    @Test
    void testGetInstance() {
        HolidayRules a = HolidayRules.getInstance();
        HolidayRules b = HolidayRules.getInstance();
        assertSame(b, a);
    }

    @Test
    void testApplyRules() {
        HolidayRules a = HolidayRules.getInstance();
        LocalDate localDate = LocalDate.of(1970, 1, 1);
        LunarDate lunarDate = LunarDate.of(1970, 8, 15);
        DailySolarTerm dailySolarTerm = DailySolarTerm.of(SolarTerm.WINTER_SOLSTICE, Moment.of(0));
        HolidayRuleContext context = HolidayRuleContext.of(localDate, lunarDate, dailySolarTerm);

        List<HolidayId> ids = a.applyRules(context);
        assertNotNull(ids);
        assertEquals(2, ids.size());

        assertThrows(NullPointerException.class, () -> a.applyRules(null));
    }
}
