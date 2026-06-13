package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.QuizResource;

public class QuizResourceFromEntityAssembler {
    public static QuizResource toResourceFromEntity(Quiz entity) {
        return new QuizResource(
                entity.getId(),
                entity.getTitle(),
                entity.getCourse(),
                entity.getCreatedBy(),
                entity.getTutorId(),
                entity.getQuestions());
    }
}