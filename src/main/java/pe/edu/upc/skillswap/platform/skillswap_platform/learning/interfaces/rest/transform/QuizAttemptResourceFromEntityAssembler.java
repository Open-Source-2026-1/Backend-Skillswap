package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.QuizAttemptResource;

public class QuizAttemptResourceFromEntityAssembler {
    public static QuizAttemptResource toResourceFromEntity(QuizAttempt entity) {
        return new QuizAttemptResource(
                entity.getId(),
                entity.getQuizId(),
                entity.getLearnerId(),
                entity.getScore(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}