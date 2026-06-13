package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;

import java.util.List;
import java.util.Optional;

public interface TutorRepository {
    Tutor save(Tutor tutor);
    Optional<Tutor> findById(Long id);
    List<Tutor> findAll();
    List<Tutor> findByAvailable(Boolean available);
    List<Tutor> findByUniversity(String university);
    void deleteById(Long id);
}