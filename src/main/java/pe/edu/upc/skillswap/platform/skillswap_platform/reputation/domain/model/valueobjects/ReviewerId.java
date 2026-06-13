package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects;

import java.util.Objects;

public record ReviewerId(Long value) {

    public ReviewerId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[ReviewerId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[ReviewerId] value must be greater than zero");
        }
    }

    public ReviewerId() { this(0L); }
}