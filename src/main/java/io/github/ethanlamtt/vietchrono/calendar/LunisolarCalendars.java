package io.github.ethanlamtt.vietchrono.calendar;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * Represents for a lunisolar calendar system factory.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class LunisolarCalendars {

    private static final Map<String, LunisolarCalendar> CALENDARS;

    static {
        Map<String, LunisolarCalendar> map = new HashMap<>();

        LunisolarCalendar defaultCalendar = DefaultLunisolarCalendar.getInstance();
        map.put(defaultCalendar.id(), defaultCalendar);

        ServiceLoader<LunisolarCalendar> loader =
                ServiceLoader.load(LunisolarCalendar.class);

        for (LunisolarCalendar calendar : loader) {
            String id = calendar.id();
            if (map.putIfAbsent(id, calendar) != null) {
                throw new IllegalStateException("Duplicate LunisolarCalendar with ID: " + id);
            }
        }

        CALENDARS = Map.copyOf(map);
    }

    public static LunisolarCalendar of(String id) {
        LunisolarCalendar calendar = CALENDARS.get(id);
        if (calendar == null)
            throw new IllegalArgumentException("Invalid calendar with ID: " + id);

        return calendar;
    }

    public static LunisolarCalendar ofDefault() {
        return of("Default");
    }

    public static Set<String> availableCalendarIds() {
        return CALENDARS.keySet();
    }
}
