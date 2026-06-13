package pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects.QuizId;

import java.util.List;

@Repository
public interface QuizAttemptJpaRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByLearnerId(Long learnerId);
    List<QuizAttempt> findByQuizId(QuizId quizId);
}