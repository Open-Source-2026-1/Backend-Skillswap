package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizAttemptCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizAttemptJpaRepository;

import java.util.Optional;

@Service
public class QuizAttemptCommandServiceImpl implements QuizAttemptCommandService {

    private final QuizAttemptJpaRepository quizAttemptJpaRepository;

    public QuizAttemptCommandServiceImpl(QuizAttemptJpaRepository quizAttemptJpaRepository) {
        this.quizAttemptJpaRepository = quizAttemptJpaRepository;
    }

    @Override
    public Optional<QuizAttempt> handle(CreateQuizAttemptCommand command) {
        var attempt = new QuizAttempt(
                command.quizId(),
                command.learnerId());
        var savedAttempt = quizAttemptJpaRepository.save(attempt);
        return Optional.of(savedAttempt);
    }

    @Override
    public Optional<QuizAttempt> handle(CompleteQuizAttemptCommand command) {
        var optionalAttempt = quizAttemptJpaRepository.findById(command.attemptId());
        if (optionalAttempt.isEmpty()) return Optional.empty();
        var attempt = optionalAttempt.get();
        attempt.complete(command.score());
        var updatedAttempt = quizAttemptJpaRepository.save(attempt);
        return Optional.of(updatedAttempt);
    }

    @Override
    public void handle(DeleteQuizAttemptCommand command) {
        quizAttemptJpaRepository.deleteById(command.attemptId());
    }
}