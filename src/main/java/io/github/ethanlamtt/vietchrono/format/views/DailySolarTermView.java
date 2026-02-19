package io.github.ethanlamtt.vietchrono.format.views;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Represents for a daily solar term view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class DailySolarTermView {

    private final String termOfDay;
    private final ZonedDateTime transitionMoment;

    public DailySolarTermView(String termOfDay, ZonedDateTime transitionMoment) {
        this.termOfDay = Objects.requireNonNull(termOfDay, "termOfDay cannot be null");
        this.transitionMoment = transitionMoment;
    }

    public String termOfDay() {
        return termOfDay;
    }

    public ZonedDateTime transitionMoment() {
        return transitionMoment;
    }

    @Override
    public String toString() {
        return String.format("%s %s", termOfDay, transitionMoment == null ? "" : "(" + transitionMoment + ")");
    }
}
