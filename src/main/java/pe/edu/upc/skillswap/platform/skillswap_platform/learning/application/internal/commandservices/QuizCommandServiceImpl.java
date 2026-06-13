package pe.edu.upc.skillswap.platform.skillswap_platform.learning.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.aggregates.Quiz;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.infrastructure.persistence.jpa.repositories.QuizJpaRepository;

import java.util.Optional;

@Service
public class QuizCommandServiceImpl implements QuizCommandService {

    private final QuizJpaRepository quizJpaRepository;

    public QuizCommandServiceImpl(QuizJpaRepository quizJpaRepository) {
        this.quizJpaRepository = quizJpaRepository;
    }

    @Override
    public Optional<Quiz> handle(CreateQuizCommand command) {
        var quiz = new Quiz(
                command.title(),
                command.course(),
                command.createdBy(),
                command.tutorId(),
                command.questions());
        var savedQuiz = quizJpaRepository.save(quiz);
        return Optional.of(savedQuiz);
    }

    @Override
    public Optional<Quiz> handle(UpdateQuizCommand command) {
        var optionalQuiz = quizJpaRepository.findById(command.quizId());
        if (optionalQuiz.isEmpty()) return Optional.empty();
        var quiz = optionalQuiz.get();
        quiz.setTitle(command.title());
        quiz.setCourse(command.course());
        quiz.setQuestions(command.questions());
        var updatedQuiz = quizJpaRepository.save(quiz);
        return Optional.of(updatedQuiz);
    }

    @Override
    public void handle(DeleteQuizCommand command) {
        quizJpaRepository.deleteById(command.quizId());
    }
}