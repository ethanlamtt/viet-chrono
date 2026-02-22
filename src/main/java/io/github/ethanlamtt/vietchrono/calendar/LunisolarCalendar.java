package io.github.ethanlamtt.vietchrono.calendar;

import io.github.ethanlamtt.vietchrono.astro.Moment;
import io.github.ethanlamtt.vietchrono.holiday.HolidayId;
import io.github.ethanlamtt.vietchrono.sexagenary.SexagenaryDateTime;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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

    default LunarDate getLunarDate(LocalDate solarDate, ZoneId zoneId) {
        throw new UnsupportedOperationException();
    }
}
