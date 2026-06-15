package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

public record CreateReportCommand(Long reporterUserId, Long reportedUserId, String reason, String status) {
}
