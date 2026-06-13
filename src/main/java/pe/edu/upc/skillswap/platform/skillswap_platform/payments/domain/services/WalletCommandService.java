package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.*;

import java.util.Optional;

public interface WalletCommandService {
    Optional<Wallet> handle(CreateWalletCommand command);
    Optional<Wallet> handle(AddFundsToWalletCommand command);
    Optional<Wallet> handle(WithdrawFundsFromWalletCommand command);
}