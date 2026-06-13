package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.UpdateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.resources.UpdateTutorResource;

public class UpdateTutorCommandFromResourceAssembler {
    public static UpdateTutorCommand toCommandFromResource(Long tutorId, UpdateTutorResource resource) {
        return new UpdateTutorCommand(
                tutorId,
                resource.name(),
                resource.university(),
                resource.bio(),
                resource.rating(),
                resource.skills(),
                resource.available(),
                resource.avatarUrl(),
                resource.specialty(),
                resource.portfolioUrl(),
                resource.yearsExperience());
    }
}