package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.aggregates.Report;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {
  public static ReportResource toResourceFromEntity(Report entity) {
    return new ReportResource(
            entity.getId(),
            entity.getReporterUserId(),
            entity.getReportedUserId(),
            entity.getReason(),
            entity.getStatus(),
            entity.isClosed(),
            entity.getReportedAt(),
            entity.getCreatedAt(),
            entity.getUpdatedAt());
  }
}