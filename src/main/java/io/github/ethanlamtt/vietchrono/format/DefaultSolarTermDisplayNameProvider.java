package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.calendar.SolarTerm;

import java.util.*;

/**
 * Represents for a solar term display name provider implementation.
 *
 * @author ethanlamtt
 * @since 1.0
 */
final class DefaultSolarTermDisplayNameProvider extends SolarTermDisplayNameProvider {

    private static final String[] VIETNAMESE = {
            "Lập Xuân", "Vũ Thủy", "Kinh Trập", "Xuân Phân",
            "Thanh Minh", "Cốc Vũ", "Lập Hạ", "Tiểu Mãn",
            "Mang Chủng", "Hạ Chí", "Tiểu Thử", "Đại Thử",
            "Lập Thu", "Xử Thử", "Bạch Lộ", "Thu Phân",
            "Hàn Lộ", "Sương Giáng", "Lập Đông", "Tiểu Tuyết",
            "Đại Tuyết", "Đông Chí", "Tiểu Hàn", "Đại Hàn"
    };

    private static final String[] ENGLISH = {
            "Start of Spring", "Rain Water", "Awakening of Insects", "Vernal Equinox",
            "Pure Brightness", "Grain Rain", "Start of Summer", "Grain Buds",
            "Grain in Ear", "Summer Solstice", "Minor Heat", "Major Heat",
            "Start of Autumn", "Limit of Heat", "White Dew", "Autumnal Equinox",
            "Cold Dew", "Frost Descent", "Start of Winter", "Minor Snow",
            "Major Snow", "Winter Solstice", "Minor Cold", "Major Cold"
    };

    private static final Map<Locale, String[]> SOLAR_TERMS = Map.of(
            Locale.of("vi"), VIETNAMESE,
            Locale.of("en"), ENGLISH
    );

    static {
        int expectedLength = SolarTerm.values().length;

        for (Map.Entry<Locale, String[]> entry : SOLAR_TERMS.entrySet()) {
            if (entry.getValue().length != expectedLength)
                throw new IllegalStateException("Invalid solar term length");
        }
    }


    @Override
    public String getName(SolarTerm solarTerm, Locale locale) {
        String[] texts = SOLAR_TERMS.get(locale);

        if (texts == null) {
            texts = SOLAR_TERMS.get(Locale.of(locale.getLanguage()));
            if (texts == null)
                throw new IllegalArgumentException("Unsupported locale: " + locale);
        }

        return texts[solarTerm.index()];
    }
}
