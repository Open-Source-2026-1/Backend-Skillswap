package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.RevieweeId;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.ReviewerId;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.ReviewJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewJpaRepository reviewJpaRepository;

    public ReviewQueryServiceImpl(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewJpaRepository.findAll();
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewJpaRepository.findById(query.reviewId());
    }

    @Override
    public List<Review> handle(GetReviewsByTutorIdQuery query) {
        return reviewJpaRepository.findByTutorId(new RevieweeId(query.tutorId()));
    }

    @Override
    public List<Review> handle(GetReviewsByLearnerIdQuery query) {
        return reviewJpaRepository.findByLearnerId(new ReviewerId(query.learnerId()));
    }
}