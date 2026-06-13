package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.*;

import java.util.Optional;

public interface QuizCommandService {
    Optional<Quiz> handle(CreateQuizCommand command);
    Optional<Quiz> handle(UpdateQuizCommand command);
    void handle(DeleteQuizCommand command);
}