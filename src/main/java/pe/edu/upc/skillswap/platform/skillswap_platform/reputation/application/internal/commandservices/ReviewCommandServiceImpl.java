package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities.ReviewJpaEntity;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.ReviewJpaRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories.TutorJpaRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.ApplicationError;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.application.result.Result;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewJpaRepository reviewRepository;
    private final TutorJpaRepository tutorRepository;

    public ReviewCommandServiceImpl(ReviewJpaRepository reviewRepository, TutorJpaRepository tutorRepository) {
        this.reviewRepository = reviewRepository;
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateReviewCommand command) {
        var tutorOptional = tutorRepository.findById(command.tutorId());
        if (tutorOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound("Tutor", command.tutorId().toString()));
        }

        if (reviewRepository.existsByTutorIdAndStudentIdAndSessionId(command.tutorId(), command.studentId(), command.sessionId())) {
            return Result.failure(ApplicationError.conflict("Review", "This student already reviewed this session"));
        }

        var reviewDomain = new Review(command.tutorId(), command.studentId(), command.sessionId(), command.scoreValue(), command.comment());
        var reviewEntity = ReviewJpaEntity.fromDomain(reviewDomain);
        var savedReview = reviewRepository.save(reviewEntity);

        var tutorEntity = tutorOptional.get();
        tutorEntity.setTotalReviews(tutorEntity.getTotalReviews() + 1);
        double newAverage = ((tutorEntity.getAverageScore() * (tutorEntity.getTotalReviews() - 1)) + command.scoreValue()) / tutorEntity.getTotalReviews();
        tutorEntity.setAverageScore(Math.round(newAverage * 10.0) / 10.0);

        tutorRepository.save(tutorEntity);

        return Result.success(savedReview.getId());
    }
}