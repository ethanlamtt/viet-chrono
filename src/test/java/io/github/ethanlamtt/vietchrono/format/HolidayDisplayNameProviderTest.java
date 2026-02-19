package io.github.ethanlamtt.vietchrono.format;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class HolidayDisplayNameProviderTest {
    @Test
    void testGetInstance() {
        HolidayDisplayNameProvider a = HolidayDisplayNameProvider.getInstance();
        HolidayDisplayNameProvider b = HolidayDisplayNameProvider.getInstance();
        assertNotNull(a);
        assertSame(a, b);
    }

    @Test
    void testGetNameVi() {
        HolidayDisplayNameProvider provider = HolidayDisplayNameProvider.getInstance();
        Locale vn = Locale.of("vi", "VN");

        assertEquals("Tết Dương lịch", provider.getName("New_Years_Day", vn));
        assertEquals("Ngày Cá tháng Tư", provider.getName("April_Fools_Day", vn));
        assertEquals("Lễ Tình nhân (Valentine)", provider.getName("Valentines_Day", vn));
        assertEquals("Quốc tế Phụ nữ", provider.getName("International_Womens_Day", vn));
        assertEquals("Ngày Giải phóng miền Nam", provider.getName("Liberation_Day", vn));
        assertEquals("Quốc tế Lao động", provider.getName("International_Workers_Day", vn));
        assertEquals("Ngày sinh Chủ tịch Hồ Chí Minh", provider.getName("President_Ho_Chi_Minh_Birthday", vn));
        assertEquals("Quốc khánh Việt Nam", provider.getName("Vietnam_National_Day", vn));
        assertEquals("Ngày Phụ nữ Việt Nam", provider.getName("Vietnamese_Womens_Day", vn));
        assertEquals("Lễ hội hóa trang (Halloween)", provider.getName("Halloween", vn));
        assertEquals("Ngày Quốc tế Nam giới", provider.getName("International_Mens_Day", vn));
        assertEquals("Ngày Nhà vệ sinh Thế giới", provider.getName("World_Toilet_Day", vn));
        assertEquals("Ngày Nhà giáo Việt Nam", provider.getName("Vietnamese_Teachers_Day", vn));
        assertEquals("Lễ Giáng sinh", provider.getName("Christmas", vn));
        assertEquals("Giao thừa", provider.getName("New_Years_Eve", vn));

        assertEquals("Tết Nguyên Đán", provider.getName("Lunar_New_Year", vn));
        assertEquals("Tết Nguyên Tiêu", provider.getName("Lantern_Festival", vn));
        assertEquals("Giỗ tổ Hùng Vương", provider.getName("Hung_King_Festival", vn));
        assertEquals("Lễ Phật Đản", provider.getName("Vesak_Day", vn));
        assertEquals("Tết Đoan Ngọ", provider.getName("Mid_Year_Festival", vn));
        assertEquals("Lễ Vu Lan", provider.getName("Vu_Lan_Festival", vn));
        assertEquals("Tết Trung Thu", provider.getName("Mid_Autumn", vn));
        assertEquals("Tết ông Công ông Táo", provider.getName("Kitchen_Gods_Day", vn));

        assertEquals("Ngày Lập trình viên", provider.getName("Programmers_Day", vn));
        assertEquals("Thứ Sáu Đen", provider.getName("Black_Friday", vn));
        assertEquals("Thứ Sáu ngày 13", provider.getName("Friday_The_13th", vn));
        assertEquals("Tết Thanh Minh", provider.getName("Tomb_Sweeping_Day", vn));
    }

    @Test
    void testGetNameEn() {
        HolidayDisplayNameProvider provider = HolidayDisplayNameProvider.getInstance();
        Locale en = Locale.ENGLISH;

        assertEquals("New Year's Day", provider.getName("New_Years_Day", en));
        assertEquals("April Fool's Day", provider.getName("April_Fools_Day", en));
        assertEquals("Valentine's Day", provider.getName("Valentines_Day", en));
        assertEquals("International Women's Day", provider.getName("International_Womens_Day", en));
        assertEquals("Reunification Day", provider.getName("Liberation_Day", en));
        assertEquals("International Workers' Day", provider.getName("International_Workers_Day", en));
        assertEquals("President Ho Chi Minh's Birthday", provider.getName("President_Ho_Chi_Minh_Birthday", en));
        assertEquals("Vietnam National Day", provider.getName("Vietnam_National_Day", en));
        assertEquals("Vietnamese Women's Day", provider.getName("Vietnamese_Womens_Day", en));
        assertEquals("Halloween", provider.getName("Halloween", en));
        assertEquals("International Men's Day", provider.getName("International_Mens_Day", en));
        assertEquals("World Toilet Day", provider.getName("World_Toilet_Day", en));
        assertEquals("Vietnamese Teachers' Day", provider.getName("Vietnamese_Teachers_Day", en));
        assertEquals("Christmas Day", provider.getName("Christmas", en));
        assertEquals("New Year's Eve", provider.getName("New_Years_Eve", en));

        assertEquals("Lunar New Year", provider.getName("Lunar_New_Year", en));
        assertEquals("Lantern Festival", provider.getName("Lantern_Festival", en));
        assertEquals("Hung Kings' Commemoration Day", provider.getName("Hung_King_Festival", en));
        assertEquals("Vesak Day", provider.getName("Vesak_Day", en));
        assertEquals("Mid-year Festival", provider.getName("Mid_Year_Festival", en));
        assertEquals("Vu Lan Festival", provider.getName("Vu_Lan_Festival", en));
        assertEquals("Mid-Autumn Festival", provider.getName("Mid_Autumn", en));
        assertEquals("Kitchen Guardians' Day", provider.getName("Kitchen_Gods_Day", en));

        assertEquals("Programmers' Day", provider.getName("Programmers_Day", en));
        assertEquals("Black Friday", provider.getName("Black_Friday", en));
        assertEquals("Friday the 13th", provider.getName("Friday_The_13th", en));
        assertEquals("Tomb Sweeping Day", provider.getName("Tomb_Sweeping_Day", en));
    }

    @Test
    void testGetNameCn() {
        HolidayDisplayNameProvider provider = HolidayDisplayNameProvider.getInstance();
        assertThrows(IllegalArgumentException.class, () -> provider.getName("Tomb_Sweeping_Day", Locale.CHINESE));
    }
}
