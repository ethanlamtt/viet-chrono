package io.github.ethanlamtt.vietchrono.sexagenary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SexagenaryDateTest {

    @Test
    void testEquals() {
        SexagenaryCycle cyclicYear = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicMonth = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicDay = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryDate a = SexagenaryDate.of(cyclicYear, cyclicMonth, cyclicDay);
        SexagenaryDate b = SexagenaryDate.of(cyclicYear, cyclicMonth, cyclicDay);
        SexagenaryDate c = SexagenaryDate.of(cyclicYear, cyclicMonth, cyclicDay);
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
        SexagenaryDate diffYear = SexagenaryDate.of(diffCyclicYear, cyclicMonth, cyclicDay);
        SexagenaryDate diffMonth = SexagenaryDate.of(cyclicYear, diffCyclicMonth, cyclicDay);
        SexagenaryDate diffDay = SexagenaryDate.of(cyclicYear, cyclicMonth, diffCyclicDay);
        assertNotEquals(diffYear, a);
        assertNotEquals(diffMonth, a);
        assertNotEquals(diffDay, a);
    }

    @Test
    void testHashCode() {
        SexagenaryCycle cyclicYear = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicMonth = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryCycle cyclicDay = SexagenaryCycle.of(YANG_WOOD, RAT);
        SexagenaryDate a = SexagenaryDate.of(cyclicYear, cyclicMonth, cyclicDay);
        SexagenaryDate b = SexagenaryDate.of(cyclicYear, cyclicMonth, cyclicDay);
        assertEquals(b.hashCode(), a.hashCode());

        SexagenaryCycle diffCyclicYear = SexagenaryCycle.of(YANG_WOOD, TIGER);
        SexagenaryCycle diffCyclicMonth = SexagenaryCycle.of(YANG_WOOD, PIG);
        SexagenaryCycle diffCyclicDay = SexagenaryCycle.of(YANG_WOOD, DOG);
        SexagenaryDate diffYear = SexagenaryDate.of(diffCyclicYear, cyclicMonth, cyclicDay);
        SexagenaryDate diffMonth = SexagenaryDate.of(cyclicYear, diffCyclicMonth, cyclicDay);
        SexagenaryDate diffDay = SexagenaryDate.of(cyclicYear, cyclicMonth, diffCyclicDay);
        assertNotEquals(diffYear.hashCode(), a.hashCode());
        assertNotEquals(diffMonth.hashCode(), a.hashCode());
        assertNotEquals(diffDay.hashCode(), a.hashCode());

        Executable nullYear = () -> SexagenaryDate.of(null, cyclicMonth, cyclicDay);
        Executable nullMonth = () -> SexagenaryDate.of(cyclicYear, null, cyclicDay);
        Executable nullDay = () -> SexagenaryDate.of(cyclicYear, cyclicMonth, null);
        assertThrows(NullPointerException.class, nullYear);
        assertThrows(NullPointerException.class, nullMonth);
        assertThrows(NullPointerException.class, nullDay);
    }

    @Test
    void testAuspiciousHours() {
        SexagenaryCycle cyclicYear = SexagenaryCycle.of(YANG_FIRE, HORSE);
        SexagenaryCycle cyclicMonth = SexagenaryCycle.of(YIN_METAL, CAT);
        SexagenaryCycle cyclicDay = SexagenaryCycle.of(YIN_METAL, PIG);
        SexagenaryDate a = SexagenaryDate.of(cyclicYear, cyclicMonth, cyclicDay);
        assertEquals(6, a.auspiciousHours().size());

        List<AuspiciousHour> auspiciousHours = a.auspiciousHours();
        assertNotNull(auspiciousHours);
        assertEquals(6, auspiciousHours.size());
        assertEquals(AuspiciousHour.of(DoubleHour.of(WATER_BUFFALO)), auspiciousHours.get(0));
        assertEquals(AuspiciousHour.of(DoubleHour.of(DRAGON)), auspiciousHours.get(1));
        assertEquals(AuspiciousHour.of(DoubleHour.of(HORSE)), auspiciousHours.get(2));
        assertEquals(AuspiciousHour.of(DoubleHour.of(GOAT)), auspiciousHours.get(3));
        assertEquals(AuspiciousHour.of(DoubleHour.of(DOG)), auspiciousHours.get(4));
        assertEquals(AuspiciousHour.of(DoubleHour.of(PIG)), auspiciousHours.get(5));
    }

}
