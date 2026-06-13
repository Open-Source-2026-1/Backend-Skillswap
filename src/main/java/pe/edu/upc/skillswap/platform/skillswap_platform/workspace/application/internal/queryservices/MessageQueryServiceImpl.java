package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionId;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.MessageJpaRepository;

import java.util.List;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageJpaRepository messageJpaRepository;

    public MessageQueryServiceImpl(MessageJpaRepository messageJpaRepository) {
        this.messageJpaRepository = messageJpaRepository;
    }

    @Override
    public List<Message> handle(GetAllMessagesBySessionIdQuery query) {
        return messageJpaRepository.findBySessionId(new SessionId(query.sessionId()));
    }
}