package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.astro.JulianDay.ofEphemeris;
import static io.github.ethanlamtt.vietchrono.astro.JulianDay.ofUniversal;
import static io.github.ethanlamtt.vietchrono.scale.TimeScale.TT;
import static io.github.ethanlamtt.vietchrono.scale.TimeScale.UTC;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JulianDayTest {

    @Test
    void testEquals() {
        JulianDay a = ofEphemeris(2451545.0);
        JulianDay b = ofEphemeris(2451545.0);
        JulianDay c = ofEphemeris(2451545.0);
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

        JulianDay diffValue = ofEphemeris(2451545.5);
        JulianDay diffScale = ofUniversal(2451545.0);
        assertNotEquals(diffValue, a);
        assertNotEquals(diffScale, a);
    }

    @Test
    void testHashCode() {
        JulianDay a = ofEphemeris(2451545.0);
        JulianDay b = ofEphemeris(2451545.0);
        assertEquals(b.hashCode(), a.hashCode());

        JulianDay diffValue = ofEphemeris(2451545.5);
        JulianDay diffScale = ofUniversal(2451545.0);
        assertNotEquals(diffValue.hashCode(), a.hashCode());
        assertNotEquals(diffScale.hashCode(), a.hashCode());
    }

    @Test
    void testOf() {
        JulianDay a = JulianDay.of(2451545.0, UTC);
        assertEquals(2451545.0, a.value());
        assertEquals(UTC, a.timescale());

        assertThrows(NullPointerException.class, () -> JulianDay.of(123, null));
    }

    @Test
    void testOfEphemeris() {
        JulianDay jd = ofEphemeris(2451545.0);
        assertEquals(2451545.0, jd.value());
        assertEquals(TT, jd.timescale());
    }

    @Test
    void testOfUniversal() {
        JulianDay jd = ofUniversal(2451545.0);
        assertEquals(2451545.0, jd.value());
        assertEquals(UTC, jd.timescale());
    }

    @Test
    void testWithValue() {
        JulianDay a = JulianDay.of(2451545.0, TT);
        JulianDay b = a.withValue(2451533.0);
        assertEquals(2451533.0, b.value());
        assertEquals(TT, b.timescale());
    }

    @Test
    void testWithTimescale() {
        JulianDay a = JulianDay.of(2451545.0, TT);
        JulianDay b = a.withTimeScale(UTC);
        assertEquals(UTC, b.timescale());
        assertEquals(2451545.0, b.value());
    }

    @Test
    void testPlusDays() {
        JulianDay jd = ofEphemeris(2451545.0);
        assertEquals(2451546.0, jd.plusDays(1).value());
    }

    @Test
    void testMinusDays() {
        JulianDay jd = ofEphemeris(2451545.0);
        assertEquals(2451544.5, jd.minusDays(0.5).value());
    }

    @Test
    void testToMoment() {
        Moment a = ofUniversal(2440587.5)
                .toMoment();
        Moment b = ofEphemeris(2440587.5)
                .toMoment();
        assertEquals(0, a.value());
        assertNotEquals(0, b.value());
    }
}
