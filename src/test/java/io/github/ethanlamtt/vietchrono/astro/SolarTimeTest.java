package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.scale.TimeScale.UTC;
import static org.junit.jupiter.api.Assertions.*;

class SolarTimeTest {

    @Test
    void testGetInstance() {
        SolarTime a = SolarTime.getInstance();
        SolarTime b = SolarTime.getInstance();
        assertSame(b, a);
    }

    @Test
    void testApparentLongitudeAt() {
        SolarTime a = SolarTime.getInstance();
        double longitude = a.apparentLongitudeAt(Moment.of(0));

        assertEquals(280.1562711, longitude, 1e-2);
    }

    @Test
    void testAtLongitude() {
        SolarTime a = SolarTime.getInstance();
        JulianDay jd = a.atLongitude(281.1755766, Moment.of(0))
                        .toUniversal();

        assertEquals(JulianDay.of(2440588.5, UTC).value(), jd.value(), 1e-2);
    }

}
