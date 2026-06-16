package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Entity
@Table(name = "reports")
public class Report extends AuditableAbstractAggregateRoot<Report> {

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "reporter_user_id", nullable = false)
    private Long reporterUserId;

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "reported_user_id", nullable = false)
    private Long reportedUserId;

    @Getter
    @NotBlank
    @Column(name = "reason", length = 500, nullable = false)
    private String reason;

    @Getter
    @NotBlank
    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @Getter
    @Column(name = "closed", nullable = false)
    private boolean closed;

    @Getter
    @Column(name = "reported_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedAt;

    public Report() {}

    public Report(CreateReportCommand command) {
        this.reporterUserId = command.reporterUserId();
        this.reportedUserId = command.reportedUserId();
        this.reason = command.reason();
        this.status = (command.status() != null && !command.status().isBlank()) ? command.status() : "pending";
        this.closed = false;
        this.reportedAt = new Date();
    }

    public Report updateInformation(String reason, String status, boolean closed) {
        this.reason = reason;
        this.status = status;
        this.closed = closed;
        return this;
    }

    public Report close() {
        this.closed = true;
        this.status = "resolved";
        return this;
    }

    public boolean isPending() {
        return "pending".equalsIgnoreCase(this.status);
    }
}