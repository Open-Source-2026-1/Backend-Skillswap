package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;

public record CreateDonationResource(
        Long donorId,
        Long tutorId,
        Long sessionId,
        Double amount,
        Double commission,
        Currency currency) {
}