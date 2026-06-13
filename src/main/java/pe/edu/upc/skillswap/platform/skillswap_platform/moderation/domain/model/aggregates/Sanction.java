package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.SanctionType;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "sanctions")
public class Sanction extends AbstractDomainAggregateRoot<Sanction> {

    @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SanctionType type;

    @Setter
    @Column(nullable = false)
    private String reason;

    @Setter
    @Column(name = "report_id")
    private Long reportId;

    @Setter
    @Column(name = "applied_at")
    private LocalDateTime appliedAt;

    @Setter
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public Sanction() {
        this.appliedAt = LocalDateTime.now();
    }

    public Sanction(Long userId, SanctionType type, String reason,
                    Long reportId, LocalDateTime expiresAt) {
        this();
        this.userId = userId;
        this.type = type;
        this.reason = reason;
        this.reportId = reportId;
        this.expiresAt = expiresAt;
    }
}