package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;

public record ReportResource(
        Long id,
        Long reporterId,
        String reporterName,
        Long reportedUserId,
        String reason,
        String description,
        ReportStatus status,
        Long sessionId) {
}