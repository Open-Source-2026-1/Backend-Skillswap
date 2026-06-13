package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands;

import java.time.LocalDateTime;

public record CreateTutoringSessionCommand(
        String topic,
        Long learnerId,
        Long tutorId,
        LocalDateTime scheduledAt,
        String message,
        String studentLevel) {
}