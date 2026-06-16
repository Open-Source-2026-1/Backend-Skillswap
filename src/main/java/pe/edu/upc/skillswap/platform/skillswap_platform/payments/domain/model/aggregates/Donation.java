package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.CreateDonationCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "donations")
public class Donation extends AuditableAbstractAggregateRoot<Donation> {

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "donor_id", nullable = false)
    private Long donorId;

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    @Getter
    @Column(name = "session_id")
    private Long sessionId;

    @Getter
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Double amount;

    @Getter
    @Column(name = "net_amount")
    private Double netAmount;

    @Getter
    @Column(name = "commission")
    private Double commission;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String currency;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String status;

    public Donation() {}

    public Donation(CreateDonationCommand command) {
        this.donorId = command.donorId();
        this.tutorId = command.tutorId();
        this.sessionId = command.sessionId();
        this.amount = command.amount();
        this.commission = command.commission();
        this.netAmount = command.amount() - (command.amount() * command.commission() / 100);
        this.currency = command.currency();
        this.status = "pending";
    }

    public Donation updateStatus(String status) {
        this.status = status;
        return this;
    }

    public boolean isPending() {
        return "pending".equalsIgnoreCase(this.status);
    }

    public boolean isCompleted() {
        return "completed".equalsIgnoreCase(this.status);
    }
}