package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.Currency;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.DonationStatus;

public record DonationResource(
        Long id,
        Long donorId,
        Long tutorId,
        Long sessionId,
        Double amount,
        Double netAmount,
        Double commission,
        Currency currency,
        DonationStatus status) {
}