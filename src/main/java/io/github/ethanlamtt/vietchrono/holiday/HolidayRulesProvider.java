package io.github.ethanlamtt.vietchrono.holiday;

import java.util.List;

/**
 * Provides holiday rules provider.
 *
 * @author ethanlamtt
 * @since 1.0
 */
public interface HolidayRulesProvider {
    List<HolidayRule> getRules();
}
