package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities.ReviewJpaEntity;

import java.util.List;

@Repository
public interface ReviewJpaRepository extends JpaRepository<ReviewJpaEntity, Long> {
    List<ReviewJpaEntity> findByTutorId(Long tutorId);
    boolean existsByTutorIdAndStudentIdAndSessionId(Long tutorId, Long studentId, Long sessionId);
}