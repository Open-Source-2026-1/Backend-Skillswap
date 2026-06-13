package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands;

public record CompleteQuizAttemptCommand(
        Long attemptId,
        Float score) {
}