package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.RevieweeId;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.ReviewerId;

import java.util.List;

@Repository
public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    List<Review> findByTutorId(RevieweeId tutorId);
    List<Review> findByLearnerId(ReviewerId learnerId);
}