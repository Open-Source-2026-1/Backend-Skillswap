package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.TutoringSessionRepository;

import java.util.Optional;

@Service
public class TutoringSessionCommandServiceImpl implements TutoringSessionCommandService {

    private final TutoringSessionRepository tutoringSessionRepository;

    public TutoringSessionCommandServiceImpl(TutoringSessionRepository tutoringSessionRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;
    }

    @Override
    public Long handle(CreateTutoringSessionCommand command) {
        if (command.learnerId().equals(command.tutorId())) {
            throw new IllegalArgumentException("Learner and tutor cannot be the same person");
        }
        var session = new TutoringSession(command);
        this.tutoringSessionRepository.save(session);
        return session.getId();
    }

    @Override
    public Optional<TutoringSession> handle(UpdateTutoringSessionCommand command) {
        if (!this.tutoringSessionRepository.existsById(command.sessionId())) {
            throw new IllegalArgumentException("Tutoring session with id " + command.sessionId() + " does not exist");
        }
        var sessionToUpdate = this.tutoringSessionRepository.findById(command.sessionId()).get();
        sessionToUpdate.updateInformation(command);
        var updatedSession = this.tutoringSessionRepository.save(sessionToUpdate);
        return Optional.of(updatedSession);
    }

    @Override
    public Optional<TutoringSession> handle(UpdateTutoringSessionStatusCommand command) {
        if (!this.tutoringSessionRepository.existsById(command.sessionId())) {
            throw new IllegalArgumentException("Tutoring session with id " + command.sessionId() + " does not exist");
        }
        var session = this.tutoringSessionRepository.findById(command.sessionId()).get();
        session.updateStatus(command.status());
        var updatedSession = this.tutoringSessionRepository.save(session);
        return Optional.of(updatedSession);
    }
}