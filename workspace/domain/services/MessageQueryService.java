package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;

import java.util.List;

public interface MessageQueryService {
    List<Message> handle(GetAllMessagesBySessionIdQuery query);
}