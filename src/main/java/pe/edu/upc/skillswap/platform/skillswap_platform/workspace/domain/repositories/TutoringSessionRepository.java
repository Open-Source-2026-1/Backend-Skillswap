package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.LearnerId;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.TutorId;

import java.util.List;
import java.util.Optional;

public interface TutoringSessionRepository {
    TutoringSession save(TutoringSession session);
    Optional<TutoringSession> findById(Long id);
    List<TutoringSession> findAll();
    List<TutoringSession> findByLearnerId(LearnerId learnerId);
    List<TutoringSession> findByTutorId(TutorId tutorId);
    void deleteById(Long id);
}