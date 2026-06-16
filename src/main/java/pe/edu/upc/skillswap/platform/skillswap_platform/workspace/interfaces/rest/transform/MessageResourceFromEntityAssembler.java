package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {
    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(
                entity.getId(),
                entity.getContent(),
                entity.getSenderId(),
                entity.getSessionId(),
                entity.getSentAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}