package io.github.ethanlamtt.vietchrono.calendar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.ethanlamtt.vietchrono.astro.AstroUtils.normalizeAngle;

/**
 * Represents for a solar term.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public enum SolarTerm {

    START_OF_SPRING(0, 315),
    RAIN_WATER(1, 330),
    AWAKENING_OF_INSECTS(2, 345),
    VERNAL_EQUINOX(3, 0),
    PURE_BRIGHTNESS(4, 15),
    GRAIN_RAIN(5, 30),
    START_OF_SUMMER(6, 45),
    GRAIN_BUDS(7, 60),
    GRAIN_IN_EAR(8, 75),
    SUMMER_SOLSTICE(9, 90),
    MINOR_HEAT(10, 105),
    MAJOR_HEAT(11, 120),
    START_OF_AUTUMN(12, 135),
    LIMIT_OF_HEAT(13, 150),
    WHITE_DEW(14, 165),
    AUTUMNAL_EQUINOX(15, 180),
    COLD_DEW(16, 195),
    FROST_DESCENT(17, 210),
    START_OF_WINTER(18, 225),
    MINOR_SNOW(19, 240),
    MAJOR_SNOW(20, 255),
    WINTER_SOLSTICE(21, 270),
    MINOR_COLD(22, 285),
    MAJOR_COLD(23, 300);

    public static final Comparator<SolarTerm> NATURAL_ORDER =
            Comparator.comparingInt(SolarTerm::index);
    public static final Comparator<SolarTerm> BY_LONGITUDE =
            Comparator.comparingInt(SolarTerm::longitude);

    private static final Map<Integer, SolarTerm> CACHE_BY_LONGITUDE = Arrays.stream(values())
            .collect(Collectors.toMap(
                    s -> s.longitude,
                    s -> s
            ));

    private final int index;
    private final int longitude;

    SolarTerm(int index, int longitude) {
        if (index < 0 || index > 23)
            throw new IllegalArgumentException("Invalid index: " + longitude);
        if (longitude < 0 || longitude >= 360 || longitude % 15 != 0) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }
        this.index = index;
        this.longitude = longitude;
    }

    public static SolarTerm ofLongitude(int longitude) {
        SolarTerm solarTerm = CACHE_BY_LONGITUDE.get(longitude);
        if (solarTerm == null)
            throw new IllegalArgumentException("No term found with longitude: " + longitude);
        return solarTerm;
    }

    public static SolarTerm from(double longitude) {
        int termLongitude = ((int) normalizeAngle(longitude) / 15) * 15;
        return ofLongitude(termLongitude);
    }

    public static SolarTerm majorFrom(double longitude) {
        int termLongitude = ((int) normalizeAngle(longitude) / 30) * 30;
        return ofLongitude(termLongitude);
    }

    public int index() {
        return index;
    }

    public int longitude() {
        return longitude;
    }

    public boolean isMajorTerm() {
        return longitude % 30 == 0;
    }

    public SolarTerm roll(int amount) {
        amount = Math.floorMod(amount, 24);
        int termLongitude = Math.floorMod(longitude + amount * 15, 360);
        return ofLongitude(termLongitude);
    }

    public SolarTerm previousTerm() {
        return roll(-1);
    }

    public SolarTerm nextTerm() {
        return roll(1);
    }

}
