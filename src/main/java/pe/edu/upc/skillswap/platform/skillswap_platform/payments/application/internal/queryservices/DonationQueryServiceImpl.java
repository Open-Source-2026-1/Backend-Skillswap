package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.DonationQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.DonationJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DonationQueryServiceImpl implements DonationQueryService {

    private final DonationJpaRepository donationJpaRepository;

    public DonationQueryServiceImpl(DonationJpaRepository donationJpaRepository) {
        this.donationJpaRepository = donationJpaRepository;
    }

    @Override
    public List<Donation> handle(GetAllDonationsQuery query) {
        return donationJpaRepository.findAll();
    }

    @Override
    public Optional<Donation> handle(GetDonationByIdQuery query) {
        return donationJpaRepository.findById(query.donationId());
    }

    @Override
    public List<Donation> handle(GetDonationsByDonorIdQuery query) {
        return donationJpaRepository.findByDonorId(query.donorId());
    }

    @Override
    public List<Donation> handle(GetDonationsByTutorIdQuery query) {
        return donationJpaRepository.findByTutorId(query.tutorId());
    }
}