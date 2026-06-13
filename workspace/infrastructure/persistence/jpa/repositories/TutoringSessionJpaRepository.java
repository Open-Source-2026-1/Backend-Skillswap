package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.LearnerId;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.TutorId;

import java.util.List;

@Repository
public interface TutoringSessionJpaRepository extends JpaRepository<TutoringSession, Long> {
    List<TutoringSession> findByLearnerId(LearnerId learnerId);
    List<TutoringSession> findByTutorId(TutorId tutorId);
}