package io.github.ethanlamtt.vietchrono.calendar;

import org.junit.jupiter.api.Test;

import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.*;
import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.ofLongitude;
import static org.junit.jupiter.api.Assertions.*;

class SolarTermTest {
    @Test
    void testOfLongitude() {
        assertEquals(START_OF_SPRING, ofLongitude(315));
        assertEquals(RAIN_WATER, ofLongitude(330));
        assertEquals(AWAKENING_OF_INSECTS, ofLongitude(345));
        assertEquals(VERNAL_EQUINOX, ofLongitude(0));
        assertEquals(PURE_BRIGHTNESS, ofLongitude(15));
        assertEquals(GRAIN_RAIN, ofLongitude(30));
        assertEquals(START_OF_SUMMER, ofLongitude(45));
        assertEquals(GRAIN_BUDS, ofLongitude(60));
        assertEquals(GRAIN_IN_EAR, ofLongitude(75));
        assertEquals(SUMMER_SOLSTICE, ofLongitude(90));
        assertEquals(MINOR_HEAT, ofLongitude(105));
        assertEquals(MAJOR_HEAT, ofLongitude(120));
        assertEquals(START_OF_AUTUMN, ofLongitude(135));
        assertEquals(LIMIT_OF_HEAT, ofLongitude(150));
        assertEquals(WHITE_DEW, ofLongitude(165));
        assertEquals(AUTUMNAL_EQUINOX, ofLongitude(180));
        assertEquals(COLD_DEW, ofLongitude(195));
        assertEquals(FROST_DESCENT, ofLongitude(210));
        assertEquals(START_OF_WINTER, ofLongitude(225));
        assertEquals(MINOR_SNOW, ofLongitude(240));
        assertEquals(MAJOR_SNOW, ofLongitude(255));
        assertEquals(WINTER_SOLSTICE, ofLongitude(270));
        assertEquals(MINOR_COLD, ofLongitude(285));
        assertEquals(MAJOR_COLD, ofLongitude(300));

        assertThrows(IllegalArgumentException.class, () -> ofLongitude(-1));
    }

    @Test
    void testFrom() {
        assertEquals(START_OF_SPRING, from(317));
        assertEquals(RAIN_WATER, from(332));
        assertEquals(AWAKENING_OF_INSECTS, from(347));
        assertEquals(VERNAL_EQUINOX, from(2));
        assertEquals(PURE_BRIGHTNESS, from(17));
        assertEquals(GRAIN_RAIN, from(32));
        assertEquals(START_OF_SUMMER, from(47));
        assertEquals(GRAIN_BUDS, from(62));
        assertEquals(GRAIN_IN_EAR, from(77));
        assertEquals(SUMMER_SOLSTICE, from(92));
        assertEquals(MINOR_HEAT, from(107));
        assertEquals(MAJOR_HEAT, from(122));
        assertEquals(START_OF_AUTUMN, from(137));
        assertEquals(LIMIT_OF_HEAT, from(152));
        assertEquals(WHITE_DEW, from(167));
        assertEquals(AUTUMNAL_EQUINOX, from(182));
        assertEquals(COLD_DEW, from(197));
        assertEquals(FROST_DESCENT, from(212));
        assertEquals(START_OF_WINTER, from(227));
        assertEquals(MINOR_SNOW, from(242));
        assertEquals(MAJOR_SNOW, from(257));
        assertEquals(WINTER_SOLSTICE, from(272));
        assertEquals(MINOR_COLD, from(287));
        assertEquals(MAJOR_COLD, from(302));

        assertEquals(AWAKENING_OF_INSECTS, from(-1));
        assertEquals(VERNAL_EQUINOX, from(361));
    }

