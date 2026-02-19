package io.github.ethanlamtt.vietchrono.calendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LunarMonthTest {
    @Test
    void testEquals() {
        LunarMonth a = LunarMonth.of(1, false);
        LunarMonth b = LunarMonth.of(1, false);
        LunarMonth c = LunarMonth.of(1, false);
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

        LunarMonth diffMonth = LunarMonth.of(2, false);
        LunarMonth diffLeapMonth = LunarMonth.of(1, true);
        assertNotEquals(diffMonth, a);
        assertNotEquals(diffLeapMonth, a);
    }

    @Test
    void testHashCode() {
        LunarMonth a = LunarMonth.of(1, false);
        LunarMonth b = LunarMonth.of(1, false);
        assertEquals(b.hashCode(), a.hashCode());

        LunarMonth diffMonth = LunarMonth.of(2, false);
        LunarMonth diffLeapMonth = LunarMonth.of(1, true);
        assertNotEquals(diffMonth.hashCode(), a.hashCode());
        assertNotEquals(diffLeapMonth.hashCode(), a.hashCode());
    }

    @Test
    void testCompareTo() {
        LunarMonth a = LunarMonth.of(1, false);
        LunarMonth diffMonth = LunarMonth.of(2, false);
        LunarMonth diffLeapMonth = LunarMonth.of(1, true);

        assertEquals(-1, a.compareTo(diffMonth));
        assertEquals(-1, a.compareTo(diffLeapMonth));
    }

    @Test
    void testOf() {
        LunarMonth a = LunarMonth.of(1, false);
        assertEquals(1, a.value());
        assertFalse(a.isLeapMonth());

        LunarMonth b = LunarMonth.of(1, true);
        assertEquals(1, b.value());
        assertTrue(b.isLeapMonth());

        assertThrows(IllegalArgumentException.class, () -> LunarMonth.of(0, false));
        assertThrows(IllegalArgumentException.class, () -> LunarMonth.of(13, false));
    }
}
