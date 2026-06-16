package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizQueryServiceImpl implements QuizQueryService {

    private final QuizRepository quizRepository;

    public QuizQueryServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> handle(GetAllQuizzesQuery query) {
        return this.quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> handle(GetQuizByIdQuery query) {
        return this.quizRepository.findById(query.quizId());
    }

    @Override
    public List<Quiz> handle(GetQuizzesByTutorIdQuery query) {
        return this.quizRepository.findByTutorId(query.tutorId());
    }

    @Override
    public List<Quiz> handle(GetQuizzesByCourseQuery query) {
        return this.quizRepository.findByCourse(query.course());
    }
}