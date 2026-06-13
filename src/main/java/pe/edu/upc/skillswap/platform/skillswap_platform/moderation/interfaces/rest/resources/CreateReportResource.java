package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

public record CreateReportResource(
        Long reporterId,
        String reporterName,
        Long reportedUserId,
        String reason,
        String description,
        Long sessionId) {
}