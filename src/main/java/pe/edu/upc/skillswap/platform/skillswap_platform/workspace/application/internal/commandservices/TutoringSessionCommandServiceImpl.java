package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.TutoringSessionJpaRepository;

import java.util.Optional;

@Service
public class TutoringSessionCommandServiceImpl implements TutoringSessionCommandService {

    private final TutoringSessionJpaRepository tutoringSessionJpaRepository;

    public TutoringSessionCommandServiceImpl(TutoringSessionJpaRepository tutoringSessionJpaRepository) {
        this.tutoringSessionJpaRepository = tutoringSessionJpaRepository;
    }

    @Override
    public Optional<TutoringSession> handle(CreateTutoringSessionCommand command) {
        var session = new TutoringSession(
                command.topic(),
                command.learnerId(),
                command.tutorId(),
                command.scheduledAt(),
                command.message(),
                command.studentLevel());
        var savedSession = tutoringSessionJpaRepository.save(session);
        return Optional.of(savedSession);
    }

    @Override
    public Optional<TutoringSession> handle(UpdateTutoringSessionStatusCommand command) {
        var optionalSession = tutoringSessionJpaRepository.findById(command.sessionId());
        if (optionalSession.isEmpty()) return Optional.empty();
        var session = optionalSession.get();
        session.updateStatus(command.status());
        var updatedSession = tutoringSessionJpaRepository.save(session);
        return Optional.of(updatedSession);
    }

    @Override
    public void handle(DeleteTutoringSessionCommand command) {
        tutoringSessionJpaRepository.deleteById(command.sessionId());
    }
}