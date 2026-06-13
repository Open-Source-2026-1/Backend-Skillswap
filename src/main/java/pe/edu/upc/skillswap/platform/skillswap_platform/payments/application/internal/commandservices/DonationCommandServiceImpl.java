package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.valueobjects.DonationStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.DonationCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.DonationJpaRepository;

import java.util.Optional;

@Service
public class DonationCommandServiceImpl implements DonationCommandService {

    private final DonationJpaRepository donationJpaRepository;

    public DonationCommandServiceImpl(DonationJpaRepository donationJpaRepository) {
        this.donationJpaRepository = donationJpaRepository;
    }

    @Override
    public Optional<Donation> handle(CreateDonationCommand command) {
        var donation = new Donation(
                command.donorId(),
                command.tutorId(),
                command.sessionId(),
                command.amount(),
                command.commission(),
                command.currency());
        var savedDonation = donationJpaRepository.save(donation);
        return Optional.of(savedDonation);
    }

    @Override
    public Optional<Donation> handle(UpdateDonationStatusCommand command) {
        var optionalDonation = donationJpaRepository.findById(command.donationId());
        if (optionalDonation.isEmpty()) return Optional.empty();
        var donation = optionalDonation.get();
        if (command.status() == DonationStatus.COMPLETED) donation.complete();
        else if (command.status() == DonationStatus.FAILED) donation.fail();
        var updatedDonation = donationJpaRepository.save(donation);
        return Optional.of(updatedDonation);
    }

    @Override
    public void handle(DeleteDonationCommand command) {
        donationJpaRepository.deleteById(command.donationId());
    }
}