package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.RevieweeId;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.valueobjects.ReviewerId;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "reviews")
public class Review extends AbstractDomainAggregateRoot<Review> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "tutor_id", nullable = false))
    })
    private RevieweeId tutorId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "learner_id", nullable = false))
    })
    private ReviewerId learnerId;

    @Setter
    @Column(name = "learner_name", nullable = false)
    private String learnerName;

    @Setter
    @Column(nullable = false)
    private Float rating;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Setter
    @Column(name = "session_id", nullable = false)
    private Long sessionId;


    @Setter
    @Column(name = "tutor_reply")
    private String tutorReply;

    public Review() {

    }

    public Review(Long tutorId, Long learnerId, String learnerName,
                  Float rating, String comment, Long sessionId, String tutorReply) {
        this();
        this.tutorId = new RevieweeId(tutorId);
        this.learnerId = new ReviewerId(learnerId);
        this.learnerName = learnerName;
        this.rating = rating;
        this.comment = comment;
        this.sessionId = sessionId;
        this.tutorReply = tutorReply;
    }
}