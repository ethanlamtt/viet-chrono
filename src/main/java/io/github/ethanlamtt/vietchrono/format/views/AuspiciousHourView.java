package io.github.ethanlamtt.vietchrono.format.views;

/**
 * Represents for an auspicious hour view.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class AuspiciousHourView {
    private final String branch;
    private final String startTime;
    private final String endTime;

    public AuspiciousHourView(String branch, String startTime, String endTime) {
        this.branch = branch;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String branch() {
        return branch;
    }

    public String startTime() {
        return startTime;
    }

    public String endTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return String.format("%s (%s-%s)", branch, startTime, endTime);
    }
}
