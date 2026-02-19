package io.github.ethanlamtt.vietchrono.sexagenary;

import java.util.*;

import static io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch.*;

/**
 * Represents for an auspicious hour.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class AuspiciousHour {

    private static final List<EnumSet<EarthlyBranch>> RULES = List.of(
            EnumSet.of(RAT, WATER_BUFFALO, CAT, HORSE, MONKEY, ROOSTER),
            EnumSet.of(TIGER, CAT, SNAKE, MONKEY, DOG, PIG),
            EnumSet.of(RAT, WATER_BUFFALO, DRAGON, SNAKE, GOAT, DOG),
            EnumSet.of(RAT, TIGER, CAT, HORSE, GOAT, ROOSTER),
            EnumSet.of(TIGER, DRAGON, SNAKE, MONKEY, ROOSTER, PIG),
            EnumSet.of(WATER_BUFFALO, DRAGON, HORSE, GOAT, DOG, PIG)
    );

    /**
     * A double hour which holds the earthly branch, start time, end time of this instance.
     */
    private final DoubleHour doubleHour;

    /**
     * Constructs an {@code AuspiciousHour} with the specified double hour..
     *
     * <p>This constructor is private to prevent the initialization directly.</p>
     * @param doubleHour the double hour
     */
    private AuspiciousHour(DoubleHour doubleHour) {
        this.doubleHour = Objects.requireNonNull(doubleHour, "doubleHour");
    }

    /**
     * Returns an {@code AuspiciousHour} with the specified double hour..
     *
     * @param doubleHour the double hour
     * @return an {@code AuspiciousHour} instance
     */
    public static AuspiciousHour of(DoubleHour doubleHour) {
        return new AuspiciousHour(doubleHour);
    }

    /**
     * Returns a list of {@code AuspiciousHour} with the specified earthly branch of a day.
     *
     * @param dailyBranch the early branch of a day
     * @return a list of {@code AuspiciousHour} instance
     */
    public static List<AuspiciousHour> from(EarthlyBranch dailyBranch) {
        int group = dailyBranch.index() % 6;
        EnumSet<EarthlyBranch> hourBranches = RULES.get(group);
        List<EarthlyBranch> hours = new ArrayList<>(hourBranches);

        List<AuspiciousHour> auspiciousHours = new ArrayList<>(6);

        for (EarthlyBranch hourBranch : hours) {
            DoubleHour doubleHour = DoubleHour.of(hourBranch);
            auspiciousHours.add(of(doubleHour));
        }
        return List.copyOf(auspiciousHours);
    }

    /**
     * Obtains the double hour of this instance.
     * @return the double hour.
     */
    public DoubleHour doubleHour() {
        return doubleHour;
    }

    /**
     * Compares this auspicious star with the specified object for equality.
     *
     * <p>Two {@code AuspiciousHour} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof AuspiciousHour other))
            return false;

        return doubleHour.equals(other.doubleHour);
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        return doubleHour.hashCode();
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("AuspiciousHour(doubleHour=%s)", doubleHour);
    }
}