package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands;

public record UpdateReportCommand(Long reportId, String reason, String status, boolean closed) {
}