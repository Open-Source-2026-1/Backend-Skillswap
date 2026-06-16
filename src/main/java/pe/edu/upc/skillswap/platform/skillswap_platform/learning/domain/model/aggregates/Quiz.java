package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.CreateQuizCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.UpdateQuizCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz extends AuditableAbstractAggregateRoot<Quiz> {

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String title;

    @Getter
    @Column(name = "course")
    private String course;

    @Getter
    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Getter
    @NotNull
    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "quiz_questions", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "question")
    private List<String> questions;

    public Quiz() {}

    public Quiz(CreateQuizCommand command) {
        this.title = command.title();
        this.course = command.course();
        this.createdBy = command.createdBy();
        this.tutorId = command.tutorId();
        this.questions = command.questions();
    }

    public Quiz updateInformation(UpdateQuizCommand command) {
        this.title = command.title();
        this.course = command.course();
        this.questions = command.questions();
        return this;
    }
}