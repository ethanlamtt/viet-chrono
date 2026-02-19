package io.github.ethanlamtt.vietchrono;

import io.github.ethanlamtt.vietchrono.calendar.LunisolarCalendar;
import io.github.ethanlamtt.vietchrono.calendar.LunisolarCalendars;
import io.github.ethanlamtt.vietchrono.calendar.LunisolarDate;
import io.github.ethanlamtt.vietchrono.format.LunisolarDateViewFormatter;
import io.github.ethanlamtt.vietchrono.format.views.LunisolarDateView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

/**
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
class Main {
    public static void main(String[] args) {
        LocalDate[] solarDates = {
                LocalDate.of(2025, 7, 24),
                LocalDate.of(2027, 2, 8),
                LocalDate.of(2027, 2, 9),
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 11),
                LocalDate.of(2027, 2, 12),
                LocalDate.of(2027, 2, 13),
                LocalDate.of(2027, 2, 14),
                LocalDate.of(2027, 2, 15),
                LocalDate.of(2027, 2, 16),
                LocalDate.of(2027, 2, 17),
                LocalDate.of(2027, 9, 25),
                LocalDate.of(2028, 2, 26),
                LocalDate.of(2029, 12, 31),
                LocalDate.of(2030, 2, 19),

                LocalDate.of(2025, 7, 25),
                LocalDate.of(2024, 2, 4),
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2026, 6, 19),
                LocalDate.of(2026, 11, 19),
                LocalDate.of(2026, 9, 13),
                LocalDate.of(2026, 4, 5),
                LocalDate.of(2026, 5, 19),
                LocalDate.of(2026, 5, 5),
                LocalDate.of(2026, 4, 7),
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 9)
        };

        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        Locale locale = Locale.of("vi", "VN");
        LunisolarCalendar calendar = LunisolarCalendars.ofDefault();

        for (LocalDate solarDate : solarDates) {
            LunisolarDate lunisolarDate = calendar.getDate(solarDate, zoneId);
            LunisolarDateView view = LunisolarDateViewFormatter.format(lunisolarDate, zoneId, locale);
            System.out.println(view);
        }
    }
}
