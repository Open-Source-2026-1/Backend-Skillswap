package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

public record CreateDonationResource(
        Long donorId,
        Long tutorId,
        Long sessionId,
        Double amount,
        Double commission,
        String currency) {
}