package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.UpdateReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "reviews")
public class Review extends AuditableAbstractAggregateRoot<Review> {

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "learner_id", nullable = false)
    private Long learnerId;

    @Getter
    @NotBlank
    @Column(name = "learner_name", nullable = false)
    private String learnerName;

    @Getter
    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Float rating;

    @Getter
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Getter
    @Column(name = "tutor_reply")
    private String tutorReply;

    public Review() {}

    public Review(CreateReviewCommand command) {
        this.tutorId = command.tutorId();
        this.learnerId = command.learnerId();
        this.learnerName = command.learnerName();
        this.rating = command.rating();
        this.comment = command.comment();
        this.sessionId = command.sessionId();
        this.tutorReply = command.tutorReply();
    }

    public Review updateInformation(Float rating, String comment, String tutorReply) {
        this.rating = rating;
        this.comment = comment;
        this.tutorReply = tutorReply;
        return this;
    }
}