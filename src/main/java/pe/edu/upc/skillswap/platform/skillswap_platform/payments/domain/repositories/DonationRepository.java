package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationRepository {
    Donation save(Donation donation);
    Optional<Donation> findById(Long id);
    List<Donation> findAll();
    List<Donation> findByDonorId(Long donorId);
    List<Donation> findByTutorId(Long tutorId);
    void deleteById(Long id);
}