package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.DonationStatus;

public record UpdateDonationStatusCommand(
        Long donationId,
        DonationStatus status) {
}