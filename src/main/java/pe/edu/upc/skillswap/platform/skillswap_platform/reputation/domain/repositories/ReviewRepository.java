package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.RevieweeId;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.ReviewerId;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    Optional<Review> findById(Long id);
    List<Review> findAll();
    List<Review> findByTutorId(RevieweeId tutorId);
    List<Review> findByLearnerId(ReviewerId learnerId);
    void deleteById(Long id);
}