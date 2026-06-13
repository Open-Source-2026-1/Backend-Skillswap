package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

public record CreateMessageResource(
        String content,
        Long senderId,
        Long sessionId) {
}