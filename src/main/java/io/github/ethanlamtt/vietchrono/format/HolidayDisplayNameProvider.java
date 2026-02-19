package io.github.ethanlamtt.vietchrono.format;

import java.util.Locale;
import java.util.ServiceLoader;

/**
 * Represents for a holiday display name provider.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public abstract class HolidayDisplayNameProvider {

    private static final class Holder {
        private static final HolidayDisplayNameProvider INSTANCE;

        static {
            ServiceLoader<HolidayDisplayNameProvider> loader =
                    ServiceLoader.load(HolidayDisplayNameProvider.class);

            INSTANCE = loader.findFirst()
                    .orElse(new DefaultHolidayDisplayNameProvider());
        }
    }

    protected HolidayDisplayNameProvider() {}

    public static HolidayDisplayNameProvider getInstance() {
        return Holder.INSTANCE;
    }

    public abstract String getName(String holidayId, Locale locale);
}
