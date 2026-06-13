package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.LearnerId;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.TutorId;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tutoring_sessions")
public class TutoringSession extends AbstractDomainAggregateRoot<TutoringSession> {

    @Setter
    private String topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "learner_id", nullable = false))
    })
    private LearnerId learnerId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "tutor_id", nullable = false))
    })
    private TutorId tutorId;

    @Setter
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Setter
    @Column(name = "message")
    private String message;

    @Setter
    @Column(name = "student_level")
    private String studentLevel;

    public TutoringSession() {
        this.status = SessionStatus.PENDING;
    }

    public TutoringSession(String topic, Long learnerId, Long tutorId, LocalDateTime scheduledAt, String message, String studentLevel) {
        this();
        this.topic = topic;
        this.learnerId = new LearnerId(learnerId);
        this.tutorId = new TutorId(tutorId);
        this.scheduledAt = scheduledAt;
        this.message = message;
        this.studentLevel = studentLevel;
    }

    public void updateStatus(SessionStatus status) {
        this.status = status;
    }
}