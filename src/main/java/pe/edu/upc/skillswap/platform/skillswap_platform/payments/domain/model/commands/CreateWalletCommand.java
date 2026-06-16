package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

public record CreateWalletCommand(
        Long tutorId,
        String currency,
        String bankName,
        String accountNumber) {
}