package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateTutoringSessionCommand(
        Long sessionId,
        String topic,
        String message,
        String studentLevel,
        LocalDateTime scheduledAt) {
}