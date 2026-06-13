package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionStatus;

public record UpdateTutoringSessionStatusCommand(
        Long sessionId,
        SessionStatus status) {
}