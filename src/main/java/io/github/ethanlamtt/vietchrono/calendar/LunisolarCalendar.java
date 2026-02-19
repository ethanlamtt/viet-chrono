package io.github.ethanlamtt.vietchrono.calendar;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Represents for a lunisolar calendar system.
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public interface LunisolarCalendar {

    /**
     * Returns an id of this calendar.
     *
     * @return an id of this calendar
     */
    String id();

    /**
     * Returns a lunisolar date at a specified time zone.
     *
     * @param solarDate the reference solar date
     * @param zoneId the reference time zone
     * @return a lunisolar date at a specified time zone.
     */
    LunisolarDate getDate(LocalDate solarDate, ZoneId zoneId);
}
