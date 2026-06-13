package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects;

import java.util.Objects;

public record RevieweeId(Long value) {

    public RevieweeId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[RevieweeId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[RevieweeId] value must be greater than zero");
        }
    }

    public RevieweeId() { this(0L); }
}