package pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Donation;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorId(Long donorId);
    List<Donation> findByTutorId(Long tutorId);
    List<Donation> findByStatus(String status);
}