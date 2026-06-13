package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources;

public record CreateQuizAttemptResource(
        Long quizId,
        Long learnerId) {
}