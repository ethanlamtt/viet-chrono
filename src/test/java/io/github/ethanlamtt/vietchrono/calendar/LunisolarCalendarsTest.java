package io.github.ethanlamtt.vietchrono.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LunisolarCalendarsTest {
    @Test
    void testOfDefault() {
        LunisolarCalendar a = LunisolarCalendars.ofDefault();
        LunisolarCalendar b = LunisolarCalendars.ofDefault();
        assertSame(b, a);
    }

    @Test
    void testOf() {
        LunisolarCalendar a = LunisolarCalendars.of("Default");
        LunisolarCalendar b = LunisolarCalendars.of("Default");
        assertSame(b, a);

        assertThrows(IllegalArgumentException.class, () -> LunisolarCalendars.of("Default1"));
    }

    @Test
    void testAvailableCalendarIds() {
        assertNotNull(LunisolarCalendars.availableCalendarIds());
        assertFalse(LunisolarCalendars.availableCalendarIds().isEmpty());
    }
}
