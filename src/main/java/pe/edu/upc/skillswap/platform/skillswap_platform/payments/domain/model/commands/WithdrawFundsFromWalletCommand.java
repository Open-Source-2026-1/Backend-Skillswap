package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

public record WithdrawFundsFromWalletCommand(
        Long walletId,
        Double amount) {
}