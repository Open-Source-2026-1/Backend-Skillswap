package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;

public record WalletResource(
        Long id,
        Long tutorId,
        Double balance,
        Currency currency,
        String bankName,
        String accountNumber) {
}