package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates.Review;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.ReviewStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class ReviewJpaEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Column(name = "score_value", nullable = false)
    private Integer scoreValue;

    @Column(name = "comment", length = 500, nullable = false)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReviewStatus status;

    public static ReviewJpaEntity fromDomain(Review review) {
        var entity = new ReviewJpaEntity();
        entity.setId(review.getId());
        entity.setTutorId(review.getTutorId());
        entity.setStudentId(review.getStudentId());
        entity.setSessionId(review.getSessionId());
        entity.setScoreValue(review.getScore().value());
        entity.setComment(review.getComment());
        entity.setStatus(review.getStatus());
        return entity;
    }

    public Review toDomain() {
        var review = new Review(this.tutorId, this.studentId, this.sessionId, this.scoreValue, this.comment);
        review.setId(this.getId());
        review.setStatus(this.status);
        return review;
    }
}