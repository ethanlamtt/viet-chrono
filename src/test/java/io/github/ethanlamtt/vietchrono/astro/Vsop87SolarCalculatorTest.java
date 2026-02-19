package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Vsop87SolarCalculatorTest {

    @Test
    void testApparentLongitude() {
        SolarCalculator calculator = Vsop87SolarCalculator.getInstance();
        double time = calculator.apparentLongitude(2448908.5);
        assertEquals(199.907372, time, 1e-2);
    }
}
