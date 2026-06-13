package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects.AttemptStatus;

public record QuizAttemptResource(
        Long id,
        Long quizId,
        Long learnerId,
        Float score,
        AttemptStatus status) {
}