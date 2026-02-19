package io.github.ethanlamtt.vietchrono.format;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.*;
import static org.junit.jupiter.api.Assertions.*;

class SolarTermDisplayNameProviderTest {

    @Test
    void testGetInstance() {
        SolarTermDisplayNameProvider a = SolarTermDisplayNameProvider.getInstance();
        SolarTermDisplayNameProvider b = SolarTermDisplayNameProvider.getInstance();
        assertNotNull(a);
        assertSame(a, b);
    }

    @Test
    void testGetNameVi() {
        SolarTermDisplayNameProvider a = SolarTermDisplayNameProvider.getInstance();
        Locale vn = Locale.of("vi", "VN");
        assertEquals("Vũ Thủy", a.getName(RAIN_WATER, vn));
        assertEquals("Kinh Trập", a.getName(AWAKENING_OF_INSECTS, vn));
        assertEquals("Xuân Phân", a.getName(VERNAL_EQUINOX, vn));
        assertEquals("Thanh Minh", a.getName(PURE_BRIGHTNESS, vn));
        assertEquals("Cốc Vũ", a.getName(GRAIN_RAIN, vn));
        assertEquals("Lập Hạ", a.getName(START_OF_SUMMER, vn));
        assertEquals("Tiểu Mãn", a.getName(GRAIN_BUDS, vn));
        assertEquals("Mang Chủng", a.getName(GRAIN_IN_EAR, vn));
        assertEquals("Hạ Chí", a.getName(SUMMER_SOLSTICE, vn));
        assertEquals("Tiểu Thử", a.getName(MINOR_HEAT, vn));
        assertEquals("Đại Thử", a.getName(MAJOR_HEAT, vn));
        assertEquals("Lập Thu", a.getName(START_OF_AUTUMN, vn));
        assertEquals("Xử Thử", a.getName(LIMIT_OF_HEAT, vn));
        assertEquals("Bạch Lộ", a.getName(WHITE_DEW, vn));
        assertEquals("Thu Phân", a.getName(AUTUMNAL_EQUINOX, vn));
        assertEquals("Hàn Lộ", a.getName(COLD_DEW, vn));
        assertEquals("Sương Giáng", a.getName(FROST_DESCENT, vn));
        assertEquals("Lập Đông", a.getName(START_OF_WINTER, vn));
        assertEquals("Tiểu Tuyết", a.getName(MINOR_SNOW, vn));
        assertEquals("Đại Tuyết", a.getName(MAJOR_SNOW, vn));
        assertEquals("Đông Chí", a.getName(WINTER_SOLSTICE, vn));
        assertEquals("Tiểu Hàn", a.getName(MINOR_COLD, vn));
        assertEquals("Đại Hàn", a.getName(MAJOR_COLD, vn));
    }

    @Test
    void testGetNameEn() {
        SolarTermDisplayNameProvider a = SolarTermDisplayNameProvider.getInstance();
        Locale en = Locale.ENGLISH;
        assertEquals("Rain Water", a.getName(RAIN_WATER, en));
        assertEquals("Awakening of Insects", a.getName(AWAKENING_OF_INSECTS, en));
        assertEquals("Vernal Equinox", a.getName(VERNAL_EQUINOX, en));
        assertEquals("Pure Brightness", a.getName(PURE_BRIGHTNESS, en));
        assertEquals("Grain Rain", a.getName(GRAIN_RAIN, en));
        assertEquals("Start of Summer", a.getName(START_OF_SUMMER, en));
        assertEquals("Grain Buds", a.getName(GRAIN_BUDS, en));
        assertEquals("Grain in Ear", a.getName(GRAIN_IN_EAR, en));
        assertEquals("Summer Solstice", a.getName(SUMMER_SOLSTICE, en));
        assertEquals("Minor Heat", a.getName(MINOR_HEAT, en));
        assertEquals("Major Heat", a.getName(MAJOR_HEAT, en));
        assertEquals("Start of Autumn", a.getName(START_OF_AUTUMN, en));
        assertEquals("Limit of Heat", a.getName(LIMIT_OF_HEAT, en));
        assertEquals("White Dew", a.getName(WHITE_DEW, en));
        assertEquals("Autumnal Equinox", a.getName(AUTUMNAL_EQUINOX, en));
        assertEquals("Cold Dew", a.getName(COLD_DEW, en));
        assertEquals("Frost Descent", a.getName(FROST_DESCENT, en));
        assertEquals("Start of Winter", a.getName(START_OF_WINTER, en));
        assertEquals("Minor Snow", a.getName(MINOR_SNOW, en));
        assertEquals("Major Snow", a.getName(MAJOR_SNOW, en));
        assertEquals("Winter Solstice", a.getName(WINTER_SOLSTICE, en));
        assertEquals("Minor Cold", a.getName(MINOR_COLD, en));
        assertEquals("Major Cold", a.getName(MAJOR_COLD, en));

    }

    @Test
    void testGetNameCn() {
        SolarTermDisplayNameProvider a = SolarTermDisplayNameProvider.getInstance();
        assertThrows(IllegalArgumentException.class, () -> a.getName(START_OF_SPRING, Locale.CHINESE));
    }
}
