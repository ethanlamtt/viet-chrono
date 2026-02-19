package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.astro.LunarPhase.NEW_MOON;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LunarPhaseTest {

    @Test
    void testAngleFraction() {
        assertEquals(0.0, NEW_MOON.angleFraction());
    }
}
