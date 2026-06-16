package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.UpdateSanctionResource;

public class UpdateSanctionCommandFromResourceAssembler {
  public static UpdateSanctionCommand toCommandFromResource(Long sanctionId, UpdateSanctionResource resource) {
    return new UpdateSanctionCommand(
            sanctionId,
            resource.type(),
            resource.description(),
            resource.durationDays());
  }
}