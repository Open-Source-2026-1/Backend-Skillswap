package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizRepository;

import java.util.Optional;

@Service
public class QuizCommandServiceImpl implements QuizCommandService {

    private final QuizRepository quizRepository;

    public QuizCommandServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Long handle(CreateQuizCommand command) {
        if (this.quizRepository.existsByTitleAndTutorId(command.title(), command.tutorId())) {
            throw new IllegalArgumentException("A quiz with the same title already exists for this tutor");
        }
        var quiz = new Quiz(command);
        this.quizRepository.save(quiz);
        return quiz.getId();
    }

    @Override
    public Optional<Quiz> handle(UpdateQuizCommand command) {
        if (!this.quizRepository.existsById(command.quizId())) {
            throw new IllegalArgumentException("Quiz with id " + command.quizId() + " does not exist");
        }
        var quizToUpdate = this.quizRepository.findById(command.quizId()).get();
        quizToUpdate.updateInformation(command);
        var updatedQuiz = this.quizRepository.save(quizToUpdate);
        return Optional.of(updatedQuiz);
    }

    @Override
    public void handle(DeleteQuizCommand command) {
        if (!this.quizRepository.existsById(command.quizId())) {
            throw new IllegalArgumentException("Quiz with id " + command.quizId() + " does not exist");
        }
        this.quizRepository.deleteById(command.quizId());
    }
}