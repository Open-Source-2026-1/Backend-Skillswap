package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

public record CreateDonationCommand(
        Long donorId,
        Long tutorId,
        Long sessionId,
        Double amount,
        Double commission,
        String currency) {
}