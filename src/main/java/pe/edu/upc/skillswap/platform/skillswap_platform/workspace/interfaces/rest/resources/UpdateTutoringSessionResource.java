package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateTutoringSessionResource(
        String topic,
        String message,
        String studentLevel,
        LocalDateTime scheduledAt) {
}