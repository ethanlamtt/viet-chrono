package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryCycle;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem.*;
import static org.junit.jupiter.api.Assertions.*;

class DefaultLunisolarCalendarTest {

    @Test
    void testGetInstance() {
        LunisolarCalendar calendar = DefaultLunisolarCalendar.getInstance();
        assertNotNull(calendar);
    }

    @Test
    void testId() {
        LunisolarCalendar calendar = DefaultLunisolarCalendar.getInstance();
        assertNotNull(calendar.id());
    }

    @Test
    void testGetDate() {
        LunisolarCalendar calendar = DefaultLunisolarCalendar.getInstance();

        LunisolarDate date = calendar.getDate(
                LocalDate.of(2026, 5, 5),
                ZoneId.of("Asia/Ho_Chi_Minh")
        );

        assertNotNull(date);
        assertEquals(LocalDate.of(2026, 5, 5), date.toSolarDate());
        assertEquals(LunarDate.of(2026, 3, 19), date.toLunarDate());
        assertDoesNotThrow(() -> date.solarTermInfo().transition().get());

        long expectedTransition = 1777981680;
        long actualTransition = date.solarTermInfo().transition().get().value();
        assertTrue(Math.abs(expectedTransition - actualTransition) <= 60);
        assertEquals(
                START_OF_SUMMER,
                date.solarTermInfo().termOfDay()
        );
        assertEquals(
                SexagenaryDateTime.of(
                        SexagenaryCycle.of(YANG_FIRE, HORSE),
                        SexagenaryCycle.of(YANG_WATER, DRAGON),
                        SexagenaryCycle.of(YIN_EARTH, CAT),
                        SexagenaryCycle.of(YANG_WOOD, RAT)
                ),
                date.toSexagenaryDateTime()
        );
    }
}
