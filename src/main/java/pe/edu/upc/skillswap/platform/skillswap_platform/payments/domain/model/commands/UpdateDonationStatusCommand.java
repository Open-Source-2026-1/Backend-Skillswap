package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

public record UpdateDonationStatusCommand(
        Long donationId,
        String status) {
}