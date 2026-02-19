package io.github.ethanlamtt.vietchrono.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LunarDateTest {

    @Test
    void testEquals() {
        LunarDate a = LunarDate.of(2026, 1, 1);
        LunarDate b = LunarDate.of(2026, 1, 1);
        LunarDate c = LunarDate.of(2026, 1, 1);
        // Reflexive
        assertEquals(a, a);
        // Symmetric
        assertEquals(a, b);
        assertEquals(b, a);
        // Transitive
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
        // Non-nullable
        assertNotEquals(null, a);
        // Different type
        assertNotEquals("0", a);

        LunarDate diffYear = LunarDate.of(2025, 1, 1);
        LunarDate diffMonth = LunarDate.of(2026, 2, 1);
        LunarDate diffLeapMonth = LunarDate.of(2026, LunarMonth.of(2, false), 1);
        LunarDate diffDay = LunarDate.of(2026, 1, 2);
        assertNotEquals(diffYear, a);
        assertNotEquals(diffMonth, a);
        assertNotEquals(diffLeapMonth, a);
        assertNotEquals(diffDay, a);
    }

    @Test
    void testHashCode() {
        LunarDate a = LunarDate.of(2026, 1, 1);
        LunarDate b = LunarDate.of(2026, 1, 1);
        assertEquals(b.hashCode(), a.hashCode());

        LunarDate diffYear = LunarDate.of(2025, 1, 1);
        LunarDate diffMonth = LunarDate.of(2026, 2, 1);
        LunarDate diffLeapMonth = LunarDate.of(2026, LunarMonth.of(1, true), 1);
        LunarDate diffDay = LunarDate.of(2026, 1, 2);
        assertNotEquals(diffYear.hashCode(), a.hashCode());
        assertNotEquals(diffMonth.hashCode(), a.hashCode());
        assertNotEquals(diffLeapMonth.hashCode(), a.hashCode());
        assertNotEquals(diffDay.hashCode(), a.hashCode());
    }

    @Test
    void testCompareTo() {
        LunarDate a = LunarDate.of(2026, 1, 1);
        LunarDate diffYear = LunarDate.of(2025, 1, 1);
        LunarDate diffMonth = LunarDate.of(2026, 2, 1);
        LunarDate diffLeapMonth = LunarDate.of(2026, LunarMonth.of(1, true), 1);
        LunarDate diffDay = LunarDate.of(2026, 1, 2);

        assertEquals(1, a.compareTo(diffYear));
        assertEquals(-1, a.compareTo(diffMonth));
        assertEquals(-1, a.compareTo(diffLeapMonth));
        assertEquals(-1, a.compareTo(diffDay));
    }

    @Test
    void testOf() {
        LunarDate a = LunarDate.of(2026, 1, 1);
        assertEquals(2026, a.year());
        assertEquals(1, a.monthValue());
        assertFalse(a.month().isLeapMonth());
        assertEquals(1, a.dayOfMonth());

        LunarDate b = LunarDate.of(2026, LunarMonth.of(1, true), 1);
        assertEquals(2026, b.year());
        assertEquals(1, b.monthValue());
        assertTrue(b.month().isLeapMonth());
        assertEquals(1, b.dayOfMonth());

        assertThrows(NullPointerException.class, () -> LunarDate.of(2026, null, 1));
        assertThrows(IllegalArgumentException.class, () -> LunarDate.of(2026, 1, 0));
        assertThrows(IllegalArgumentException.class, () -> LunarDate.of(2026, 1, 32));
    }
}
