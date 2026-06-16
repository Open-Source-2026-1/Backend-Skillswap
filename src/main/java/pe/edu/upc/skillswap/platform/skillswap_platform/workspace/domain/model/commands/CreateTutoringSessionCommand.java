package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands;

import java.time.LocalDateTime;

public record CreateTutoringSessionCommand(
        Long learnerId,
        Long tutorId,
        String topic,
        String message,
        String studentLevel,
        LocalDateTime scheduledAt) {
}