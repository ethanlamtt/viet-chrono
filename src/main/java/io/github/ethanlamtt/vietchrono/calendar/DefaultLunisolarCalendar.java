package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.astro.LunarTime;
import io.github.ethanlamtt.vietchrono.astro.Moment;
import io.github.ethanlamtt.vietchrono.astro.SolarTime;
import io.github.ethanlamtt.vietchrono.holiday.HolidayId;
import io.github.ethanlamtt.vietchrono.holiday.HolidayRuleContext;
import io.github.ethanlamtt.vietchrono.holiday.HolidayRules;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryCycle;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryDateTime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static io.github.ethanlamtt.vietchrono.astro.LunarPhase.MEAN_SYNODIC_MONTH;
import static io.github.ethanlamtt.vietchrono.astro.LunarPhase.NEW_MOON;
import static io.github.ethanlamtt.vietchrono.calendar.SolarTerm.WINTER_SOLSTICE;

/**
 * Provides a lunisolar calendar system implementation.
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
final class DefaultLunisolarCalendar implements LunisolarCalendar {

    /**
     * The singleton instance of this class.
     */
    private static final LunisolarCalendar INSTANCE = new DefaultLunisolarCalendar();

    /**
     * Constructs a {@code DefaultLunisolarCalendar}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     */
    private DefaultLunisolarCalendar() {}

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code LunisolarCalendar} instance.
     */
    public static LunisolarCalendar getInstance() {
        return INSTANCE;
    }

    /**
     * Represents for a year frame of an astronomical year.
     */
    private static final class YearFrame {

        /**
         * The anchor year. Used for specifying correctly astronomical year.
         */
        private final int anchorYear;

        /**
         * The new moon of the start November. This is the first month of the astronomical year.
         */
        private final Moment currentNovNewMoon;

        /**
         * The index of a leap month. This is -1 if no leap month exists.
         */
        private final int leapMonthIndex;

        /**
         * The flag check if there's a leap month
         */
        private final boolean hasLeapMonth;

        /**
         * Constructs a {@code YearFrame} with a specified params.
         * @param anchorYear the anchor year
         * @param currentNovNewMoon The new moon of the start November
         * @param leapMonthIndex The index of a leap month
         * @param hasLeapMonth The flag check if there's a leap month
         * @throws NullPointerException if {@code currentNovNewMoon} is null
         */
        YearFrame(int anchorYear, Moment currentNovNewMoon,
                int leapMonthIndex, boolean hasLeapMonth) {
            this.currentNovNewMoon = Objects.requireNonNull(currentNovNewMoon, "startNovNewMoon");
            this.leapMonthIndex = leapMonthIndex;
            this.anchorYear = anchorYear;
            this.hasLeapMonth = hasLeapMonth;
        }

        /**
         * Returns a {@code YearFrame} with a specified params.
         * @param anchorYear the anchor year
         * @param currentNovNewMoon The new moon of the start November
         * @param leapMonthIndex The index of a leap month
         * @param hasLeapMonth The flag check if there's a leap month
         * @return a {@code YearFrame} instance.
         */
        static YearFrame of(int anchorYear, Moment currentNovNewMoon,
                int leapMonthIndex, boolean hasLeapMonth) {
            return new YearFrame(anchorYear, currentNovNewMoon, leapMonthIndex, hasLeapMonth);
        }
    }

    /**
     * Year frames cache
     */
    private static final ConcurrentMap<Integer, YearFrame> YEAR_FRAMES = new ConcurrentHashMap<>();

    /**
     * November new moons cache.
     */
    private static final ConcurrentMap<Integer, Moment> NOVEMBER_NEW_MOONS = new ConcurrentHashMap<>();

    private static final LunarTime LUNAR_TIME = LunarTime.getInstance();
    private static final SolarTime SOLAR_TIME = SolarTime.getInstance();

    @Override
    public String id() {
        return "Default";
    }

    @Override
    public LunisolarDate getDate(LocalDate solarDate, ZoneId zoneId) {
        Objects.requireNonNull(solarDate, "solarDate cannot be null");
        Objects.requireNonNull(zoneId, "zoneId");

        Moment anchor = Moment.ofInstant(
                solarDate.atStartOfDay(zoneId)
                        .toInstant()
        );

        LunarDate lunarDate = getLunarDate(solarDate, zoneId);
        DailySolarTerm dailySolarTerm = getSolarTermInfo(anchor);
        SexagenaryDateTime sexagenaryDateTime = getSexagenaryDateTime(anchor, lunarDate, zoneId);
        List<HolidayId> holidayIds = getHolidayIds(solarDate, lunarDate, dailySolarTerm);

        return LunisolarDate.of(solarDate, lunarDate, dailySolarTerm, sexagenaryDateTime, holidayIds);
    }

    @Override
    public LunarDate getLunarDate(LocalDate solarDate, ZoneId zoneId) {
        int solarYear = solarDate.getYear();
        Moment anchor = Moment.ofInstant(
                solarDate.atStartOfDay(zoneId)
                        .toInstant()
        );
        Moment currentNewMoon = startNewMoonOf(anchor);

        Moment startNovNewMoon = NOVEMBER_NEW_MOONS.computeIfAbsent(solarYear,
                                                      y -> startNewMoonOf(winterSolstice(y)));

        LocalDate currentNewMoonDate = currentNewMoon.atZone(zoneId)
                .toLocalDate();
        LocalDate currentNovNewMoonDate = startNovNewMoon.atZone(zoneId)
                .toLocalDate();

        int expectedSolarYear = currentNewMoonDate.isBefore(currentNovNewMoonDate) ? solarYear - 1 : solarYear;

        YearFrame yearFrame = YEAR_FRAMES.computeIfAbsent(expectedSolarYear, (anchorYear) -> {
            Moment currentNovNewMoon = startNovNewMoon;

            if (anchorYear != solarYear) {
                currentNovNewMoon = startNewMoonOf(winterSolstice(anchorYear));
            }

            Moment nextNovNewMoon = startNewMoonOf(winterSolstice(anchorYear + 1));

            int lunations = lunations(currentNovNewMoon, nextNovNewMoon);
            boolean hasLeapMonth = (lunations == 13);
            int leapMonthIndex = -1;

            if (hasLeapMonth) {
                Moment startNewMoon = currentNovNewMoon;
                for (int i = 0; i < 13; i++) {
                    Moment nextNewMoon = LUNAR_TIME.after(startNewMoon, NEW_MOON);

                    double startNewMoonLongitude = SOLAR_TIME.apparentLongitudeAt(startNewMoon);
                    double nextNewMoonLongitude = SOLAR_TIME.apparentLongitudeAt(nextNewMoon);

                    SolarTerm currentTerm = SolarTerm.majorFrom(startNewMoonLongitude);
                    SolarTerm nextTerm = SolarTerm.majorFrom(nextNewMoonLongitude);

                    if (currentTerm == nextTerm) {
                        leapMonthIndex = i;
                        break;
                    }

                    startNewMoon = nextNewMoon;
                }

                if (leapMonthIndex == -1)
                    throw new IllegalStateException("No leap month found");
            }

            return YearFrame.of(anchorYear, currentNovNewMoon, leapMonthIndex, hasLeapMonth);
        });

        int anchorYear = yearFrame.anchorYear;
        Moment currentNovNewMoon = yearFrame.currentNovNewMoon;
        int leapMonthIndex = yearFrame.leapMonthIndex;
        boolean hasLeapMonth = yearFrame.hasLeapMonth;

        int monthIndex = lunations(currentNovNewMoon, currentNewMoon);

        int month;
        boolean isLeapMonth = false;

        if (!hasLeapMonth || monthIndex < leapMonthIndex)
            month = (monthIndex + 10) % 12 + 1;
        else {
            month = (monthIndex + 9) % 12 + 1;
            if (monthIndex == leapMonthIndex)
                isLeapMonth = true;
        }

        int lunarYear = anchorYear;
        if (month < 11)
            lunarYear++;

        LocalDate anchorDate = anchor.atZone(zoneId)
                .toLocalDate();

        LocalDate newMoonDate = currentNewMoon.atZone(zoneId)
                .toLocalDate();

        int lunarDay = (int) (anchorDate.toEpochDay() - newMoonDate.toEpochDay()) + 1;

        LunarMonth lunarMonth = LunarMonth.of(month, isLeapMonth);

        return LunarDate.of(lunarYear, lunarMonth, lunarDay);
    }

    private int lunations(Moment start, Moment end) {
        double offsetDays = end.toEphemeris().value() - start.toEphemeris().value();
        return (int) Math.round(offsetDays / MEAN_SYNODIC_MONTH);
    }

    private Moment startNewMoonOf(Moment anchor) {
        Moment nextDayAnchor = anchor.plusDays(1);

        return LUNAR_TIME.before(nextDayAnchor, NEW_MOON);
    }

    private Moment winterSolstice(int solarYear) {
        Instant anchorInstant = LocalDate.of(solarYear, 12, 14)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        Moment anchor = Moment.ofInstant(anchorInstant);

        return SOLAR_TIME.atLongitude(WINTER_SOLSTICE.longitude(), anchor);
    }

    private DailySolarTerm getSolarTermInfo(Moment anchor) {
        double currentLongitude = SOLAR_TIME.apparentLongitudeAt(anchor);
        SolarTerm currentTerm = SolarTerm.from(currentLongitude);
        SolarTerm nextTerm = currentTerm.nextTerm();
        Moment transition = SOLAR_TIME.atLongitude(nextTerm.longitude(), anchor);

        if (transition.compareTo(anchor.plusDays(1)) >= 0)
            return DailySolarTerm.of(currentTerm, null);

        return DailySolarTerm.of(nextTerm, transition);
    }

    private SexagenaryDateTime getSexagenaryDateTime(Moment anchor, LunarDate lunarDate, ZoneId zoneId) {
        int year = lunarDate.year();
        int month = lunarDate.monthValue();

        SexagenaryCycle cyclicYear = SexagenaryCycle.ofYear(year);
        SexagenaryCycle cyclicMonth = SexagenaryCycle.ofMonth(cyclicYear, month);
        SexagenaryCycle cyclicDay = SexagenaryCycle.ofDay(anchor, zoneId);
        SexagenaryCycle cyclicHour = SexagenaryCycle.ofHour(cyclicDay);

        return SexagenaryDateTime.of(cyclicYear, cyclicMonth, cyclicDay, cyclicHour);
    }

    private List<HolidayId> getHolidayIds(LocalDate solarDate, LunarDate lunarDate, DailySolarTerm dailySolarTerm) {
        HolidayRuleContext context = HolidayRuleContext.of(solarDate, lunarDate, dailySolarTerm);
        return HolidayRules.getInstance().applyRules(context);
    }

}