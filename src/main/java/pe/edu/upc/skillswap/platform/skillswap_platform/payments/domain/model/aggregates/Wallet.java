package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.CreateWalletCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "wallets")
public class Wallet extends AuditableAbstractAggregateRoot<Wallet> {

    @Getter
    @NotNull
    @Min(1)
    @Column(name = "tutor_id", nullable = false, unique = true)
    private Long tutorId;

    @Getter
    @Min(0)
    @Column(nullable = false)
    private Double balance;

    @Getter
    @NotBlank
    @Column(nullable = false)
    private String currency;

    @Getter
    @Column(name = "bank_name")
    private String bankName;

    @Getter
    @Column(name = "account_number")
    private String accountNumber;

    public Wallet() {}

    public Wallet(CreateWalletCommand command) {
        this.tutorId = command.tutorId();
        this.balance = 0.0;
        this.currency = command.currency();
        this.bankName = command.bankName();
        this.accountNumber = command.accountNumber();
    }

    public Wallet addFunds(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance += amount;
        return this;
    }

    public Wallet withdrawFunds(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
        return this;
    }

    public boolean hasSufficientFunds(Double amount) {
        return this.balance >= amount;
    }
}