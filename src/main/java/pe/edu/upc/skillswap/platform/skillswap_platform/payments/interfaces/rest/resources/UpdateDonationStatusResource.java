package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.DonationStatus;

public record UpdateDonationStatusResource(DonationStatus status) {
}