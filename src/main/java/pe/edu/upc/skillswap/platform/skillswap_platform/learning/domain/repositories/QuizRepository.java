package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizRepository {
    Quiz save(Quiz quiz);
    Optional<Quiz> findById(Long id);
    List<Quiz> findAll();
    List<Quiz> findByTutorId(Long tutorId);
    void deleteById(Long id);
}