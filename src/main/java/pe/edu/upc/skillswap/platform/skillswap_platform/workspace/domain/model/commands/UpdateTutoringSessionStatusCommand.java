package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands;

public record UpdateTutoringSessionStatusCommand(
        Long sessionId,
        String status) {
}