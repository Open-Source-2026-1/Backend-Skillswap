package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

public record CreateSanctionCommand(Long reportId, Long sanctionedUserId, String type, String description, int durationDays) {
}