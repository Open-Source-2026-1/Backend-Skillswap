package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizQueryServiceImpl implements QuizQueryService {

    private final QuizJpaRepository quizJpaRepository;

    public QuizQueryServiceImpl(QuizJpaRepository quizJpaRepository) {
        this.quizJpaRepository = quizJpaRepository;
    }

    @Override
    public List<Quiz> handle(GetAllQuizzesQuery query) {
        return quizJpaRepository.findAll();
    }

    @Override
    public Optional<Quiz> handle(GetQuizByIdQuery query) {
        return quizJpaRepository.findById(query.quizId());
    }

    @Override
    public List<Quiz> handle(GetQuizzesByTutorIdQuery query) {
        return quizJpaRepository.findByTutorId(query.tutorId());
    }
}