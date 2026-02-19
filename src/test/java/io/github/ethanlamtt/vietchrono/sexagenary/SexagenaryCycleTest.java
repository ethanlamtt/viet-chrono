package io.github.ethanlamtt.vietchrono.sexagenary;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.ZoneId;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem.*;
import static org.junit.jupiter.api.Assertions.*;

class SexagenaryCycleTest {

    @Test
    void testEquals() {
        SexagenaryCycle a = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle b = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle c = SexagenaryCycle.of(YANG_WOOD, RAT);
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

        SexagenaryCycle diffStem = SexagenaryCycle.of(YIN_WOOD, RAT);
        SexagenaryCycle diffBranch = SexagenaryCycle.of(YANG_WOOD, PIG);
        assertNotEquals(diffStem, a);
        assertNotEquals(diffBranch, a);
    }

    @Test
    void testHashCode() {
        SexagenaryCycle a = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle b = SexagenaryCycle.of(YANG_WOOD, RAT);
        assertEquals(b.hashCode(), a.hashCode());

        SexagenaryCycle diffStem = SexagenaryCycle.of(YIN_WOOD, RAT);
        SexagenaryCycle diffBranch = SexagenaryCycle.of(YANG_WOOD, PIG);
        assertNotEquals(diffStem.hashCode(), a.hashCode());
        assertNotEquals(diffBranch.hashCode(), a.hashCode());
    }


    @Test
    void testOf() {
        SexagenaryCycle a = SexagenaryCycle.of(YANG_WOOD, RAT);
        assertEquals(YANG_WOOD, a.stem());
        assertEquals(RAT, a.branch());

        Executable nullStem = () -> SexagenaryCycle.of(null, RAT);
        Executable nullBranch = () -> SexagenaryCycle.of(YANG_WOOD, null);
        assertThrows(NullPointerException.class, nullStem);
        assertThrows(NullPointerException.class, nullBranch);
    }

    @Test
    void testOfDay() {
        SexagenaryCycle a = SexagenaryCycle.ofDay(Moment.of(0), ZoneId.of("Asia/Ho_Chi_Minh"));
        assertEquals(YIN_METAL, a.stem());
        assertEquals(SNAKE, a.branch());
    }

    @Test
    void testOfMonth() {
        SexagenaryCycle cyclicYear = SexagenaryCycle.ofYear(2026);
        SexagenaryCycle a = SexagenaryCycle.ofMonth(cyclicYear, 3);
        assertEquals(YANG_WATER, a.stem());
        assertEquals(DRAGON, a.branch());
    }

    @Test
    void testOfYear() {
        SexagenaryCycle a = SexagenaryCycle.ofYear(2026);
        assertEquals(YANG_FIRE, a.stem());
        assertEquals(HORSE, a.branch());
    }

    @Test
    void testOfHour() {
        SexagenaryCycle cyclicDay = SexagenaryCycle.ofDay(Moment.of(0), ZoneId.of("Asia/Ho_Chi_Minh"));
        SexagenaryCycle a = SexagenaryCycle.ofHour(cyclicDay);
        assertEquals(YANG_EARTH, a.stem());
        assertEquals(RAT, a.branch());
    }
}
