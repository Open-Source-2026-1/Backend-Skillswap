package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

@Getter
@Entity
@Table(name = "wallets")
public class Wallet extends AbstractDomainAggregateRoot<Wallet> {

    @Setter
    @Column(name = "tutor_id", nullable = false, unique = true)
    private Long tutorId;

    @Setter
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Setter
    @Column(name = "bank_name")
    private String bankName;

    @Setter
    @Column(name = "account_number")
    private String accountNumber;

    public Wallet() {
        this.balance = 0.0;
        this.currency = Currency.PEN;
    }

    public Wallet(Long tutorId, Currency currency, String bankName, String accountNumber) {
        this();
        this.tutorId = tutorId;
        this.currency = currency;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public void addFunds(Double amount) {
        this.balance += amount;
    }

    public void withdrawFunds(Double amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("[Wallet] Insufficient funds");
        }
        this.balance -= amount;
    }
}