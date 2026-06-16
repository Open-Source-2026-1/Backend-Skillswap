package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.QuizAttempt;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizAttemptCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizAttemptRepository;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizRepository;

import java.util.Optional;

@Service
public class QuizAttemptCommandServiceImpl implements QuizAttemptCommandService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;

    public QuizAttemptCommandServiceImpl(QuizAttemptRepository quizAttemptRepository,
                                         QuizRepository quizRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public Long handle(CreateQuizAttemptCommand command) {
        if (!this.quizRepository.existsById(command.quizId())) {
            throw new IllegalArgumentException("Quiz with id " + command.quizId() + " does not exist");
        }
        if (this.quizAttemptRepository.existsByQuizIdAndLearnerIdAndStatus(
                command.quizId(), command.learnerId(), "in_progress")) {
            throw new IllegalArgumentException("Learner already has an in-progress attempt for this quiz");
        }
        var attempt = new QuizAttempt(command);
        this.quizAttemptRepository.save(attempt);
        return attempt.getId();
    }

    @Override
    public Optional<QuizAttempt> handle(CompleteQuizAttemptCommand command) {
        if (!this.quizAttemptRepository.existsById(command.attemptId())) {
            throw new IllegalArgumentException("Quiz attempt with id " + command.attemptId() + " does not exist");
        }
        var attempt = this.quizAttemptRepository.findById(command.attemptId()).get();
        attempt.complete(command.score());
        var updatedAttempt = this.quizAttemptRepository.save(attempt);
        return Optional.of(updatedAttempt);
    }

    @Override
    public void handle(DeleteQuizAttemptCommand command) {
        if (!this.quizAttemptRepository.existsById(command.attemptId())) {
            throw new IllegalArgumentException("Quiz attempt with id " + command.attemptId() + " does not exist");
        }
        this.quizAttemptRepository.deleteById(command.attemptId());
    }
}