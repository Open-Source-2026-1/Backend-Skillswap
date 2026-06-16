package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.CreateQuizAttemptCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt extends AuditableAbstractAggregateRoot<QuizAttempt> {

    @Getter
    @NotNull
    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Getter
    @NotNull
    @Column(name = "learner_id", nullable = false)
    private Long learnerId;

    @Getter
    @Min(0)
    @Max(10)
    @Column(name = "score")
    private Float score;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String status;

    public QuizAttempt() {}

    public QuizAttempt(CreateQuizAttemptCommand command) {
        this.quizId = command.quizId();
        this.learnerId = command.learnerId();
        this.status = "in_progress";
    }

    public QuizAttempt complete(Float score) {
        this.score = score;
        this.status = "completed";
        return this;
    }

    public QuizAttempt abandon() {
        this.status = "abandoned";
        return this;
    }

    public boolean isCompleted() {
        return "completed".equalsIgnoreCase(this.status);
    }
}