package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;

public record CreateWalletCommand(
        Long tutorId,
        Currency currency,
        String bankName,
        String accountNumber) {
}