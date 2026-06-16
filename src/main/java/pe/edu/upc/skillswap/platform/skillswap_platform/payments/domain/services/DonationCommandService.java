package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.*;

import java.util.Optional;

public interface DonationCommandService {
    Long handle(CreateDonationCommand command);
    Optional<Donation> handle(UpdateDonationStatusCommand command);
}