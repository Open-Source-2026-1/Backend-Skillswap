package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects.QuizId;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptRepository {
    QuizAttempt save(QuizAttempt attempt);
    Optional<QuizAttempt> findById(Long id);
    List<QuizAttempt> findAll();
    List<QuizAttempt> findByLearnerId(Long learnerId);
    List<QuizAttempt> findByQuizId(QuizId quizId);
    void deleteById(Long id);
}