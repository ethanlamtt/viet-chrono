package io.github.ethanlamtt.vietchrono.scale;

/**
 * Provides deltaT algorithm provider implementation using
 * the do-nothing algorithms.
 *
 * @author ethanlamtt
 * @since 1.0.0
 */
public class NoOpDeltaTProvider implements DeltaTProvider {

    /**
     * The singleton instance of this class.
     */
    private static final DeltaTProvider INSTANCE = new NoOpDeltaTProvider();

    /**
     * Constructs a {@code NoOpDeltaTProvider}.
     *
     * <p>This constructor is private to control instances of this class.</p>
     */
    private NoOpDeltaTProvider() {}

    /**
     * Gets the initialized instance of this class.
     *
     * @return {@code DeltaTProvider} instance.
     */
    public static DeltaTProvider getInstance() {
        return INSTANCE;
    }

    @Override
    public double estimate(double year) {
        return 0.0;
    }
}
