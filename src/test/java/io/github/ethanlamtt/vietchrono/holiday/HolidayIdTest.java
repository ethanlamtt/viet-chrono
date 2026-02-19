package io.github.ethanlamtt.vietchrono.holiday;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class HolidayIdTest {
    @Test
    void testEquals() {
        HolidayId a = HolidayId.of("New_Years_Eve", HolidayType.SOLAR);
        HolidayId b = HolidayId.of("New_Years_Eve", HolidayType.SOLAR);
        HolidayId c = HolidayId.of("New_Years_Eve", HolidayType.SOLAR);
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

        HolidayId diffId = HolidayId.of("New_Years_Day", HolidayType.SOLAR);
        HolidayId diffType = HolidayId.of("New_Years_Eve", HolidayType.LUNAR);
        assertNotEquals(diffId, a);
        assertNotEquals(diffType, a);
    }

    @Test
    void testHashCode() {
        HolidayId a = HolidayId.of("New_Years_Eve", HolidayType.SOLAR);
        HolidayId b = HolidayId.of("New_Years_Eve", HolidayType.SOLAR);
        assertEquals(b.hashCode(), a.hashCode());

        HolidayId diffId = HolidayId.of("New_Years_Day", HolidayType.SOLAR);
        HolidayId diffType = HolidayId.of("New_Years_Eve", HolidayType.LUNAR);
        assertNotEquals(diffId.hashCode(), a.hashCode());
        assertNotEquals(diffType.hashCode(), a.hashCode());
    }

    @Test
    void testOf() {
        HolidayId a = HolidayId.of("New_Years_Eve", HolidayType.SOLAR);
        assertEquals("New_Years_Eve", a.id());
        assertEquals(HolidayType.SOLAR, a.type());

        Executable nullId = () -> HolidayId.of(null, HolidayType.SOLAR);
        Executable nullType = () -> HolidayId.of("New_Years_Eve", null);
        assertThrows(NullPointerException.class, nullId);
        assertThrows(NullPointerException.class, nullType);
    }
}
