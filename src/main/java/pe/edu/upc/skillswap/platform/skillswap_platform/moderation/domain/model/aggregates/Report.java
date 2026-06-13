package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
@Entity
@Table(name = "reports")
public class Report extends AbstractDomainAggregateRoot<Report> {

    @Setter
    @Column(name = "reporter_id", nullable = false)
    private Long reporterId;

    @Setter
    @Column(name = "reporter_name", nullable = false)
    private String reporterName;

    @Setter
    @Column(name = "reported_user_id", nullable = false)
    private Long reportedUserId;

    @Setter
    @Column(nullable = false)
    private String reason;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus status;

    @Setter
    @Column(name = "session_id")
    private Long sessionId;

    public Report() {
        this.status = ReportStatus.PENDING;
    }

    public Report(Long reporterId, String reporterName, Long reportedUserId,
                  String reason, String description, Long sessionId) {
        this();
        this.reporterId = reporterId;
        this.reporterName = reporterName;
        this.reportedUserId = reportedUserId;
        this.reason = reason;
        this.description = description;
        this.sessionId = sessionId;
    }

    public void updateStatus(ReportStatus status) {
        this.status = status;
    }
}