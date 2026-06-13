package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources;

import java.time.LocalDateTime;

public record MessageResource(
        Long id,
        String content,
        Long senderId,
        Long sessionId,
        LocalDateTime sentAt) {
}