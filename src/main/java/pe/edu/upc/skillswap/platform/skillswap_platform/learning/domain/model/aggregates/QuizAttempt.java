package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects.AttemptStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects.QuizId;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt extends AbstractDomainAggregateRoot<QuizAttempt> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "quiz_id", nullable = false))
    })
    private QuizId quizId;

    @Setter
    @Column(name = "learner_id", nullable = false)
    private Long learnerId;

    @Setter
    private Float score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttemptStatus status;

    public QuizAttempt() {
        this.status = AttemptStatus.IN_PROGRESS;
    }

    public QuizAttempt(Long quizId, Long learnerId) {
        this();
        this.quizId = new QuizId(quizId);
        this.learnerId = learnerId;
    }

    public void complete(Float score) {
        this.score = score;
        this.status = AttemptStatus.COMPLETED;
    }

    public void abandon() {
        this.status = AttemptStatus.ABANDONED;
    }
}