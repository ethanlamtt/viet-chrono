package io.github.ethanlamtt.vietchrono.holiday;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Provides holiday rules.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class HolidayRules {

    private static final HolidayRules INSTANCE;

    static {
        ServiceLoader<HolidayRulesProvider> loader = ServiceLoader.load(HolidayRulesProvider.class);
        HolidayRulesProvider provider = loader.findFirst()
                .orElse(DefaultHolidayRulesProvider.getInstance());
        INSTANCE = new HolidayRules(provider);
    }

    private final HolidayRulesProvider provider;

    private HolidayRules(HolidayRulesProvider provider) {
        this.provider = provider;
    }

    public static HolidayRules getInstance() {
        return INSTANCE;
    }

    public List<HolidayId> applyRules(HolidayRuleContext context) {
        Objects.requireNonNull(context, "context");

        List<HolidayId> ids = new ArrayList<>();

        for (HolidayRule rule : provider.getRules()) {
            if (rule.matches(context)) {
                ids.add(rule.id());
            }
        }

        return ids;
    }
}
