package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands;

public record CreateMessageCommand(
        String content,
        Long senderId,
        Long sessionId) {
}