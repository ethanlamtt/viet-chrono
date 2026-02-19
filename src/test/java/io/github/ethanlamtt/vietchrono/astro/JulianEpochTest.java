package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.astro.JulianEpoch.*;
import static org.junit.jupiter.api.Assertions.*;

class JulianEpochTest {

    @Test
    void testEquals() {
        JulianEpoch a = J2000;
        JulianEpoch b = J2000;
        JulianEpoch c = J2000;
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
        assertNotEquals("2451545.0", a);

        assertNotEquals(J1900, a);
        assertNotEquals(UNIX, a);
    }

    @Test
    void testHashCode() {
        JulianEpoch a = J2000;
        JulianEpoch b = JulianEpoch.of(JulianDay.ofEphemeris(2451545.0));
        assertEquals(b.hashCode(), a.hashCode());

        assertNotEquals(J1900.hashCode(), a.hashCode());
        assertNotEquals(UNIX.hashCode(), a.hashCode());
    }

    @Test
    void testOf() {
        JulianDay jd = JulianDay.ofEphemeris(2451545.0);
        JulianEpoch a = JulianEpoch.of(jd);
        assertEquals(2451545.0, a.value());
        assertEquals(jd, a.asJulianDay());

        assertThrows(NullPointerException.class, () -> JulianEpoch.of(null));
    }
}
