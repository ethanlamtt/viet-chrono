package io.github.ethanlamtt.vietchrono.format;

import java.util.Locale;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Represents for a holiday display name provider implementation.
 *
 * @author ethanlamtt
 * @since 1.0
 */
final class DefaultHolidayDisplayNameProvider extends HolidayDisplayNameProvider {

        private static final Map<String, String> VIETNAMESE = Map.ofEntries(
            entry("New_Years_Day", "Tết Dương lịch"),
            entry("April_Fools_Day", "Ngày Cá tháng Tư"),
            entry("Valentines_Day", "Lễ Tình nhân (Valentine)"),
            entry("International_Womens_Day", "Quốc tế Phụ nữ"),
            entry("Liberation_Day", "Ngày Giải phóng miền Nam"),
            entry("International_Workers_Day", "Quốc tế Lao động"),
            entry("President_Ho_Chi_Minh_Birthday", "Ngày sinh Chủ tịch Hồ Chí Minh"),
            entry("Vietnam_National_Day", "Quốc khánh Việt Nam"),
            entry("Vietnamese_Womens_Day", "Ngày Phụ nữ Việt Nam"),
            entry("Halloween", "Lễ hội hóa trang (Halloween)"),
            entry("International_Mens_Day", "Ngày Quốc tế Nam giới"),
            entry("World_Toilet_Day", "Ngày Nhà vệ sinh Thế giới"),
            entry("Vietnamese_Teachers_Day", "Ngày Nhà giáo Việt Nam"),
            entry("Christmas", "Lễ Giáng sinh"),
            entry("New_Years_Eve", "Giao thừa"),

            entry("Lunar_New_Year", "Tết Nguyên Đán"),
            entry("Lantern_Festival", "Tết Nguyên Tiêu"),
            entry("Hung_King_Festival", "Giỗ tổ Hùng Vương"),
            entry("Vesak_Day", "Lễ Phật Đản"),
            entry("Mid_Year_Festival", "Tết Đoan Ngọ"),
            entry("Vu_Lan_Festival", "Lễ Vu Lan"),
            entry("Mid_Autumn", "Tết Trung Thu"),
            entry("Kitchen_Gods_Day", "Tết ông Công ông Táo"),

            entry("Programmers_Day", "Ngày Lập trình viên"),
            entry("Black_Friday", "Thứ Sáu Đen"),
            entry("Friday_The_13th", "Thứ Sáu ngày 13"),
            entry("Tomb_Sweeping_Day", "Tết Thanh Minh")
    );

    private static final Map<String, String> ENGLISH = Map.ofEntries(
            entry("New_Years_Day", "New Year's Day"),
            entry("April_Fools_Day", "April Fool's Day"),
            entry("Valentines_Day", "Valentine's Day"),
            entry("International_Womens_Day", "International Women's Day"),
            entry("Liberation_Day", "Reunification Day"),
            entry("International_Workers_Day", "International Workers' Day"),
            entry("President_Ho_Chi_Minh_Birthday", "President Ho Chi Minh's Birthday"),
            entry("Vietnam_National_Day", "Vietnam National Day"),
            entry("Vietnamese_Womens_Day", "Vietnamese Women's Day"),
            entry("Halloween", "Halloween"),
            entry("International_Mens_Day", "International Men's Day"),
            entry("World_Toilet_Day", "World Toilet Day"),
            entry("Vietnamese_Teachers_Day", "Vietnamese Teachers' Day"),
            entry("Christmas", "Christmas Day"),
            entry("New_Years_Eve", "New Year's Eve"),

            entry("Lunar_New_Year", "Lunar New Year"),
            entry("Lantern_Festival", "Lantern Festival"),
            entry("Hung_King_Festival", "Hung Kings' Commemoration Day"),
            entry("Vesak_Day", "Vesak Day"),
            entry("Mid_Year_Festival", "Mid-year Festival"),
            entry("Vu_Lan_Festival", "Vu Lan Festival"),
            entry("Mid_Autumn", "Mid-Autumn Festival"),
            entry("Kitchen_Gods_Day", "Kitchen Guardians' Day"),

            entry("Programmers_Day", "Programmers' Day"),
            entry("Black_Friday", "Black Friday"),
            entry("Friday_The_13th", "Friday the 13th"),
            entry("Tomb_Sweeping_Day", "Tomb Sweeping Day")
    );

    private static final Map<Locale, Map<String, String>> HOLIDAYS = Map.of(
            Locale.of("vi"), VIETNAMESE,
            Locale.of("en"), ENGLISH
    );

    public DefaultHolidayDisplayNameProvider() {
    }

    @Override
    public String getName(String holidayId, Locale locale) {

        Map<String, String> holidays = HOLIDAYS.get(locale);

        if (holidays == null) {
            holidays = HOLIDAYS.get(Locale.of(locale.getLanguage()));
            if (holidays == null)
                throw new IllegalArgumentException("Unsupported locale: " + locale);
        }

        if (!holidays.containsKey(holidayId))
            throw new IllegalStateException("Invalid holiday ID: " + holidayId);

        return holidays.get(holidayId);
    }
}
