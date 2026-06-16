package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates;

import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.ReviewStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.Score;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
public class Review extends AbstractDomainAggregateRoot<Review> {

    @Setter
    private Long id;

    private Long tutorId;
    private Long studentId;
    private Long sessionId;
    private Score score;
    private String comment;

    @Setter
    private ReviewStatus status;

    public Review(Long tutorId, Long studentId, Long sessionId, Integer scoreValue, String comment) {
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.score = new Score(scoreValue);
        this.comment = comment;
        this.status = ReviewStatus.PUBLISHED;
    }

    public Review() {}
}