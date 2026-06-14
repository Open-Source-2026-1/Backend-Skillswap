package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.reputation.domain.model.aggregates.Tutor;
import pe.edu.upc.skillswap.platform.reputation.interfaces.rest.resources.TutorResource;

public class TutorResourceFromEntityAssembler {
    public static TutorResource toResourceFromEntity(Tutor entity) {
        return new TutorResource(entity.getId(), entity.getFullName(), entity.getUniversity(), entity.getAverageScore(), entity.getTotalReviews());
    }
}