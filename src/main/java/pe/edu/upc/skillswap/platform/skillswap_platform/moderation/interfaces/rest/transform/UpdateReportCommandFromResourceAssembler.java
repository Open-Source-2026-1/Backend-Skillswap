package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.UpdateReportResource;

public class UpdateReportCommandFromResourceAssembler {
  public static UpdateReportCommand toCommandFromResource(Long reportId, UpdateReportResource resource) {
    return new UpdateReportCommand(
            reportId,
            resource.reason(),
            resource.status(),
            resource.closed());
  }
}