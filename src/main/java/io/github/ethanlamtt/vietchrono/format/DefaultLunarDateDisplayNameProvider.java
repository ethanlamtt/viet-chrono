package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.calendar.LunarDate;

import java.util.Locale;
import java.util.Map;

/**
 * Represents for a lunar date display name provider implementation.
 *
 * @author ethanlamtt
 * @since 1.0
 */
final class DefaultLunarDateDisplayNameProvider extends LunarDateDisplayNameProvider {

    private static final class LunarDateFormat {
        final String[] months;
        final String leapMonthSuffix;
        final String dayPrefix;
        final String middleDayOfMonth;

        public LunarDateFormat(String[] months, String leapMonthSuffix, String dayPrefix, String middleDayOfMonth) {
            this.months = months;
            this.leapMonthSuffix = leapMonthSuffix;
            this.dayPrefix = dayPrefix;
            this.middleDayOfMonth = middleDayOfMonth;
        }
    }

    private static final String[] VIETNAMESE_MONTHS = new String[]{
            "Tháng Giêng", "Tháng Hai", "Tháng Ba", "Tháng Tư", "Tháng Năm", "Tháng Sáu",
            "Tháng Bảy", "Tháng Tám", "Tháng Chín", "Tháng Mười", "Tháng Mười Một", "Tháng Chạp"
    };

    private static final String[] ENGLISH_MONTHS = new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    private static final LunarDateFormat VIETNAMESE_LUNAR_DATE_FORMAT =
            new LunarDateFormat(
                    VIETNAMESE_MONTHS,
                    "Nhuận",
                    "Mồng",
                    "Rằm"
    );

    private static final LunarDateFormat ENGLISH_LUNAR_DATE_FORMAT =
            new LunarDateFormat(
                    ENGLISH_MONTHS,
                    "Leap",
                    "",
                    ""
            );

    private static final Map<Locale, LunarDateFormat> LUNAR_DATES = Map.of(
            Locale.of("vi"), VIETNAMESE_LUNAR_DATE_FORMAT,
            Locale.of("en"), ENGLISH_LUNAR_DATE_FORMAT
    );

    static {
        for (Map.Entry<Locale, LunarDateFormat> entry : LUNAR_DATES.entrySet()) {
            String[] months = entry.getValue().months;
            if (months.length != 12)
                throw new IllegalStateException("Invalid months length");
        }
    }

    public DefaultLunarDateDisplayNameProvider() {
    }

    @Override
    public String getDayName(LunarDate lunarDate, Locale locale) {
        LunarDateFormat format = getFormat(locale);

        int day = lunarDate.dayOfMonth();
        if (day == 15) {
            if (format.middleDayOfMonth.isEmpty())
                return "" + day;
            return format.middleDayOfMonth;
        }

        if (day >= 1 && day <= 10) {
            if (format.dayPrefix.isEmpty())
                return "" + day;
            return format.dayPrefix + " " + day;
        }

        return String.valueOf(day);
    }

    @Override
    public String getMonthName(LunarDate lunarDate, Locale locale) {
        LunarDateFormat format = getFormat(locale);

        int month = lunarDate.monthValue();

        return format.months[month - 1];
    }

    private LunarDateFormat getFormat(Locale locale) {
        LunarDateFormat format = LUNAR_DATES.get(locale);

        if (format == null) {
            format = LUNAR_DATES.get(Locale.of(locale.getLanguage()));
            if (format == null)
                throw new IllegalArgumentException("Unsupported locale: " + locale);
        }

        return format;
    }
}
