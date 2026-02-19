package io.github.ethanlamtt.vietchrono.astro;

import io.github.ethanlamtt.vietchrono.scale.DeltaT;
import io.github.ethanlamtt.vietchrono.scale.TimeScale;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

import static io.github.ethanlamtt.vietchrono.astro.JulianEpoch.UNIX;
import static io.github.ethanlamtt.vietchrono.scale.TimeScale.TT;

/**
 * Represents for astronomical moment.
 *
 * <p>This class works as an {@code Instant} in {@code java.time}, it holds the number of seconds
 * from Unix epoch, and it can be converted to {@link JulianDay} for astronomical calculations and
 * to {@link Instant} for calendrical events. Using this class for almost astronomical API contracts.</p>
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class Moment implements Comparable<Moment> {

    /**
     * Number of seconds per days.
     */
    private static final double DAY_IN_SECONDS = 86400.0;

    /**
     * DeltaT computation instance. Used to obtain the deltaT value, the different between
     * Terrestrial Time (TT) and Universal Time (UT).
     */
    private static final DeltaT DELTA_T = DeltaT.getInstance();

    /**
     * Number of seconds from Unix epoch (1970-1-1 00:00 UTC)
     */
    private final long epochSecond;


    /**
     * Constructs a {@code Moment} with the specified seconds from Unix epoch.
     *
     * <p>This constructor is private to prevent the initialization directly.</p>
     * @param epochSecond the number of seconds from Unix epoch.
     */
    private Moment(long epochSecond) {
        this.epochSecond = epochSecond;
    }

    /**
     * Returns a Moment with the specified seconds from Unix epoch.
     *
     * @param epochSecond the number of seconds from Unix epoch.
     * @return a {@code JulianDay} instance.
     */
    public static Moment of(long epochSecond) {
        return new Moment(epochSecond);
    }

    /**
     * Returns a Moment with the specified {@link Instant}.
     *
     * @param instant the number of seconds from Unix epoch.
     * @return a {@code Moment} instance.
     * @throws NullPointerException if {@code instant} is null.
     */
    public static Moment ofInstant(Instant instant) {
        Objects.requireNonNull(instant, "instant");

        return of(instant.getEpochSecond());
    }

    /**
     * Returns a Moment with the specified {@link JulianDay}.
     *
     * <p>If the specified {@code JulianDay} in scale TT, this method convert it to scale UTC.</p>
     *
     * @param julianDay the {@code JulianDay} instance.
     * @return a {@code Moment} from a {@code JulianDay}.
     * @throws NullPointerException if {@code julianDay} is null.
     */
    public static Moment ofJulianDay(JulianDay julianDay) {
        Objects.requireNonNull(julianDay, "julianDay");

        TimeScale scale = julianDay.timescale();

        double utcValue = julianDay.value();

        if (scale == TT) {
            double offsetSeconds = DELTA_T.atJulianDay(julianDay.value());
            utcValue -= offsetSeconds / DAY_IN_SECONDS;
        }

        double daysSinceEpoch = utcValue - UNIX.value();
        double secondsSinceEpoch = daysSinceEpoch * DAY_IN_SECONDS;

        long epochSecond = (long) Math.floor(secondsSinceEpoch);

        return of(epochSecond);
    }

    /**
     * Obtains the seconds from Unix epoch.
     * @return the {@code epochSecond} of this instance.
     */
    public long value() {
        return epochSecond;
    }

    /**
     * Returns a moment with the seconds added.
     * @param secondsToPlus seconds to add.
     * @return a moment with the seconds added.
     */
    public Moment plusSeconds(long secondsToPlus) {
        return Moment.of(value() + secondsToPlus);
    }

    /**
     * Returns a moment with the seconds subtracted.
     * @param secondsToMinus seconds to subtract.
     * @return a moment with the seconds subtracted.
     */
    public Moment minusSeconds(long secondsToMinus) {
        return Moment.of(value() - secondsToMinus);
    }

    /**
     * Returns a moment with the days added.
     * @param daysToPlus days to add.
     * @return a moment with the days added.
     */
    public Moment plusDays(long daysToPlus) {
        return plusSeconds(daysToPlus * (long) DAY_IN_SECONDS);
    }

    /**
     * Returns a moment with the days subtracted.
     * @param daysToMinus days to subtract.
     * @return a moment with the days subtracted.
     */
    public Moment minusDays(long daysToMinus) {
        return minusSeconds(daysToMinus * (long) DAY_IN_SECONDS);
    }


    /**
     * Converts this instance to a {@link ZonedMoment} representation.
     * <p>This equivalent to {@code ZonedMoment.of(this, zoneId)}</p>
     *
     * @param zoneId zoneId related to this moment.
     * @return a {@code ZonedMoment} instance representing a moment related to a timezone.
     * @see ZonedMoment#of(Moment, ZoneId)
     */
    public ZonedMoment atZone(ZoneId zoneId) {
        return ZonedMoment.of(this, zoneId);
    }

    /**
     * Converts this instance to a {@link Instant} representation.
     *
     * <p>This converts and delegates calendarial calculations to {@code Instant}</p>.
     *
     * @return a {@code Instant} instance.
     * @see Instant#ofEpochSecond(long) 
     */
    public Instant toInstant() {
        return Instant.ofEpochSecond(epochSecond);
    }

    /**
     * Converts this instance to a {@link JulianDay} representation in TT time scale.
     *
     * @return a {@code JulianDay} instance in TT time scale..
     * @see JulianDay#ofEphemeris(double)
     */
    public JulianDay toEphemeris() {
        Instant instant = toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneOffset.UTC);
        double offsetSeconds = DELTA_T.atZonedDateTime(zdt);
        double jdTT = UNIX.value() + ((epochSecond + offsetSeconds) / DAY_IN_SECONDS);

        return JulianDay.ofEphemeris(jdTT);
    }

    /**
     * Converts this instance to a {@link JulianDay} representation in UT time scale.
     *
     * <p>Because of {@code |UT - UTC| <= 0.9s}. We consider UTC as equivalent to UT scale</p>
     *
     * @return a {@code JulianDay} instance in UTC time scale..
     * @see JulianDay#ofUniversal(double)
     */
    public JulianDay toUniversal() {
        double jd = UNIX.value() + (epochSecond / DAY_IN_SECONDS);

        return JulianDay.ofUniversal(jd);
    }

    /**
     * Compares this moment with the specified object for equality.
     *
     * <p>Two {@code Moment} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Moment other))
            return false;

        return this.epochSecond == other.epochSecond;
    }

    /**
     * Returns a hash code of this object. The hash code value is computed on the epoch second.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        return Long.hashCode(epochSecond);
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("Moment(epochSecond=%d)", epochSecond);
    }

    /**
     * Compares two {@code Moment} instances.
     *
     * @param other the other {@code Moment} to compare
     * @return the value {@code 0} if {@code x == y}; a value less
     *         than {@code 0} if {@code x < y} as unsigned values; and
     *         a value greater than {@code 0} if {@code x > y} as
     *         unsigned values
     */
    @Override
    public int compareTo(Moment other) {
        return Long.compare(epochSecond, other.epochSecond);
    }
}
