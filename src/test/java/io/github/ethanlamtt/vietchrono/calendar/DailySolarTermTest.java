package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.START_OF_SPRING;
import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.WINTER_SOLSTICE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DailySolarTermTest {

    @Test
    void testEquals() {
        DailySolarTerm a = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(0));
        DailySolarTerm b = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(0));
        DailySolarTerm c = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(0));
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

        DailySolarTerm diffSolarTerm = DailySolarTerm.of(START_OF_SPRING, Moment.of(0));
        DailySolarTerm diffTransition = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(-86400));
        assertNotEquals(diffSolarTerm, a);
        assertNotEquals(diffTransition, a);
    }

    @Test
    void testHashCode() {
        DailySolarTerm a = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(0));
        DailySolarTerm b = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(0));
        assertEquals(b.hashCode(), a.hashCode());

        DailySolarTerm diffTerm = DailySolarTerm.of(START_OF_SPRING, Moment.of(0));
        DailySolarTerm diffTransition = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(-86400));
        assertNotEquals(diffTerm.hashCode(), a.hashCode());
        assertNotEquals(diffTransition.hashCode(), a.hashCode());
    }

    @Test
    void testOf() {
        DailySolarTerm a = DailySolarTerm.of(WINTER_SOLSTICE, Moment.of(0));

        assertEquals(WINTER_SOLSTICE, a.termOfDay());
        assertEquals(Moment.of(0), a.transition().get());

        assertThrows(NullPointerException.class,
                     () -> DailySolarTerm.of(null, Moment.of(0)));
        assertDoesNotThrow(() -> DailySolarTerm.of(WINTER_SOLSTICE, null));
    }
}
