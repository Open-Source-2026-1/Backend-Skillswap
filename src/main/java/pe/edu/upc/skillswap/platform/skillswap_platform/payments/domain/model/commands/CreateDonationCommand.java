package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;

public record CreateDonationCommand(
        Long donorId,
        Long tutorId,
        Long sessionId,
        Double amount,
        Double commission,
        Currency currency) {
}