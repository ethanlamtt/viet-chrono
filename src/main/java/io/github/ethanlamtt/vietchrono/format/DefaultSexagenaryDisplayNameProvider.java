package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch;
import io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem;

import java.util.Locale;
import java.util.Map;

/**
 * Represents for a sexagenary display name provider implementation.
 *
 * @author ethanlamtt
 * @since 1.0
 */
final class DefaultSexagenaryDisplayNameProvider extends SexagenaryDisplayNameProvider {

    private static class SexagenaryFormat {
        final String[] stems;
        final String[] branches;

        public SexagenaryFormat(String[] stems, String[] branches) {
            this.stems = stems;
            this.branches = branches;
        }
    }

    private static final SexagenaryFormat VIETNAMESE_SEXAGENARY_FORMAT = new SexagenaryFormat(
            new String[] {
                    "Giáp", "Ất", "Bính", "Đinh", "Mậu",
                    "Kỷ", "Canh", "Tân", "Nhâm", "Quý"
            },
            new String[]{
                    "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ",
                    "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"
            }
    );

    private static final SexagenaryFormat ENGLISH_SEXAGENARY_FORMAT = new SexagenaryFormat(
            new String[]{
                    "Yang Wood", "Yin Wood", "Yang Fire", "Yin Fire", "Yang Earth",
                    "Yin Earth", "Yang Metal", "Yin Metal", "Yang Water", "Yin Water"
            },
            new String[]{
                    "Rat", "Water Buffalo", "Tiger", "Cat", "Dragon", "Snake",
                    "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig"
            }
    );

    private static final Map<Locale, SexagenaryFormat> SEXAGENARIES = Map.of(
            Locale.of("vi"), VIETNAMESE_SEXAGENARY_FORMAT,
            Locale.of("en"), ENGLISH_SEXAGENARY_FORMAT
    );

    static {
        int expectedStemLength = HeavenlyStem.values().length;
        int expectedBranchLength = EarthlyBranch.values().length;

        for (Map.Entry<Locale, SexagenaryFormat> entry : SEXAGENARIES.entrySet()) {
            String[] stems = entry.getValue().stems;
            String[] branches = entry.getValue().branches;

            if (stems.length != expectedStemLength)
                throw new IllegalStateException("Invalid stems length");

            if (branches.length != expectedBranchLength)
                throw new IllegalStateException("Invalid branches length");
        }
    }

    public DefaultSexagenaryDisplayNameProvider() {}

    @Override
    public String getStemName(HeavenlyStem stem, Locale locale) {
        return getFormat(locale).stems[stem.index()];
    }

    @Override
    public String getBranchName(EarthlyBranch branch, Locale locale) {
        return getFormat(locale).branches[branch.index()];
    }

    private SexagenaryFormat getFormat(Locale locale) {
        SexagenaryFormat format = SEXAGENARIES.get(locale);

        if (format == null) {
            format = SEXAGENARIES.get(Locale.of(locale.getLanguage()));
            if (format == null)
                throw new IllegalArgumentException("Unsupported locale: " + locale);
        }

        return format;
    }
}
