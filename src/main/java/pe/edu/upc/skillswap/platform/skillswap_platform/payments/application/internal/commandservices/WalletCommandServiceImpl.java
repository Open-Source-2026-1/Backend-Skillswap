package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.WalletCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.Optional;

@Service
public class WalletCommandServiceImpl implements WalletCommandService {

    private final WalletRepository walletRepository;

    public WalletCommandServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Long handle(CreateWalletCommand command) {
        if (this.walletRepository.existsByTutorId(command.tutorId())) {
            throw new IllegalArgumentException("Wallet already exists for tutor with id " + command.tutorId());
        }
        var wallet = new Wallet(command);
        this.walletRepository.save(wallet);
        return wallet.getId();
    }

    @Override
    public Optional<Wallet> handle(AddFundsToWalletCommand command) {
        if (!this.walletRepository.existsById(command.walletId())) {
            throw new IllegalArgumentException("Wallet with id " + command.walletId() + " does not exist");
        }
        var wallet = this.walletRepository.findById(command.walletId()).get();
        wallet.addFunds(command.amount());
        var updatedWallet = this.walletRepository.save(wallet);
        return Optional.of(updatedWallet);
    }

    @Override
    public Optional<Wallet> handle(WithdrawFundsFromWalletCommand command) {
        if (!this.walletRepository.existsById(command.walletId())) {
            throw new IllegalArgumentException("Wallet with id " + command.walletId() + " does not exist");
        }
        var wallet = this.walletRepository.findById(command.walletId()).get();
        wallet.withdrawFunds(command.amount());
        var updatedWallet = this.walletRepository.save(wallet);
        return Optional.of(updatedWallet);
    }
}