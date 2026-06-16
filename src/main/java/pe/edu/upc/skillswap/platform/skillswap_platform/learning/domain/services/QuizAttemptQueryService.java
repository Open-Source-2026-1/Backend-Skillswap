package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface QuizAttemptQueryService {
    List<QuizAttempt> handle(GetAllQuizAttemptsQuery query);
    Optional<QuizAttempt> handle(GetQuizAttemptByIdQuery query);
    List<QuizAttempt> handle(GetQuizAttemptsByLearnerIdQuery query);
    List<QuizAttempt> handle(GetQuizAttemptsByQuizIdQuery query);
}