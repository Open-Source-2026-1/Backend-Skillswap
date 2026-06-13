package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
    List<Review> handle(GetReviewsByTutorIdQuery query);
    List<Review> handle(GetReviewsByLearnerIdQuery query);
}