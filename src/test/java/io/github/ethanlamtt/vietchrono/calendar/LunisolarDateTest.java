package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LunisolarDateTest {

    @Test
    void testEquals() {
        Moment a = Moment.of(0);
        Moment b = Moment.of(0);
        Moment c = Moment.of(0);
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

        Moment diffEpochSecond = Moment.of(1);
        assertNotEquals(diffEpochSecond, a);
    }

    @Test
    void testHashCode() {
        Moment a = Moment.of(0);
        Moment b = Moment.of(0);
        assertEquals(b.hashCode(), a.hashCode());

        assertNotEquals(Moment.of(1).hashCode(), a.hashCode());
    }

    @Test
    void testCompareTo() {
        Moment a = Moment.of(0);
        Moment b = Moment.of(1);
        assertEquals(-1, a.compareTo(b));
        assertEquals(1, b.compareTo(a));
    }

    @Test
    void testOf() {
    }
}
