package io.github.ethanlamtt.vietchrono.sexagenary;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.ethanlamtt.vietchrono.sexagenary.FiveElement.*;
import static io.github.ethanlamtt.vietchrono.sexagenary.YinYang.*;

/**
 * Represents for a heavenly stem.
 *
 * <p>A heavenly stem is a combination between {@link YinYang} and {@link FiveElement}</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public enum HeavenlyStem {

    YANG_WOOD(0, YANG, WOOD),
    YIN_WOOD(1, YIN, WOOD),
    YANG_FIRE(2, YANG, FIRE),
    YIN_FIRE(3, YIN, FIRE),
    YANG_EARTH(4, YANG, EARTH),
    YIN_EARTH(5, YIN, EARTH),
    YANG_METAL(6 ,YANG, METAL),
    YIN_METAL(7, YIN, METAL),
    YANG_WATER(8, YANG, WATER),
    YIN_WATER(9, YIN, WATER);

    /**
     * Caches earthly branches by their indexes.
     */
    private static final Map<Integer, HeavenlyStem> BY_INDEX = Arrays.stream(values())
            .collect(Collectors.toMap(
                    s -> s.index,
                    s -> s
            ));

    /**
     * The index of the early branch.
     */
    private final int index;

    /**
     * The yin yang component.
     */
    private final YinYang yinYang;

    /**
     * The five element component.
     */
    private final FiveElement element;

    /**
     * Constructs an {@code HeavenlyStem} with specified index, yin yang and five element.
     *
     * @param index the index of this heavenly stem
     * @param yinYang the yin yang component
     * @param element the five element component
     */
    HeavenlyStem(int index, YinYang yinYang, FiveElement element) {
        this.index = index;
        this.yinYang = yinYang;
        this.element = element;
    }

    /**
     * Returns an {@code HeavenlyStem} with a specified index.
     * @param index the index of the heavenly stem
     * @return a heavenly stem
     * @throws IllegalArgumentException if no heavenly stem found
     */
    public static HeavenlyStem ofIndex(int index) {
        HeavenlyStem stem = BY_INDEX.get(index);
        if (stem != null)
            return stem;

        throw new IllegalArgumentException("No heavenly stem found with index: " + index);
    }

    /**
     * Obtains the index of the heavenly stem.
     * @return the index of this instance
     */
    public int index() {
        return index;
    }
}
