package io.github.ethanlamtt.vietchrono.scale;

import io.github.ethanlamtt.vietchrono.astro.JulianEpoch;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Provides deltaT calculations for UT and TT conversion.
 *
 * <p>This class is instance-controlled, immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public final class DeltaT {

    /**
     * The singleton instance of this class.
     */
    private static final DeltaT INSTANCE;

    /**
     * Number of days in a Julian year.
     */
    private static final double DAYS_IN_JULIAN_YEAR = 365.25;

    static {
        ServiceLoader<DeltaTProvider> loader = ServiceLoader.load(DeltaTProvider.class);
        DeltaTProvider provider = loader.findFirst()
                .orElse(EspenakMeeusDeltaTProvider.getInstance());
        INSTANCE = new DeltaT(provider);
    }

    /**
     * The deltaT algorithm provider.
     */
    private final DeltaTProvider provider;

    /**
     * Constructs a {@code DeltaT}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     *
     * @param provider the deltaT algorithm provider.
     * @throws NullPointerException if {@code provider} is null
     */
    private DeltaT(DeltaTProvider provider) {
        this.provider = Objects.requireNonNull(provider, "provider");
    }

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code DeltaT} instance
     */
    public static DeltaT getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the deltaT value at a specified Julian Day number.
     *
     * @param julianDayValue the Julian Day number
     * @return the deltaT at {@code julianDayValue}
     */
    public double atJulianDay(double julianDayValue) {
        double year = 2000.0 + (julianDayValue - JulianEpoch.J2000.value()) / DAYS_IN_JULIAN_YEAR;
        return provider.estimate(year);
    }

    /**
     * Returns the deltaT value at a specified year.
     *
     * @param year the reference year
     * @return the deltaT at {@code year}
     */
    public double atYear(double year) {
        return provider.estimate(year);
    }

    /**
     * Returns the deltaT value at a specified zoned date time.
     *
     * @param zdt the reference zoned date time
     * @return the deltaT at {@code zdt}
     */
    public double atZonedDateTime(ZonedDateTime zdt) {
        ZonedDateTime startOfYear = zdt.truncatedTo(ChronoUnit.DAYS)
                .withDayOfYear(1);
        ZonedDateTime startOfNextYear = zdt.plusYears(1);

        long nanoOfYear = Duration.between(startOfYear, zdt).toNanos();
        long nanoInYear = Duration.between(startOfYear, startOfNextYear).toNanos();

        double year = zdt.getYear() + ((double) nanoOfYear / nanoInYear);

        return provider.estimate(year);
    }
}
