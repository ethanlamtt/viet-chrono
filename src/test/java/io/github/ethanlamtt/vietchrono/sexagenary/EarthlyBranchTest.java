package io.github.ethanlamtt.vietchrono.sexagenary;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EarthlyBranchTest {

    @Test
    void testOfIndex() {
        assertEquals(RAT, EarthlyBranch.ofIndex(0));
        assertEquals(WATER_BUFFALO, EarthlyBranch.ofIndex(1));
        assertEquals(TIGER, EarthlyBranch.ofIndex(2));
        assertEquals(CAT, EarthlyBranch.ofIndex(3));
        assertEquals(DRAGON, EarthlyBranch.ofIndex(4));
        assertEquals(SNAKE, EarthlyBranch.ofIndex(5));
        assertEquals(HORSE, EarthlyBranch.ofIndex(6));
        assertEquals(GOAT, EarthlyBranch.ofIndex(7));
        assertEquals(MONKEY, EarthlyBranch.ofIndex(8));
        assertEquals(ROOSTER, EarthlyBranch.ofIndex(9));
        assertEquals(DOG, EarthlyBranch.ofIndex(10));
        assertEquals(PIG, EarthlyBranch.ofIndex(11));

        assertThrows(IllegalArgumentException.class, () -> HeavenlyStem.ofIndex(12));
    }
    
}
