package io.github.ethanlamtt.vietchrono.sexagenary;

import io.github.ethanlamtt.vietchrono.astro.Moment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Represents for a sexagenary cycle.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class SexagenaryCycle {

    /**
     * Heavenly stem component.
     */
    private final HeavenlyStem stem;

    /**
     * Early branch component.
     */
    private final EarthlyBranch branch;

    /**
     * Constructs an {@code EarlyBranch} with a specified stem, branch.
     *
     * @param stem the heavenly stem component
     * @param branch the early branch component
     * @throws NullPointerException if {@code stem} is  null
     * @throws NullPointerException if {@code branch} is  null
     */
    private SexagenaryCycle(HeavenlyStem stem, EarthlyBranch branch) {
        this.stem = Objects.requireNonNull(stem, "stem");
        this.branch = Objects.requireNonNull(branch, "branch");
    }

    /**
     * Returns an {@code SexagenaryCycle} with the specified double hour and auspicious star.
     *
     * @param stem the heavenly stem component
     * @param branch the early branch component
     * @return an {@code SexagenaryCycle} instance
     */
    public static SexagenaryCycle of(HeavenlyStem stem, EarthlyBranch branch) {
        return new SexagenaryCycle(stem, branch);
    }

    /**
     * Returns an {@code SexagenaryCycle} of a day.
     *
     * <p>A sexagenary cycle day depends on the anchor day. In order to specify it, we need to know the
     * historical anchor.</p>
     *
     * @param moment the moment
     * @param zoneId the zoneId
     * @return an {@code SexagenaryCycle} instance
     */
    public static SexagenaryCycle ofDay(Moment moment, ZoneId zoneId) {
        LocalDate anchorDate = LocalDate.of(1970, 1, 1);

        int stemOffset = HeavenlyStem.YIN_METAL.index();
        int branchOffset = EarthlyBranch.SNAKE.index();

        ZonedDateTime targetDate = moment.toInstant()
                .atZone(zoneId);

        long days = ChronoUnit.DAYS.between(anchorDate, targetDate);

        int stemIndex = (int) (days + stemOffset) % 10;
        int branchIndex = (int) (days + branchOffset) % 12;

        HeavenlyStem stem = HeavenlyStem.ofIndex(stemIndex);
        EarthlyBranch branch = EarthlyBranch.ofIndex(branchIndex);

        return SexagenaryCycle.of(stem, branch);
    }

    /**
     * Returns an {@code SexagenaryCycle} of a year.
     *
     * <p>A sexagenary cycle year depends on the anchor year. In order to specify it, we need to know the
     * historical anchor.</p>
     *
     * @param year the year
     * @return an {@code SexagenaryCycle} instance
     */
    public static SexagenaryCycle ofYear(int year) {
        LocalDate anchorDate = LocalDate.of(1970, 1, 1);

        int stemOffset = HeavenlyStem.YANG_METAL.index();
        int branchOffset = EarthlyBranch.DOG.index();

        long years = ChronoUnit.YEARS.between(anchorDate, LocalDate.of(year, 1, 1));

        int stemIndex = (int) ((years + stemOffset) % 10);
        int branchIndex = (int) ((years + branchOffset) % 12);

        HeavenlyStem stem = HeavenlyStem.ofIndex(stemIndex);
        EarthlyBranch branch = EarthlyBranch.ofIndex(branchIndex);

        return SexagenaryCycle.of(stem, branch);
    }

    /**
     * Returns an {@code SexagenaryCycle} of a month.
     *
     * <p>A sexagenary cycle month depends on the Five Tigers Method - Ngũ Hổ Độn</p>
     *
     * @param cyclicYear the sexagenary cycle of a year
     * @param month the month
     * @return an {@code SexagenaryCycle} instance
     */
    public static SexagenaryCycle ofMonth(SexagenaryCycle cyclicYear, int month) {
        // Starts from YANG_FIRE-TIGER
        int firstStemIndex = HeavenlyStem.YANG_FIRE.index();
        int firstBranchIndex = EarthlyBranch.TIGER.index();

        int firstMonthStemIndex = (cyclicYear.stem().index() * 2 + firstStemIndex) % 10;
        int firstMonthBranchIndex = firstBranchIndex;

        int monthOffset = month - 1;
        int stemIndex = (firstMonthStemIndex + monthOffset) % 10;
        int branchIndex = (firstMonthBranchIndex + monthOffset) % 12;

        HeavenlyStem stem = HeavenlyStem.ofIndex(stemIndex);
        EarthlyBranch branch = EarthlyBranch.ofIndex(branchIndex);

        return SexagenaryCycle.of(stem, branch);
    }

    /**
     * Returns an {@code SexagenaryCycle} of an hour.
     *
     * <p>A sexagenary cycle month depends on the Five Rats Method - Ngũ Thử Độn</p>
     *
     * @param cyclicDay the sexagenary cycle of a day
     * @return an {@code SexagenaryCycle} instance
     */
    public static SexagenaryCycle ofHour(SexagenaryCycle cyclicDay) {
        // Starts from YANG_WOOD-RAT
        int firstStemIndex = HeavenlyStem.YANG_WOOD.index();
        int firstBranchIndex = EarthlyBranch.RAT.index();

        int stemIndex = (cyclicDay.stem().index() * 2 + firstStemIndex) % 10;
        int branchIndex = firstBranchIndex;

        HeavenlyStem stem = HeavenlyStem.ofIndex(stemIndex);
        EarthlyBranch branch = EarthlyBranch.ofIndex(branchIndex);

        return SexagenaryCycle.of(stem, branch);
    }

    /**
     * Obtains the heavenly stem of the sexagenary cycle.
     *
     * @return the heavenly stem of this instance
     */
    public HeavenlyStem stem() {
        return stem;
    }

    /**
     * Obtains the early branch of the sexagenary cycle.
     *
     * @return the early branch of this instance
     */
    public EarthlyBranch branch() {
        return branch;
    }

    /**
     * Compares this sexagenary cycle with the specified object for equality.
     *
     * <p>Two {@code SexagenaryCycle} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SexagenaryCycle other))
            return false;

        return stem == other.stem && branch == other.branch;
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + stem.hashCode();
        result = 31 * result + branch.hashCode();
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("%s %s", stem, branch);
    }
}
