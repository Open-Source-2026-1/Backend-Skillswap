package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects;

import java.util.Objects;

public record SanctionedUserId(Long value) {

    public SanctionedUserId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[SanctionedUserId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[SanctionedUserId] value must be greater than zero");
        }
    }

    public SanctionedUserId() { this(0L); }
}
