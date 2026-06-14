package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.infrastructure.persistence.jpa.entities.TutorJpaEntity;

import java.util.Optional;

@Repository
public interface TutorJpaRepository extends JpaRepository<TutorJpaEntity, Long> {
    boolean existsByFullName(String fullName);
    boolean existsByFullNameAndIdIsNot(String fullName, Long id);
    Optional<TutorJpaEntity> findByFullName(String fullName);
}