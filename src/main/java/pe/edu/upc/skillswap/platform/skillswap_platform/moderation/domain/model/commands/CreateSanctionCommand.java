package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.SanctionType;

import java.time.LocalDateTime;

public record CreateSanctionCommand(
        Long userId,
        SanctionType type,
        String reason,
        Long reportId,
        LocalDateTime expiresAt) {
}