package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.SanctionType;

import java.time.LocalDateTime;

public record SanctionResource(
        Long id,
        Long userId,
        SanctionType type,
        String reason,
        Long reportId,
        LocalDateTime appliedAt,
        LocalDateTime expiresAt) {
}