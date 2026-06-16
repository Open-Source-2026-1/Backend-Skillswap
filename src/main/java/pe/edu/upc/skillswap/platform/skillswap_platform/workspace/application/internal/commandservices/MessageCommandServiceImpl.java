package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.CreateMessageCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.TutoringSessionRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.MessageRepository;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepository;
    private final TutoringSessionRepository tutoringSessionRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository,
                                     TutoringSessionRepository tutoringSessionRepository) {
        this.messageRepository = messageRepository;
        this.tutoringSessionRepository = tutoringSessionRepository;
    }

    @Override
    public Long handle(CreateMessageCommand command) {
        if (!this.tutoringSessionRepository.existsById(command.sessionId())) {
            throw new IllegalArgumentException("Tutoring session with id " + command.sessionId() + " does not exist");
        }
        var message = new Message(command);
        this.messageRepository.save(message);
        return message.getId();
    }
}