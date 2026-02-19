package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.astro.Moment;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents for a solar term of a day.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class DailySolarTerm {

    /**
     * The term of the day.
     */
    private final SolarTerm termOfDay;

    /**
     * The transition moment to the this {@code termOfDay}
     */
    private final Moment transition;

    private DailySolarTerm(SolarTerm termOfDay, Moment transition) {
        this.termOfDay = Objects.requireNonNull(termOfDay, "termOfDay cannot be null");
        this.transition = transition;
    }

    public static DailySolarTerm of(SolarTerm termOfDay, Moment transition) {
        return new DailySolarTerm(termOfDay, transition);
    }

    public SolarTerm termOfDay() {
        return termOfDay;
    }

    public Optional<Moment> transition() {
        return Optional.ofNullable(transition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof DailySolarTerm other))
            return false;

        return termOfDay == other.termOfDay && Objects.equals(this.transition, other.transition);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + termOfDay.hashCode();
        result = 31 * result + transition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("DailySolarTerm(termOfDay=%s, transition=%s)", termOfDay, transition);
    }
}
