package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.LearnerId;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.TutorId;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories.TutoringSessionJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TutoringSessionQueryServiceImpl implements TutoringSessionQueryService {

    private final TutoringSessionJpaRepository tutoringSessionJpaRepository;

    public TutoringSessionQueryServiceImpl(TutoringSessionJpaRepository tutoringSessionJpaRepository) {
        this.tutoringSessionJpaRepository = tutoringSessionJpaRepository;
    }

    @Override
    public List<TutoringSession> handle(GetAllTutoringSessionsQuery query) {
        return tutoringSessionJpaRepository.findAll();
    }

    @Override
    public Optional<TutoringSession> handle(GetTutoringSessionByIdQuery query) {
        return tutoringSessionJpaRepository.findById(query.sessionId());
    }

    @Override
    public List<TutoringSession> handle(GetTutoringSessionsByLearnerIdQuery query) {
        return tutoringSessionJpaRepository.findByLearnerId(new LearnerId(query.learnerId()));
    }

    @Override
    public List<TutoringSession> handle(GetTutoringSessionsByTutorIdQuery query) {
        return tutoringSessionJpaRepository.findByTutorId(new TutorId(query.tutorId()));
    }
}