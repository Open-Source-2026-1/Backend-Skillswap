package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.TutoringSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TutoringSessionQueryServiceImpl implements TutoringSessionQueryService {

    private final TutoringSessionRepository tutoringSessionRepository;

    public TutoringSessionQueryServiceImpl(TutoringSessionRepository tutoringSessionRepository) {
        this.tutoringSessionRepository = tutoringSessionRepository;
    }

    @Override
    public List<TutoringSession> handle(GetAllTutoringSessionsQuery query) {
        return this.tutoringSessionRepository.findAll();
    }

    @Override
    public Optional<TutoringSession> handle(GetTutoringSessionByIdQuery query) {
        return this.tutoringSessionRepository.findById(query.sessionId());
    }

    @Override
    public List<TutoringSession> handle(GetTutoringSessionsByLearnerIdQuery query) {
        return this.tutoringSessionRepository.findByLearnerId(query.learnerId());
    }

    @Override
    public List<TutoringSession> handle(GetTutoringSessionsByTutorIdQuery query) {
        return this.tutoringSessionRepository.findByTutorId(query.tutorId());
    }

    @Override
    public List<TutoringSession> handle(GetTutoringSessionsByStatusQuery query) {
        return this.tutoringSessionRepository.findByStatus(query.status());
    }
}