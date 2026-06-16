package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> handle(GetAllMessagesBySessionIdQuery query) {
        return this.messageRepository.findBySessionId(query.sessionId());
    }

    @Override
    public Optional<Message> handle(GetMessageByIdQuery query) {
        return this.messageRepository.findById(query.messageId());
    }
}