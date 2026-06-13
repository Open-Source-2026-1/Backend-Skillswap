package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities.Message;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(CreateMessageCommand command);
    void handle(DeleteMessageCommand command);
}