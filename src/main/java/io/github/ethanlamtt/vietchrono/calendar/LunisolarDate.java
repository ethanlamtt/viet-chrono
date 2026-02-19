package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.holiday.HolidayId;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryDateTime;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents for a lunisolar date.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class LunisolarDate implements Comparable<LunisolarDate> {

    private static final Comparator<LunisolarDate> NATURAL_ORDER = Comparator
            .comparing((LunisolarDate d) -> d.solarDate)
            .thenComparing(d -> d.lunarDate);

    private final LocalDate solarDate;
    private final LunarDate lunarDate;
    private final DailySolarTerm dailySolarTerm;
    private final SexagenaryDateTime sexagenaryDateTime;
    private final List<HolidayId> holidayIds;

    private LunisolarDate(LocalDate solarDate, LunarDate lunarDate,
                          DailySolarTerm dailySolarTerm, SexagenaryDateTime sexagenaryDateTime,
                          List<HolidayId> holidayIds) {
        this.solarDate = Objects.requireNonNull(solarDate, "solarDate");
        this.lunarDate = Objects.requireNonNull(lunarDate, "lunarDate");
        this.dailySolarTerm = Objects.requireNonNull(dailySolarTerm, "dailySolarTerm");
        this.sexagenaryDateTime = Objects.requireNonNull(sexagenaryDateTime, "sexagenaryDateTime");
        this.holidayIds = List.copyOf(Objects.requireNonNull(holidayIds, "holidayIds"));
    }

    public static LunisolarDate of(LocalDate solarDate, LunarDate lunarDate,
                                   DailySolarTerm dailySolarTerm, SexagenaryDateTime sexagenaryDateTime,
                                   List<HolidayId> holidayIds) {
        return new LunisolarDate(solarDate, lunarDate, dailySolarTerm, sexagenaryDateTime, holidayIds);
    }

    public LocalDate toSolarDate() {
        return solarDate;
    }

    public LunarDate toLunarDate() {
        return lunarDate;
    }

    public SexagenaryDateTime toSexagenaryDateTime() {
        return sexagenaryDateTime;
    }

    public DailySolarTerm solarTermInfo() {
        return dailySolarTerm;
    }

    public List<HolidayId> holidayIds() {
        return holidayIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof LunisolarDate other))
            return false;

        return solarDate.equals(other.solarDate) && lunarDate.equals(other.lunarDate);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + solarDate.hashCode();
        result = 31 * result + lunarDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("LunisolarDate(solarDate=%s, lunarDate=%s, dailySolarTerm=%s, sexagenaryDateTime=%s)",
                             solarDate, lunarDate, dailySolarTerm, sexagenaryDateTime);
    }

    @Override
    public int compareTo(LunisolarDate other) {
        return NATURAL_ORDER.compare(this, other);
    }

}
