package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

public record CreateReportCommand(
        Long reporterId,
        String reporterName,
        Long reportedUserId,
        String reason,
        String description,
        Long sessionId) {
}