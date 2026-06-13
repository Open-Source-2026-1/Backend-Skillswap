package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects;

import java.util.Objects;

public record TutorId(Long value) {

    public TutorId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[TutorId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[TutorId] value must be greater than zero");
        }
    }

    public TutorId() { this(0L); }
}