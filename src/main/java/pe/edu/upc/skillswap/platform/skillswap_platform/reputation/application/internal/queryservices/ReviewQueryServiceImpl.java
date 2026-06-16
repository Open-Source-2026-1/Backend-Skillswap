package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return this.reviewRepository.findAll();
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return this.reviewRepository.findById(query.reviewId());
    }

    @Override
    public List<Review> handle(GetReviewsByTutorIdQuery query) {
        return this.reviewRepository.findByTutorId(query.tutorId());
    }

    @Override
    public List<Review> handle(GetReviewsByLearnerIdQuery query) {
        return this.reviewRepository.findByLearnerId(query.learnerId());
    }
}