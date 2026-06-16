package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

public record CreateReportResource(Long reporterUserId, Long reportedUserId, String reason, String status) {
}