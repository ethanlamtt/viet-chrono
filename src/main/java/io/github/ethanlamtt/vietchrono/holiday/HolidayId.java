package io.github.ethanlamtt.vietchrono.holiday;

import java.util.Objects;

/**
 * Represents for a holiday id.
 *
 * <p>This class is immutable and thread-safe.</p>
 *
 * @author ethanlamtt
 * @since 1.0
 */
public final class HolidayId {

    private final String id;
    private final HolidayType type;

    private HolidayId(String id, HolidayType type) {
        this.id = Objects.requireNonNull(id, "id");
        this.type = Objects.requireNonNull(type, "type");
    }

    public static HolidayId of(String id, HolidayType type) {
        return new HolidayId(id, type);
    }

    public String id() {
        return id;
    }

    public HolidayType type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof HolidayId other))
            return false;

        return id.equals(other.id) && type.equals(other.type);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + id.hashCode();
        result = result * 31 + type.hashCode();

        return result;
    }
}
