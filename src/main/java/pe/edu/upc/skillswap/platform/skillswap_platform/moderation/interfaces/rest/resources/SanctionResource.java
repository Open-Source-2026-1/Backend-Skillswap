package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources;

import java.util.Date;

public record SanctionResource(
        Long id,
        Long reportId,
        Long sanctionedUserId,
        String type,
        String description,
        int durationDays,
        Date createdAt,
        Date updatedAt) {
}