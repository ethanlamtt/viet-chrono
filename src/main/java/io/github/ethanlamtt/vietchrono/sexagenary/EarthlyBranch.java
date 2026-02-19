package io.github.ethanlamtt.vietchrono.sexagenary;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents for an earthly branch.
 *
 * <p>In Chinese culture, WATER_BUFFALO replaced by OX, CAT replaced by RABBIT
 * and PIG is a wild boar</p>
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public enum EarthlyBranch {
    RAT(0),
    WATER_BUFFALO(1),
    TIGER(2),
    CAT(3),
    DRAGON(4),
    SNAKE(5),
    HORSE(6),
    GOAT(7),
    MONKEY(8),
    ROOSTER(9),
    DOG(10),
    PIG(11);

    /**
     * Caches earthly branches by their indexes.
     */
    private static final Map<Integer, EarthlyBranch> BY_INDEX = Arrays.stream(values())
            .collect(Collectors.toMap(
                    s -> s.index,
                    s -> s
            ));

    /**
     * The index of the early branch.
     */
    private final int index;

    /**
     * Constructs an {@code EarlyBranch} with a specified index.
     *
     * @param index the index of the early branch
     */
    EarthlyBranch(int index) {
        this.index = index;
    }

    /**
     * Returns an {@code EarlyBranch} with a specified index.
     * @param index the index of the early branch
     * @return an early branch
     * @throws IllegalArgumentException if no early branch found
     */
    public static EarthlyBranch ofIndex(int index) {
        EarthlyBranch branch = BY_INDEX.get(index);

        if (branch != null)
            return branch;

        throw new IllegalArgumentException("No earthly branch found with index: " + index);
    }

    /**
     * Obtains the index of the early branch.
     * @return the index of this instance
     */
    public int index() {
        return index;
    }
}
