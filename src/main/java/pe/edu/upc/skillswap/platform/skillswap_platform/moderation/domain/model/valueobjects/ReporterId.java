package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects;

import java.util.Objects;

public record ReporterId(Long value) {

    public ReporterId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("[ReporterId] value cannot be null");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("[ReporterId] value must be greater than zero");
        }
    }

    public ReporterId() { this(0L); }
}
