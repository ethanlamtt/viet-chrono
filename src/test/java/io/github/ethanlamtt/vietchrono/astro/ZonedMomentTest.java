package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZonedMomentTest {


    @Test
    void testEquals() {
        ZonedMoment a = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC);
        ZonedMoment b = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC);
        ZonedMoment c = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC);
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
        assertNotEquals("1640995200", a);

        ZonedMoment diffMoment = ZonedMoment.of(Moment.of(1), ZoneOffset.UTC);
        ZonedMoment diffZone = ZonedMoment.of(Moment.of(0), ZoneId.of("Asia/Ho_Chi_Minh"));
        assertNotEquals(diffMoment, a);
        assertNotEquals(diffZone, a);
    }

    @Test
    void testHashCode() {
        ZonedMoment a = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC);
        ZonedMoment b = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC);
        assertEquals(b.hashCode(), a.hashCode());

        ZonedMoment diffMoment = ZonedMoment.of(Moment.of(1), ZoneOffset.UTC);
        ZonedMoment diffZone = ZonedMoment.of(Moment.of(0), ZoneId.of("Asia/Ho_Chi_Minh"));
        assertNotEquals(diffMoment.hashCode(), a.hashCode());
        assertNotEquals(diffZone.hashCode(), a.hashCode());
    }

    @Test
    void testOf() {
        ZonedMoment a = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC);
        assertEquals(0, a.toMoment().value());
        assertEquals(ZoneOffset.UTC, a.zoneId());

        assertThrows(NullPointerException.class, () -> ZonedMoment.of(null, ZoneOffset.UTC));
        assertThrows(NullPointerException.class, () -> ZonedMoment.of(Moment.of(0), null));
    }

    @Test
    void testToLocalDate() {
        LocalDate a = ZonedMoment.of(Moment.of(0), ZoneOffset.UTC)
                        .toLocalDate();
        assertEquals(LocalDate.of(1970, 1, 1), a);
    }
}
