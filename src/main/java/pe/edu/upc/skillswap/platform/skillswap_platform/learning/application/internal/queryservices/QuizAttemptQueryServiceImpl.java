package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.valueobjects.QuizId;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizAttemptQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizAttemptJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAttemptQueryServiceImpl implements QuizAttemptQueryService {

    private final QuizAttemptJpaRepository quizAttemptJpaRepository;

    public QuizAttemptQueryServiceImpl(QuizAttemptJpaRepository quizAttemptJpaRepository) {
        this.quizAttemptJpaRepository = quizAttemptJpaRepository;
    }

    @Override
    public List<QuizAttempt> handle(GetAllQuizAttemptsQuery query) {
        return quizAttemptJpaRepository.findAll();
    }

    @Override
    public Optional<QuizAttempt> handle(GetQuizAttemptByIdQuery query) {
        return quizAttemptJpaRepository.findById(query.attemptId());
    }

    @Override
    public List<QuizAttempt> handle(GetQuizAttemptsByLearnerIdQuery query) {
        return quizAttemptJpaRepository.findByLearnerId(query.learnerId());
    }

    @Override
    public List<QuizAttempt> handle(GetQuizAttemptsByQuizIdQuery query) {
        return quizAttemptJpaRepository.findByQuizId(new QuizId(query.quizId()));
    }
}