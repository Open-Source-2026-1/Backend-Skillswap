package pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface QuizQueryService {
    List<Quiz> handle(GetAllQuizzesQuery query);
    Optional<Quiz> handle(GetQuizByIdQuery query);
    List<Quiz> handle(GetQuizzesByTutorIdQuery query);
    List<Quiz> handle(GetQuizzesByCourseQuery query);
}