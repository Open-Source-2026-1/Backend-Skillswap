package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface DonationQueryService {
    List<Donation> handle(GetAllDonationsQuery query);
    Optional<Donation> handle(GetDonationByIdQuery query);
    List<Donation> handle(GetDonationsByDonorIdQuery query);
    List<Donation> handle(GetDonationsByTutorIdQuery query);
}