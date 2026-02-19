package io.github.ethanlamtt.vietchrono.scale;

/**
 * Provides deltaT algorithm provider.
 *
 * @author ethanlamtt
 * @since 1.0.0
 * @see EspenakMeeusDeltaTProvider
 * @see NoOpDeltaTProvider
 */
public interface DeltaTProvider {
    /**
     * Returns estimated deltaT at a decimal year.
     *
     * @param year the reference decimal year to estimate.
     * @return deltaT value in seconds
     */
    double estimate(double year);
}
