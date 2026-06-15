package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.SanctionedUserId;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
@Entity
@Table(name = "sanctions")
public class Sanction extends AbstractDomainAggregateRoot<Sanction> {

    @Setter
    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "sanctioned_user_id", nullable = false))
    })
    private SanctionedUserId sanctionedUserId;

    @Setter
    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @Setter
    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Setter
    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    public Sanction() {
    }

    public Sanction(CreateSanctionCommand command) {
        this();
        this.reportId = command.reportId();
        this.sanctionedUserId = new SanctionedUserId(command.sanctionedUserId());
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
