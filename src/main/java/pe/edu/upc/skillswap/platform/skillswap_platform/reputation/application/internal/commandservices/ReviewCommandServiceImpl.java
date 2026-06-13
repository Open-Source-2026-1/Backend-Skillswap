package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.ReviewJpaRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewJpaRepository reviewJpaRepository;

    public ReviewCommandServiceImpl(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

    @Override
    public Optional<Review> handle(CreateReviewCommand command) {
        var review = new Review(
                command.tutorId(),
                command.learnerId(),
                command.learnerName(),
                command.rating(),
                command.comment(),
                command.sessionId(),
                command.tutorReply());
        var savedReview = reviewJpaRepository.save(review);
        return Optional.of(savedReview);
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        var optionalReview = reviewJpaRepository.findById(command.reviewId());
        if (optionalReview.isEmpty()) return Optional.empty();
        var review = optionalReview.get();
        review.setRating(command.rating());
        review.setComment(command.comment());
        review.setTutorReply(command.tutorReply());
        var updatedReview = reviewJpaRepository.save(review);
        return Optional.of(updatedReview);
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        reviewJpaRepository.deleteById(command.reviewId());
    }
}