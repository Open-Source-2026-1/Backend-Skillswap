package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;

import java.util.List;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    List<Tutor> findByAvailable(Boolean available);
    List<Tutor> findByUniversity(String university);
    List<Tutor> findBySpecialty(String specialty);
    boolean existsByNameAndUniversity(String name, String university);
}