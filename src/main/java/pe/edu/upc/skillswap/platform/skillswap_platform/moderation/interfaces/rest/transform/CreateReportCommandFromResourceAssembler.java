package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CreateReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.CreateReportResource;

public class CreateReportCommandFromResourceAssembler {
  public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
    return new CreateReportCommand(resource.reporterUserId(), resource.reportedUserId(), resource.reason(), resource.status());
  }
}
