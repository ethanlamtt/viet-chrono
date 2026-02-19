package io.github.ethanlamtt.vietchrono.sexagenary;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DoubleHourTest {

    @Test
    void testEquals() {
        DoubleHour a = DoubleHour.of(EarthlyBranch.CAT);
        DoubleHour b = DoubleHour.of(EarthlyBranch.CAT);
        DoubleHour c = DoubleHour.of(EarthlyBranch.CAT);
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

        DoubleHour diffBranch = DoubleHour.of(EarthlyBranch.TIGER);
        assertNotEquals(diffBranch, a);
    }

    @Test
    void testHashCode() {
        DoubleHour a = DoubleHour.of(EarthlyBranch.CAT);
        DoubleHour b = DoubleHour.of(EarthlyBranch.CAT);
        assertEquals(b.hashCode(), a.hashCode());

        assertNotEquals(DoubleHour.of(EarthlyBranch.TIGER).hashCode(), a.hashCode());
    }

    @Test
    void testCompareTo() {
        DoubleHour a = DoubleHour.of(EarthlyBranch.CAT);
        DoubleHour b = DoubleHour.of(EarthlyBranch.TIGER);
        assertEquals(1, a.compareTo(b));
        assertEquals(-1, b.compareTo(a));
    }

    @Test
    void testOf() {
        DoubleHour doubleHour = DoubleHour.of(EarthlyBranch.RAT);
        assertEquals(LocalTime.of(23,  0), doubleHour.startTime());
        assertEquals(LocalTime.of(1,  0), doubleHour.endTime());
        assertEquals(EarthlyBranch.RAT, doubleHour.branch());
    }
}
