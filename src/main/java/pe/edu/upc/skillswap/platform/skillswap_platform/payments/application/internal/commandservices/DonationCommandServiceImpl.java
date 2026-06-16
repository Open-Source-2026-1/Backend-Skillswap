package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.DonationCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.DonationRepository;

import java.util.Optional;

@Service
public class DonationCommandServiceImpl implements DonationCommandService {

    private final DonationRepository donationRepository;

    public DonationCommandServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Long handle(CreateDonationCommand command) {
        if (command.amount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (command.donorId().equals(command.tutorId())) {
            throw new IllegalArgumentException("Donor and tutor cannot be the same person");
        }
        var donation = new Donation(command);
        this.donationRepository.save(donation);
        return donation.getId();
    }

    @Override
    public Optional<Donation> handle(UpdateDonationStatusCommand command) {
        if (!this.donationRepository.existsById(command.donationId())) {
            throw new IllegalArgumentException("Donation with id " + command.donationId() + " does not exist");
        }
        var donation = this.donationRepository.findById(command.donationId()).get();
        donation.updateStatus(command.status());
        var updatedDonation = this.donationRepository.save(donation);
        return Optional.of(updatedDonation);
    }
}