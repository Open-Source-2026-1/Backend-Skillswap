package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects;

import java.util.Objects;

public record LearnerId(Long value) {

    public LearnerId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[LearnerId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[LearnerId] value must be greater than zero");
        }
    }

    public LearnerId() { this(0L); }
}