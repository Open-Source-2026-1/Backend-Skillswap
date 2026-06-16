package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.CreateMessageCommand;

public interface MessageCommandService {
    Long handle(CreateMessageCommand command);
}