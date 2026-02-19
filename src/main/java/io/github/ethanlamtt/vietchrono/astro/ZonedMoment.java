package io.github.ethanlamtt.vietchrono.astro;

import java.time.*;
import java.util.Objects;

/**
 * Represents for astronomical moment associated with a timezone.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class ZonedMoment {

    /**
     * The astronomical moment.
     */
    private final Moment moment;
    /**
     * The zoneId associated with the moment.
     */
    private final ZoneId zoneId;

    /**
     * Constructs {@code ZonedMoment} instance.
     *
     * @param moment the astronomical moment
     * @param zoneId the zoneId associated with the moment
     * @throws NullPointerException if {@code moment} is null
     * @throws NullPointerException if {@code zoneId} is null
     */
    private ZonedMoment(Moment moment, ZoneId zoneId) {
        this.moment = Objects.requireNonNull(moment, "moment");
        this.zoneId = Objects.requireNonNull(zoneId, "zoneId");
    }

    /**
     * Returns a {@code ZonedMoment} instance associated with a zoneId.
     * @param moment the astronomical moment
     * @param zoneId the zoneId associated with the moment
     * @return a {@code ZonedMoment} instance
     */
    public static ZonedMoment of(Moment moment, ZoneId zoneId) {
        return new ZonedMoment(moment, zoneId);
    }

    /**
     * Obtains the zonedId associated with the moment
     * @return the zoneId associated with the moment
     */
    public ZoneId zoneId() {
        return zoneId;
    }

    /**
     * Converts this instance to a {@link Moment} without timezone.
     * @return the astronomical moment
     */
    public Moment toMoment() {
        return moment;
    }

    /**
     * Converts this instance to a {@link LocalDate}.
     *
     * @return a {@code LocalDate} instance.
     */
    public LocalDate toLocalDate() {
        return moment.toInstant()
                .atZone(zoneId)
                .toLocalDate();
    }

    /**
     * Compares this zoned moment with the specified object for equality.
     *
     * <p>Two {@code ZonedMoment} instances are considered equal if they have
     * the exact same the epoch second.</p>
     *
     * @param o the object to be compared.
     * @return {@code true} if the two objects are equal; otherwise {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof ZonedMoment other))
            return false;

        return moment.equals(other.moment) && zoneId.equals(other.zoneId);
    }

    /**
     * Returns a hash code of this object.
     *
     * @return a hash code of this object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + moment.hashCode();
        result = 31 * result + zoneId.hashCode();
        return result;
    }

    /**
     * Returns a {@code String} view of this object.
     *
     * @return a string view of this object.
     */
    @Override
    public String toString() {
        return String.format("ZonalMoment(moment=%s, zoneId=%s)", moment, zoneId);
    }
}
