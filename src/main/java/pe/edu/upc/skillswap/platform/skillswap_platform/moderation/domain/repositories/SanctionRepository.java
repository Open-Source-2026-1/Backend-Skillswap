package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;

import java.util.List;
import java.util.Optional;

public interface SanctionRepository {
    Sanction save(Sanction sanction);
    Optional<Sanction> findById(Long id);
    List<Sanction> findAll();
    List<Sanction> findByUserId(Long userId);
    void deleteById(Long id);
}