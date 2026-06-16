package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "sanctions")
public class Sanction extends AuditableAbstractAggregateRoot<Sanction> {

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "sanctioned_user_id", nullable = false)
    private Long sanctionedUserId;

    @Getter
    @NotBlank
    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Getter
    @NotBlank
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Getter
    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    public Sanction() {}

    public Sanction(CreateSanctionCommand command) {
        this.reportId = command.reportId();
        this.sanctionedUserId = command.sanctionedUserId();
        this.type = command.type();
        this.description = command.description();
        this.durationDays = command.durationDays();
    }

    public Sanction updateInformation(String type, String description, int durationDays) {
        this.type = type;
        this.description = description;
        this.durationDays = durationDays;
        return this;
    }

    public boolean isBan() {
        return "ban".equalsIgnoreCase(this.type);
    }
}