package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Long handle(CreateReviewCommand command) {
        if (command.tutorId().equals(command.learnerId())) {
            throw new IllegalArgumentException("Tutor and learner cannot be the same person");
        }
        if (command.rating() < 1 || command.rating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        var review = new Review(command);
        this.reviewRepository.save(review);
        return review.getId();
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        if (!this.reviewRepository.existsById(command.reviewId())) {
            throw new IllegalArgumentException("Review with id " + command.reviewId() + " does not exist");
        }
        var reviewToUpdate = this.reviewRepository.findById(command.reviewId()).get();
        reviewToUpdate.updateInformation(command.rating(), command.comment(), command.tutorReply());
        var updatedReview = this.reviewRepository.save(reviewToUpdate);
        return Optional.of(updatedReview);
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        if (!this.reviewRepository.existsById(command.reviewId())) {
            throw new IllegalArgumentException("Review with id " + command.reviewId() + " does not exist");
        }
        this.reviewRepository.deleteById(command.reviewId());
    }
}