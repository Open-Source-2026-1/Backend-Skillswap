package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources;

import java.util.Date;

public record QuizAttemptResource(
        Long id,
        Long quizId,
        Long learnerId,
        Float score,
        String status,
        Date createdAt,
        Date updatedAt) {
}