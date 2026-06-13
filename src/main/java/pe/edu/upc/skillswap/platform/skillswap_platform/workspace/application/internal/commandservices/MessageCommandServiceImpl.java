package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.MessageJpaRepository;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageJpaRepository messageJpaRepository;

    public MessageCommandServiceImpl(MessageJpaRepository messageJpaRepository) {
        this.messageJpaRepository = messageJpaRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        var message = new Message(
                command.content(),
                command.senderId(),
                command.sessionId());
        var savedMessage = messageJpaRepository.save(message);
        return Optional.of(savedMessage);
    }

    @Override
    public void handle(DeleteMessageCommand command) {
        messageJpaRepository.deleteById(command.messageId());
    }
}