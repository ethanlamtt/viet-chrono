package io.github.ethanlamtt.vietchrono.sexagenary;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents for a double hour.
 *
 * <p>Each sexagenary day has 6 double hour.</p>
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class DoubleHour implements Comparable<DoubleHour> {

    /**
     * the earthly branch of this double hour.
     */
    private final EarthlyBranch branch;

    /**
     * Constructs a {@code DoubleHour} instance with a specified earthly branch.
     *
     * <p>This constructor is private to prevent the initialization directly.</p>
     *
     * @param branch the branch of this double hour
     */
    private DoubleHour(EarthlyBranch branch) {
        this.branch = Objects.requireNonNull(branch, "branch");
    }

    /**
     * Returns a {@code DoubleHour} with the specified earthly branch.
     *
     * @param branch the earthly branch of the double hour
     * @return a {@code DoubleHour} instance
     */
    public static DoubleHour of(EarthlyBranch branch) {
        return new DoubleHour(branch);
    }

    /**
     * Obtains the early branch of the double hour.
     *
     * @return the early branch of this instance
     */
    public EarthlyBranch branch() {
        return branch;
    }

    /**
     * Obtains the start time of this double hour.
     *
     * @return the start time of this double hour
     */
    public LocalTime startTime() {
        int hour = Math.floorMod (branch.index() * 2 - 1, 24);

        return LocalTime.of(hour, 0);
    }

    /**
     * Obtains the end time of this double hour.
     *
     * @return the end time of this double hour
     */
    public LocalTime endTime() {
        int hour = Math.floorMod (branch.index() * 2 + 1, 24);

        return LocalTime.of(hour, 0);
    }

    /**
     * Compares this double hour with the specified object for equality.
     *
     * <p>Two {@code DoubleHour} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof DoubleHour other))
            return false;

        return this.branch.equals(other.branch);
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        return branch.hashCode();
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("%s-%s", startTime(), endTime());
    }

    /**
     * Compares two {@code DoubleHour} instances.
     *
     * @param other the other {@code DoubleHour} to compare
     * @return the value {@code 0} if {@code x == y}; a value less
     *         than {@code 0} if {@code x < y} as unsigned values; and
     *         a value greater than {@code 0} if {@code x > y} as
     *         unsigned values
     */
    @Override
    public int compareTo(DoubleHour other) {
        return Integer.compare(branch.index(), other.branch.index());
    }
}
