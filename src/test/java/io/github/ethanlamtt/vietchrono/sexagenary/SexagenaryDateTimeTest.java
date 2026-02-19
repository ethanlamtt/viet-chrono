package io.github.ethanlamtt.vietchrono.sexagenary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.PIG;
import static io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SexagenaryDateTimeTest {
    @Test
    void testEquals() {
        SexagenaryCycle cyclicYear = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicMonth = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicDay = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicHour = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryDateTime a = SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime b = SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime c = SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, cyclicHour);
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

        SexagenaryCycle diffCyclicYear = SexagenaryCycle.of(YANG_WOOD, TIGER);
        SexagenaryCycle diffCyclicMonth = SexagenaryCycle.of(YANG_WOOD, PIG);
        SexagenaryCycle diffCyclicDay = SexagenaryCycle.of(YANG_WOOD, DOG);
        SexagenaryCycle diffCyclicHour = SexagenaryCycle.of(YANG_WOOD, WATER_BUFFALO);
        SexagenaryDateTime diffYear = SexagenaryDateTime.of(diffCyclicYear, cyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime diffMonth = SexagenaryDateTime.of(cyclicYear, diffCyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime diffDay = SexagenaryDateTime.of(cyclicYear, cyclicMonth, diffCyclicDay, cyclicHour);
        SexagenaryDateTime diffHour = SexagenaryDateTime.of(cyclicYear, cyclicMonth, diffCyclicDay, diffCyclicHour);
        assertNotEquals(diffYear, a);
        assertNotEquals(diffMonth, a);
        assertNotEquals(diffDay, a);
        assertNotEquals(diffHour, a);
    }

    @Test
    void testHashCode() {
        SexagenaryCycle cyclicYear = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicMonth = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicDay = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicHour = SexagenaryCycle.of(YANG_WOOD, RAT);

        SexagenaryDateTime a = SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime b = SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, cyclicHour);
        assertEquals(b.hashCode(), a.hashCode());

        SexagenaryCycle diffCyclicYear = SexagenaryCycle.of(YANG_WOOD, TIGER);
        SexagenaryCycle diffCyclicMonth = SexagenaryCycle.of(YANG_WOOD, PIG);
        SexagenaryCycle diffCyclicDay = SexagenaryCycle.of(YANG_WOOD, DOG);
        SexagenaryCycle diffCyclicHour = SexagenaryCycle.of(YANG_WOOD, WATER_BUFFALO);
        SexagenaryDateTime diffYear = SexagenaryDateTime.of(diffCyclicYear, cyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime diffMonth = SexagenaryDateTime.of(cyclicYear, diffCyclicMonth, cyclicDay, cyclicHour);
        SexagenaryDateTime diffDay = SexagenaryDateTime.of(cyclicYear, cyclicMonth, diffCyclicDay, cyclicHour);
        SexagenaryDateTime diffHour = SexagenaryDateTime.of(cyclicYear, cyclicMonth, diffCyclicDay, diffCyclicHour);
        assertNotEquals(diffYear.hashCode(), a.hashCode());
        assertNotEquals(diffMonth.hashCode(), a.hashCode());
        assertNotEquals(diffDay.hashCode(), a.hashCode());
        assertNotEquals(diffHour.hashCode(), a.hashCode());

        Executable nullYear = () -> SexagenaryDateTime.of(null, cyclicMonth, cyclicDay, cyclicHour);
        Executable nullMonth = () -> SexagenaryDateTime.of(cyclicYear, null, cyclicDay, cyclicHour);
        Executable nullDay = () -> SexagenaryDateTime.of(cyclicYear, cyclicMonth, null, cyclicHour);
        Executable nullHour = () -> SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, null);
        assertThrows(NullPointerException.class, nullYear);
        assertThrows(NullPointerException.class, nullMonth);
        assertThrows(NullPointerException.class, nullDay);
        assertThrows(NullPointerException.class, nullHour);
    }
}
