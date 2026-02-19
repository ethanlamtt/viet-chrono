package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;


import static io.github.ethanlamtt.vietchrono.astro.LunarPhase.NEW_MOON;
import static org.junit.jupiter.api.Assertions.*;

class LunarTimeTest {

    @Test
    void testGetInstance() {
        LunarTime a = LunarTime.getInstance();
        LunarTime b = LunarTime.getInstance();
        assertSame(b, a);
    }

    @Test
    void testBefore() {
        LunarTime lunarTime = LunarTime.getInstance();
        JulianDay jd = lunarTime.before(Moment.of(0), NEW_MOON)
                        .toEphemeris();

        // December 9, 1969, 09:42 UTC
        assertEquals(2440564.90417, jd.value(), 1e-3);
    }

    @Test
    void testAfter() {
        LunarTime lunarTime = LunarTime.getInstance();
        JulianDay jd = lunarTime.after(Moment.of(0), NEW_MOON)
                .toEphemeris();

        // January 7, 1970, 20:35 UTC
        assertEquals(2440594.3576, jd.value(), 1e-3);
    }
}
