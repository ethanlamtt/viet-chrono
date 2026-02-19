package io.github.ethanlamtt.vietchrono.sexagenary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.RAT;
import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.WATER_BUFFALO;
import static org.junit.jupiter.api.Assertions.*;

class AuspiciousHourTest {

    @Test
    void testEquals() {
        AuspiciousHour a = AuspiciousHour.of(DoubleHour.of(RAT));
        AuspiciousHour b = AuspiciousHour.of(DoubleHour.of(RAT));
        AuspiciousHour c = AuspiciousHour.of(DoubleHour.of(RAT));
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

        AuspiciousHour diffDoubleHour = AuspiciousHour.of(DoubleHour.of(WATER_BUFFALO));
        assertNotEquals(diffDoubleHour, a);
    }

    @Test
    void testHashCode() {
        AuspiciousHour a = AuspiciousHour.of(DoubleHour.of(RAT));
        AuspiciousHour b = AuspiciousHour.of(DoubleHour.of(RAT));
        assertEquals(b.hashCode(), a.hashCode());

        AuspiciousHour diffDoubleHour = AuspiciousHour.of(DoubleHour.of(WATER_BUFFALO));
        assertNotEquals(diffDoubleHour.hashCode(), a.hashCode());
    }

    @Test
    void testOf() {
        AuspiciousHour a = AuspiciousHour.of(DoubleHour.of(RAT));

        assertEquals(DoubleHour.of(RAT), a.doubleHour());

        Executable nullDoubleHour = () -> AuspiciousHour.of(null);
        assertThrows(NullPointerException.class, nullDoubleHour);
    }
}
