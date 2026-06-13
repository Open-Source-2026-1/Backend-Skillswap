package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects;

import java.util.Objects;

public record SessionId(Long value) {

    public SessionId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[SessionId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[SessionId] value must be greater than zero");
        }
    }

    public SessionId() { this(0L); }
}