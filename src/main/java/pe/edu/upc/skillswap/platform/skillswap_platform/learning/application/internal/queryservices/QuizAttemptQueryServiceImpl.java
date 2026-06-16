package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizAttemptQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizAttemptRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAttemptQueryServiceImpl implements QuizAttemptQueryService {

    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptQueryServiceImpl(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @Override
    public List<QuizAttempt> handle(GetAllQuizAttemptsQuery query) {
        return this.quizAttemptRepository.findAll();
    }

    @Override
    public Optional<QuizAttempt> handle(GetQuizAttemptByIdQuery query) {
        return this.quizAttemptRepository.findById(query.attemptId());
    }

    @Override
    public List<QuizAttempt> handle(GetQuizAttemptsByLearnerIdQuery query) {
        return this.quizAttemptRepository.findByLearnerId(query.learnerId());
    }

    @Override
    public List<QuizAttempt> handle(GetQuizAttemptsByQuizIdQuery query) {
        return this.quizAttemptRepository.findByQuizId(query.quizId());
    }
}