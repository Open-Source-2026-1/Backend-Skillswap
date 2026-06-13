package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.*;

import java.util.Optional;

public interface QuizAttemptCommandService {
    Optional<QuizAttempt> handle(CreateQuizAttemptCommand command);
    Optional<QuizAttempt> handle(CompleteQuizAttemptCommand command);
    void handle(DeleteQuizAttemptCommand command);
}