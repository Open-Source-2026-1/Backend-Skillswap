package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands;

public record CreateQuizAttemptCommand(
        Long quizId,
        Long learnerId) {
}