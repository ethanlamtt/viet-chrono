package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.calendar.LunarDate;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LunarDateDisplayNameProviderTest {
    @Test
    void testGetInstance() {
        LunarDateDisplayNameProvider a = LunarDateDisplayNameProvider.getInstance();
        LunarDateDisplayNameProvider b = LunarDateDisplayNameProvider.getInstance();
        assertNotNull(a);
        assertSame(a, b);
    }

    @Test
    void getGetNameVi() {
        LunarDateDisplayNameProvider provider = LunarDateDisplayNameProvider.getInstance();
        Locale vn = Locale.of("vi", "VN");
        LunarDate a = LunarDate.of(1970, 1, 1);
        LunarDate b = LunarDate.of(1970, 12, 15);
        assertEquals("Mồng 1", provider.getDayName(a, vn));
        assertEquals("Rằm", provider.getDayName(b, vn));

        assertEquals("Tháng Giêng", provider.getMonthName(a, vn));
        assertEquals("Tháng Chạp", provider.getMonthName(b, vn));
    }

    @Test
    void getGetNameEn() {
        LunarDateDisplayNameProvider provider = LunarDateDisplayNameProvider.getInstance();
        Locale vn = Locale.ENGLISH;
        LunarDate a = LunarDate.of(1970, 1, 1);
        LunarDate b = LunarDate.of(1970, 12, 15);
        assertEquals("1", provider.getDayName(a, vn));
        assertEquals("15", provider.getDayName(b, vn));

        assertEquals("January", provider.getMonthName(a, vn));
        assertEquals("December", provider.getMonthName(b, vn));
    }

    @Test
    void testGetNameCn() {
        LunarDateDisplayNameProvider provider = LunarDateDisplayNameProvider.getInstance();
        LunarDate a = LunarDate.of(1970, 1, 1);
        LunarDate b = LunarDate.of(1970, 12, 15);
        assertThrows(IllegalArgumentException.class, () -> provider.getDayName(a, Locale.CHINESE));
        assertThrows(IllegalArgumentException.class, () -> provider.getMonthName(b, Locale.CHINESE));
    }
}
