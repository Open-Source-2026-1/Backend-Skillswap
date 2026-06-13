package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.DonationStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
@Entity
@Table(name = "donations")
public class Donation extends AbstractDomainAggregateRoot<Donation> {

    @Setter
    @Column(name = "donor_id", nullable = false)
    private Long donorId;

    @Setter
    @Column(name = "tutor_id", nullable = false)
    private Long tutorId;

    @Setter
    @Column(name = "session_id")
    private Long sessionId;

    @Setter
    @Column(nullable = false)
    private Double amount;

    @Setter
    @Column(name = "net_amount")
    private Double netAmount;

    @Setter
    private Double commission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationStatus status;

    public Donation() {
        this.status = DonationStatus.PENDING;
        this.currency = Currency.PEN;
    }

    public Donation(Long donorId, Long tutorId, Long sessionId, Double amount,
                    Double commission, Currency currency) {
        this();
        this.donorId = donorId;
        this.tutorId = tutorId;
        this.sessionId = sessionId;
        this.amount = amount;
        this.commission = commission;
        this.netAmount = amount - (amount * commission / 100);
        this.currency = currency;
    }

    public void complete() {
        this.status = DonationStatus.COMPLETED;
    }

    public void fail() {
        this.status = DonationStatus.FAILED;
    }
}