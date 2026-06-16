package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportedUserId;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReporterId;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.util.Date;

@Getter
@Entity
@Table(name = "reports")
public class Report extends AbstractDomainAggregateRoot<Report> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "reporter_user_id", nullable = false))
    })
    private ReporterId reporterUserId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "reported_user_id", nullable = false))
    })
    private ReportedUserId reportedUserId;

    @Setter
    @Column(name = "reason", length = 500, nullable = false)
    private String reason;

    @Setter
    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @Setter
    @Column(name = "closed", nullable = false)
    private boolean closed;

    @Column(name = "reported_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedAt;

    public Report() {
    }

    public Report(CreateReportCommand command) {
        this();
        this.reporterUserId = new ReporterId(command.reporterUserId());
        this.reportedUserId = new ReportedUserId(command.reportedUserId());
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
