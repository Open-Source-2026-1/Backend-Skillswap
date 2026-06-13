package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.WalletCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.WalletJpaRepository;

import java.util.Optional;

@Service
public class WalletCommandServiceImpl implements WalletCommandService {

    private final WalletJpaRepository walletJpaRepository;

    public WalletCommandServiceImpl(WalletJpaRepository walletJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
    }

    @Override
    public Optional<Wallet> handle(CreateWalletCommand command) {
        var wallet = new Wallet(
                command.tutorId(),
                command.currency(),
                command.bankName(),
                command.accountNumber());
        var savedWallet = walletJpaRepository.save(wallet);
        return Optional.of(savedWallet);
    }

    @Override
    public Optional<Wallet> handle(AddFundsToWalletCommand command) {
        var optionalWallet = walletJpaRepository.findById(command.walletId());
        if (optionalWallet.isEmpty()) return Optional.empty();
        var wallet = optionalWallet.get();
        wallet.addFunds(command.amount());
        var updatedWallet = walletJpaRepository.save(wallet);
        return Optional.of(updatedWallet);
    }

    @Override
    public Optional<Wallet> handle(WithdrawFundsFromWalletCommand command) {
        var optionalWallet = walletJpaRepository.findById(command.walletId());
        if (optionalWallet.isEmpty()) return Optional.empty();
        var wallet = optionalWallet.get();
        wallet.withdrawFunds(command.amount());
        var updatedWallet = walletJpaRepository.save(wallet);
        return Optional.of(updatedWallet);
    }
}