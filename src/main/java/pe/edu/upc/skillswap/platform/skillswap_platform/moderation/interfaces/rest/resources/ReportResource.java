package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

import java.util.Date;

public record ReportResource(Long id, Long reporterUserId, Long reportedUserId, String reason,
                               String status, boolean closed, Date reportedAt, Date createdAt, Date updatedAt) {
}