    @Test
    void testMajorFrom() {
        assertEquals(MAJOR_COLD, majorFrom(317));
        assertEquals(RAIN_WATER, majorFrom(332));
        assertEquals(RAIN_WATER, majorFrom(347));
        assertEquals(VERNAL_EQUINOX, majorFrom(2));
        assertEquals(VERNAL_EQUINOX, majorFrom(17));
        assertEquals(GRAIN_RAIN, majorFrom(32));
        assertEquals(GRAIN_RAIN, majorFrom(47));
        assertEquals(GRAIN_BUDS, majorFrom(62));
        assertEquals(GRAIN_BUDS, majorFrom(77));
        assertEquals(SUMMER_SOLSTICE, majorFrom(92));
        assertEquals(SUMMER_SOLSTICE, majorFrom(107));
        assertEquals(MAJOR_HEAT, majorFrom(122));
        assertEquals(MAJOR_HEAT, majorFrom(137));
        assertEquals(LIMIT_OF_HEAT, majorFrom(152));
        assertEquals(LIMIT_OF_HEAT, majorFrom(167));
        assertEquals(AUTUMNAL_EQUINOX, majorFrom(182));
        assertEquals(AUTUMNAL_EQUINOX, majorFrom(197));
        assertEquals(FROST_DESCENT, majorFrom(212));
        assertEquals(FROST_DESCENT, majorFrom(227));
        assertEquals(MINOR_SNOW, majorFrom(242));
        assertEquals(MINOR_SNOW, majorFrom(257));
        assertEquals(WINTER_SOLSTICE, majorFrom(272));
        assertEquals(WINTER_SOLSTICE, majorFrom(287));
        assertEquals(MAJOR_COLD, majorFrom(302));

        assertEquals(RAIN_WATER, majorFrom(-1));
        assertEquals(VERNAL_EQUINOX, majorFrom(361));
    }

    @Test
    void testIsMajorTerm() {
        assertTrue(RAIN_WATER.isMajorTerm());
        assertTrue(VERNAL_EQUINOX.isMajorTerm());
        assertTrue(GRAIN_RAIN.isMajorTerm());
        assertTrue(GRAIN_BUDS.isMajorTerm());
        assertTrue(SUMMER_SOLSTICE.isMajorTerm());
        assertTrue(MAJOR_HEAT.isMajorTerm());
        assertTrue(LIMIT_OF_HEAT.isMajorTerm());
        assertTrue(AUTUMNAL_EQUINOX.isMajorTerm());
        assertTrue(FROST_DESCENT.isMajorTerm());
        assertTrue(MINOR_SNOW.isMajorTerm());
        assertTrue(WINTER_SOLSTICE.isMajorTerm());
        assertTrue(MAJOR_COLD.isMajorTerm());

        assertFalse(START_OF_SPRING.isMajorTerm());
        assertFalse(AWAKENING_OF_INSECTS.isMajorTerm());
        assertFalse(PURE_BRIGHTNESS.isMajorTerm());
        assertFalse(START_OF_SUMMER.isMajorTerm());
        assertFalse(GRAIN_IN_EAR.isMajorTerm());
        assertFalse(MINOR_HEAT.isMajorTerm());
        assertFalse(START_OF_AUTUMN.isMajorTerm());
        assertFalse(WHITE_DEW.isMajorTerm());
        assertFalse(COLD_DEW.isMajorTerm());
        assertFalse(START_OF_WINTER.isMajorTerm());
        assertFalse(MAJOR_SNOW.isMajorTerm());
        assertFalse(MINOR_COLD.isMajorTerm());
    }

    @Test
    void testRoll() {
        assertEquals(WINTER_SOLSTICE, WINTER_SOLSTICE.roll(0));
        assertEquals(START_OF_SPRING, WINTER_SOLSTICE.roll(3));
        assertEquals(START_OF_WINTER, WINTER_SOLSTICE.roll(-3));
        assertEquals(START_OF_AUTUMN, WINTER_SOLSTICE.roll(9999999));
    }

