package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.resources.TutorResource;

public class TutorResourceFromEntityAssembler {
    public static TutorResource toResourceFromEntity(Tutor entity) {
        return new TutorResource(
                entity.getId(),
                entity.getName(),
                entity.getUniversity(),
                entity.getBio(),
                entity.getRating(),
                entity.getSkills(),
                entity.getAvailable(),
                entity.getAvatarUrl(),
                entity.getSpecialty(),
                entity.getPortfolioUrl(),
                entity.getYearsExperience());
    }
}