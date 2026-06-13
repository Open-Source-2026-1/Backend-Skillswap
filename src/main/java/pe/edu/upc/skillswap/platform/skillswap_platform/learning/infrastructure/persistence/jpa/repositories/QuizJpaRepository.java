package pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;

import java.util.List;

@Repository
public interface QuizJpaRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTutorId(Long tutorId);
}