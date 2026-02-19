package io.github.ethanlamtt.vietchrono.sexagenary;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeavenlyStemTest {

    @Test
    void testOfIndex() {
        assertEquals(YANG_WOOD, HeavenlyStem.ofIndex(0));
        assertEquals(YIN_WOOD, HeavenlyStem.ofIndex(1));
        assertEquals(YANG_FIRE, HeavenlyStem.ofIndex(2));
        assertEquals(YIN_FIRE, HeavenlyStem.ofIndex(3));
        assertEquals(YANG_EARTH, HeavenlyStem.ofIndex(4));
        assertEquals(YIN_EARTH, HeavenlyStem.ofIndex(5));
        assertEquals(YANG_METAL, HeavenlyStem.ofIndex(6));
        assertEquals(YIN_METAL, HeavenlyStem.ofIndex(7));
        assertEquals(YANG_WATER, HeavenlyStem.ofIndex(8));
        assertEquals(YIN_WATER, HeavenlyStem.ofIndex(9));

        assertThrows(IllegalArgumentException.class, () -> HeavenlyStem.ofIndex(10));
    }
}
