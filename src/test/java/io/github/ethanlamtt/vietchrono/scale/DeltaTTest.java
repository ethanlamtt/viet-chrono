package io.github.ethanlamtt.vietchrono.scale;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class DeltaTTest {

    @Test
    void testGetInstance() {
        DeltaT a = DeltaT.getInstance();
        DeltaT b = DeltaT.getInstance();
        assertSame(a, b);
    }

    @Test
    void testAtJulianDay() {
        DeltaT deltaT = DeltaT.getInstance();
        double value = deltaT.atJulianDay(2451545.0);
        assertEquals(63.86, value, 1e-9);
    }

    @Test
    void testAtYear() {
        DeltaT deltaT = DeltaT.getInstance();
        double value = deltaT.atYear(2000);
        assertEquals(63.86, value, 1e-9);
    }

    @Test
    void testAtZonedDateTime() {
        DeltaT deltaT = DeltaT.getInstance();
        ZonedDateTime zdt = ZonedDateTime.of(2000, 1, 1, 0,
                0, 0, 0, ZoneOffset.UTC);
        double value = deltaT.atZonedDateTime(zdt);
        assertEquals(63.86, value, 1e-9);
    }
}
