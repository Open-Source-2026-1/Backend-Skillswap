package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionStatus;

import java.time.LocalDateTime;

public record TutoringSessionResource(
        Long id,
        String topic,
        SessionStatus status,
        Long learnerId,
        Long tutorId,
        LocalDateTime scheduledAt) {
}