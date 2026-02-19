package io.github.ethanlamtt.vietchrono.format;

import io.github.ethanlamtt.vietchrono.sexagenary.EarthlyBranch;
import io.github.ethanlamtt.vietchrono.sexagenary.HeavenlyStem;

import java.util.Locale;
import java.util.ServiceLoader;

/**
 * Represents for a sexagenary display name provider.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public abstract class SexagenaryDisplayNameProvider {

    private static final class Holder {
        private static final SexagenaryDisplayNameProvider INSTANCE;

        static {
            ServiceLoader<SexagenaryDisplayNameProvider> loader =
                    ServiceLoader.load(SexagenaryDisplayNameProvider.class);

            INSTANCE = loader.findFirst()
                    .orElse(new DefaultSexagenaryDisplayNameProvider());
        }
    }

    protected SexagenaryDisplayNameProvider() {}

    public static SexagenaryDisplayNameProvider getInstance() {
        return Holder.INSTANCE;
    }

    public abstract String getStemName(HeavenlyStem stem, Locale locale);
    public abstract String getBranchName(EarthlyBranch branch, Locale locale);
}