    @Test
    void testPreviousTerm() {
        assertEquals(MAJOR_COLD, START_OF_SPRING.previousTerm());
        assertEquals(START_OF_SPRING, RAIN_WATER.previousTerm());
        assertEquals(RAIN_WATER, AWAKENING_OF_INSECTS.previousTerm());
        assertEquals(AWAKENING_OF_INSECTS, VERNAL_EQUINOX.previousTerm());
        assertEquals(VERNAL_EQUINOX, PURE_BRIGHTNESS.previousTerm());
        assertEquals(PURE_BRIGHTNESS, GRAIN_RAIN.previousTerm());
        assertEquals(GRAIN_RAIN, START_OF_SUMMER.previousTerm());
        assertEquals(START_OF_SUMMER, GRAIN_BUDS.previousTerm());
        assertEquals(GRAIN_BUDS, GRAIN_IN_EAR.previousTerm());
        assertEquals(GRAIN_IN_EAR, SUMMER_SOLSTICE.previousTerm());
        assertEquals(SUMMER_SOLSTICE, MINOR_HEAT.previousTerm());
        assertEquals(MINOR_HEAT, MAJOR_HEAT.previousTerm());
        assertEquals(MAJOR_HEAT, START_OF_AUTUMN.previousTerm());
        assertEquals(START_OF_AUTUMN, LIMIT_OF_HEAT.previousTerm());
        assertEquals(LIMIT_OF_HEAT, WHITE_DEW.previousTerm());
        assertEquals(WHITE_DEW, AUTUMNAL_EQUINOX.previousTerm());
        assertEquals(AUTUMNAL_EQUINOX, COLD_DEW.previousTerm());
        assertEquals(COLD_DEW, FROST_DESCENT.previousTerm());
        assertEquals(FROST_DESCENT, START_OF_WINTER.previousTerm());
        assertEquals(START_OF_WINTER, MINOR_SNOW.previousTerm());
        assertEquals(MINOR_SNOW, MAJOR_SNOW.previousTerm());
        assertEquals(MAJOR_SNOW, WINTER_SOLSTICE.previousTerm());
        assertEquals(WINTER_SOLSTICE, MINOR_COLD.previousTerm());
        assertEquals(MINOR_COLD, MAJOR_COLD.previousTerm());
    }

    @Test
    void testNextTerm() {
        assertEquals(RAIN_WATER, START_OF_SPRING.nextTerm());
        assertEquals(AWAKENING_OF_INSECTS, RAIN_WATER.nextTerm());
        assertEquals(VERNAL_EQUINOX, AWAKENING_OF_INSECTS.nextTerm());
        assertEquals(PURE_BRIGHTNESS, VERNAL_EQUINOX.nextTerm());
        assertEquals(GRAIN_RAIN, PURE_BRIGHTNESS.nextTerm());
        assertEquals(START_OF_SUMMER, GRAIN_RAIN.nextTerm());
        assertEquals(GRAIN_BUDS, START_OF_SUMMER.nextTerm());
        assertEquals(GRAIN_IN_EAR, GRAIN_BUDS.nextTerm());
        assertEquals(SUMMER_SOLSTICE, GRAIN_IN_EAR.nextTerm());
        assertEquals(MINOR_HEAT, SUMMER_SOLSTICE.nextTerm());
        assertEquals(MAJOR_HEAT, MINOR_HEAT.nextTerm());
        assertEquals(START_OF_AUTUMN, MAJOR_HEAT.nextTerm());
        assertEquals(LIMIT_OF_HEAT, START_OF_AUTUMN.nextTerm());
        assertEquals(WHITE_DEW, LIMIT_OF_HEAT.nextTerm());
        assertEquals(AUTUMNAL_EQUINOX, WHITE_DEW.nextTerm());
        assertEquals(COLD_DEW, AUTUMNAL_EQUINOX.nextTerm());
        assertEquals(FROST_DESCENT, COLD_DEW.nextTerm());
        assertEquals(START_OF_WINTER, FROST_DESCENT.nextTerm());
        assertEquals(MINOR_SNOW, START_OF_WINTER.nextTerm());
        assertEquals(MAJOR_SNOW, MINOR_SNOW.nextTerm());
        assertEquals(WINTER_SOLSTICE, MAJOR_SNOW.nextTerm());
        assertEquals(MINOR_COLD, WINTER_SOLSTICE.nextTerm());
        assertEquals(MAJOR_COLD, MINOR_COLD.nextTerm());
        assertEquals(START_OF_SPRING, MAJOR_COLD.nextTerm());
    }
}
