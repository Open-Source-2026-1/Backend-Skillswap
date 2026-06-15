package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Sanction;

import java.util.List;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Long> {
  List<Sanction> findByReportId(Long reportId);
  List<Sanction> findBySanctionedUserId_Value(Long sanctionedUserId);
}
