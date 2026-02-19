package io.github.ethanlamtt.vietchrono.holiday;


import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents for a holiday rule.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class HolidayRule {

    private final HolidayId id;
    private final Predicate<HolidayRuleContext> predicate;

    private HolidayRule(HolidayId id,
                        Predicate<HolidayRuleContext> predicate) {
        this.id = Objects.requireNonNull(id);
        this.predicate = Objects.requireNonNull(predicate);
    }

    public static HolidayRule of(HolidayId id,
                                 Predicate<HolidayRuleContext> predicate) {
        return new HolidayRule(id, predicate);
    }

    public static HolidayRule fixedSolar(String id, int month, int day) {
        return new HolidayRule(
                HolidayId.of(id, HolidayType.SOLAR),
                context -> context.solarMonth() == month && context.solarDay() == day
        );
    }

    public static HolidayRule fixedLunar(String id, int month, int day) {
        return new HolidayRule(
                HolidayId.of(id, HolidayType.LUNAR),
                context ->
                        context.lunarMonth() == month && context.lunarDay() == day
        );
    }

    public HolidayId id() {
        return id;
    }

    public boolean matches(HolidayRuleContext context) {
        return predicate.test(context);
    }
}
