package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.calendar.SolarTerm;

import java.util.Locale;
import java.util.ServiceLoader;

/**
 * Represents for a solar term display name provider.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public abstract class SolarTermDisplayNameProvider {

    private static final class Holder {
        private static final SolarTermDisplayNameProvider INSTANCE;

        static {
            ServiceLoader<SolarTermDisplayNameProvider> loader =
                    ServiceLoader.load(SolarTermDisplayNameProvider.class);

            INSTANCE = loader.findFirst()
                    .orElse(new DefaultSolarTermDisplayNameProvider());
        }
    }

    protected SolarTermDisplayNameProvider() {}

    public static SolarTermDisplayNameProvider getInstance() {
        return Holder.INSTANCE;
    }

    public abstract String getName(SolarTerm solarTerm, Locale locale);
}
