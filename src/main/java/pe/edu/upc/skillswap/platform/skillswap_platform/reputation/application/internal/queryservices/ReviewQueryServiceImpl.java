package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetReviewsByTutorIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities.ReviewJpaEntity;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.ReviewJpaRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewJpaRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewJpaRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Result<List<Review>, ApplicationError> handle(GetReviewsByTutorIdQuery query) {
        var reviews = reviewRepository.findByTutorId(query.tutorId()).stream()
                .map(ReviewJpaEntity::toDomain)
                .collect(Collectors.toList());
        return Result.success(reviews);
    }
}