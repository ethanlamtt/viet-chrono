package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.calendar.LunarDate;

import java.util.Locale;
import java.util.ServiceLoader;

/**
 * Represents for a lunar date display name provider.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public abstract class LunarDateDisplayNameProvider {

    private static final class Holder {
        private static final LunarDateDisplayNameProvider INSTANCE;

        static {
            ServiceLoader<LunarDateDisplayNameProvider> loader =
                    ServiceLoader.load(LunarDateDisplayNameProvider.class);

            INSTANCE = loader.findFirst()
                    .orElse(new DefaultLunarDateDisplayNameProvider());
        }
    }

    protected LunarDateDisplayNameProvider() {}

    public static LunarDateDisplayNameProvider getInstance() {
        return Holder.INSTANCE;
    }

    public abstract String getDayName(LunarDate lunarDate, Locale locale);
    public abstract String getMonthName(LunarDate lunarDate, Locale locale);

}
