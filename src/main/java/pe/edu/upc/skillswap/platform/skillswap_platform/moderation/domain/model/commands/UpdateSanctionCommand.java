package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

public record UpdateSanctionCommand(Long sanctionId, String type, String description, int durationDays) {
}