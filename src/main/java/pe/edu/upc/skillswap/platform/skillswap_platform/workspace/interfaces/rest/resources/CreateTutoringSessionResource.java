package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateTutoringSessionResource(
        String topic,
        Long learnerId,
        Long tutorId,
        LocalDateTime scheduledAt,
        String message,
        String studentLevel) {
}