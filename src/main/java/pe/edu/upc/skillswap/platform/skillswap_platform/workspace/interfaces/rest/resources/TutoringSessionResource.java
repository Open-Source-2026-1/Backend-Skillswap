package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.Date;

public record TutoringSessionResource(
        Long id,
        Long learnerId,
        Long tutorId,
        String topic,
        String message,
        String studentLevel,
        String status,
        LocalDateTime scheduledAt,
        Date createdAt,
        Date updatedAt) {
}