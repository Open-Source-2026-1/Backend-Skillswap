package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

import java.util.Date;

public record DonationResource(
        Long id,
        Long donorId,
        Long tutorId,
        Long sessionId,
        Double amount,
        Double netAmount,
        Double commission,
        String currency,
        String status,
        Date createdAt,
        Date updatedAt) {
}