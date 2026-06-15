package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects;

import java.util.Objects;

public record ReportedUserId(Long value) {

    public ReportedUserId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[ReportedUserId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[ReportedUserId] value must be greater than zero");
        }
    }

    public ReportedUserId() { this(0L); }
}
