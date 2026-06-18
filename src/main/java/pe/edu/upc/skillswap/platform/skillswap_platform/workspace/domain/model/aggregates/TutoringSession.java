package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.CreateTutoringSessionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.UpdateTutoringSessionCommand;

import java.time.LocalDateTime;

@Entity
@Table(name = "tutoring_sessions")
public class TutoringSession extends AuditableAbstractAggregateRoot<TutoringSession> {

    @Getter
    @NotNull
    @Column(name = "learner_id", nullable = false)
    private Long learnerId;

    @Getter
    @NotNull
    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String topic;

    @Getter
    @Column(name = "message")
    private String message;

    @Getter
    @Column(name = "student_level")
    private String studentLevel;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String status;

    @Getter
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    public TutoringSession() {}

    public TutoringSession(CreateTutoringSessionCommand command) {
        this.learnerId = command.learnerId();
        this.tutorId = command.tutorId();
        this.topic = command.topic();
        this.message = command.message();
        this.studentLevel = command.studentLevel();
        this.status = "PENDING";
        this.scheduledAt = command.scheduledAt();
    }

    public TutoringSession updateInformation(UpdateTutoringSessionCommand command) {
        this.topic = command.topic();
        this.message = command.message();
        this.studentLevel = command.studentLevel();
        this.scheduledAt = command.scheduledAt();
        return this;
    }

    public TutoringSession updateStatus(String status) {
        this.status = status.toUpperCase();
        return this;
    }

    public boolean isPending() {
        return "pending".equalsIgnoreCase(this.status);
    }

    public boolean isScheduled() {
        return "scheduled".equalsIgnoreCase(this.status);
    }
}
