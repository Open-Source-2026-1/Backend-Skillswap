package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {
    List<Message> handle(GetAllMessagesBySessionIdQuery query);
    Optional<Message> handle(GetMessageByIdQuery query);
}