package io.github.ethanlamtt.vietchrono.astro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AstroUtilsTest {

    @Test
    void testNormalizeAngle() {
        double a = AstroUtils.normalizeAngle(361.5);
        double b = AstroUtils.normalizeAngle(-355.2);
        double epsilon = 1e-9;

        assertEquals(1.5, a, epsilon);
        assertEquals(4.8, b, epsilon);
    }
}
