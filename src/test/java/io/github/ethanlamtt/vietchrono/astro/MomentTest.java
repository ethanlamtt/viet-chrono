package io.github.ethanlamtt.vietchrono.astro;

import io.github.ethanlamtt.vietchrono.scale.DeltaT;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneOffset;

import static io.github.ethanlamtt.vietchrono.scale.TimeScale.TT;
import static io.github.ethanlamtt.vietchrono.scale.TimeScale.UTC;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MomentTest {

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
        Moment a = Moment.of(0);
        assertEquals(0, a.value());
    }

    @Test
    void testOfInstant() {
        Moment a = Moment.ofInstant(Instant.ofEpochSecond(0));
        assertEquals(0, a.value());

        assertThrows(NullPointerException.class, () -> Moment.ofInstant(null));
    }

    @Test
    void testOfJulian() {
        Moment a = Moment.ofJulianDay(JulianDay.of(2440587.5, UTC));
        Moment b = Moment.ofJulianDay(JulianDay.of(2440587.5, TT));
        assertEquals(0, a.value());
        assertNotEquals(0, b.value());

        assertThrows(NullPointerException.class, () -> Moment.ofJulianDay(null));
    }

    @Test
    void testPlusSeconds() {
        Moment a = Moment.of(0)
                .plusSeconds(1);
        assertEquals(1, a.value());
    }

    @Test
    void testMinusSeconds() {
        Moment a = Moment.of(0)
                .minusSeconds(1);
        assertEquals(-1, a.value());
    }

    @Test
    void testPlusDays() {
        Moment a = Moment.of(0)
                .plusDays(2);
        assertEquals(172800, a.value());
    }

    @Test
    void testMinusDays() {
        Moment a = Moment.of(0)
                .minusDays(2);
        assertEquals(-172800, a.value());
    }

    @Test
    void testAtZone() {
        ZonedMoment a = Moment.of(0)
                .atZone(ZoneOffset.UTC);
        assertEquals(ZoneOffset.UTC, a.zoneId());
    }

    @Test
    void testToInstant() {
        Instant instant = Moment.of(0)
                .toInstant();
        assertEquals(0, instant.getEpochSecond());
    }

    @Test
    void testToEphemeris() {
        JulianDay jd = Moment.of(0)
                        .toEphemeris();
        double offsetDays = DeltaT.getInstance().atYear(1970) / 86400;
        double expectedValue = 2440587.5 + offsetDays;
        assertEquals(TT, jd.timescale());
        assertEquals(expectedValue, jd.value(), 1e-9);
    }

    @Test
    void testToUniversal() {
        JulianDay jd = Moment.of(0)
                .toUniversal();
        assertEquals(2440587.5, jd.value());
    }
}
