package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.util.List;

@Getter
@Entity
@Table(name = "quizzes")
public class Quiz extends AbstractDomainAggregateRoot<Quiz> {

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    private String course;

    @Setter
    @Column(name = "created_by")
    private Long createdBy;

    @Setter
    @Column(name = "tutor_id")
    private Long tutorId;

    @Setter
    @ElementCollection
    @CollectionTable(name = "quiz_questions", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "question")
    private List<String> questions;

    public Quiz() {}

    public Quiz(String title, String course, Long createdBy, Long tutorId, List<String> questions) {
        this.title = title;
        this.course = course;
        this.createdBy = createdBy;
        this.tutorId = tutorId;
        this.questions = questions;
    }
}