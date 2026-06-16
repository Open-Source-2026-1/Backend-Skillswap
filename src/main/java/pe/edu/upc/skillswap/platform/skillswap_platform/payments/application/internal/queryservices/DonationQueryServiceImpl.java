package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.DonationQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.DonationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DonationQueryServiceImpl implements DonationQueryService {

    private final DonationRepository donationRepository;

    public DonationQueryServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> handle(GetAllDonationsQuery query) {
        return this.donationRepository.findAll();
    }

    @Override
    public Optional<Donation> handle(GetDonationByIdQuery query) {
        return this.donationRepository.findById(query.donationId());
    }

    @Override
    public List<Donation> handle(GetDonationsByDonorIdQuery query) {
        return this.donationRepository.findByDonorId(query.donorId());
    }

    @Override
    public List<Donation> handle(GetDonationsByTutorIdQuery query) {
        return this.donationRepository.findByTutorId(query.tutorId());
    }

    @Override
    public List<Donation> handle(GetDonationsByStatusQuery query) {
        return this.donationRepository.findByStatus(query.status());
    }
}