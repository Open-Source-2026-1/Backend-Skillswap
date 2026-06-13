package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.DeleteQuizCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Quizzes", description = "Quizzes Management Endpoints")
public class QuizzesController {

    private final QuizCommandService quizCommandService;
    private final QuizQueryService quizQueryService;

    public QuizzesController(QuizCommandService quizCommandService,
                             QuizQueryService quizQueryService) {
        this.quizCommandService = quizCommandService;
        this.quizQueryService = quizQueryService;
    }

    @PostMapping
    public ResponseEntity<QuizResource> createQuiz(@RequestBody CreateQuizResource resource) {
        var command = CreateQuizCommandFromResourceAssembler.toCommandFromResource(resource);
        var quiz = quizCommandService.handle(command);
        if (quiz.isEmpty()) return ResponseEntity.badRequest().build();
        var quizResource = QuizResourceFromEntityAssembler.toResourceFromEntity(quiz.get());
        return new ResponseEntity<>(quizResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuizResource>> getAllQuizzes() {
        var query = new GetAllQuizzesQuery();
        var quizzes = quizQueryService.handle(query);
        var resources = quizzes.stream()
                .map(QuizResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResource> getQuizById(@PathVariable Long quizId) {
        var query = new GetQuizByIdQuery(quizId);
        var quiz = quizQueryService.handle(query);
        if (quiz.isEmpty()) return ResponseEntity.notFound().build();
        var resource = QuizResourceFromEntityAssembler.toResourceFromEntity(quiz.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<QuizResource>> getQuizzesByTutorId(@PathVariable Long tutorId) {
        var query = new GetQuizzesByTutorIdQuery(tutorId);
        var quizzes = quizQueryService.handle(query);
        var resources = quizzes.stream()
                .map(QuizResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizResource> updateQuiz(@PathVariable Long quizId,
                                                   @RequestBody UpdateQuizResource resource) {
        var command = UpdateQuizCommandFromResourceAssembler.toCommandFromResource(quizId, resource);
        var quiz = quizCommandService.handle(command);
        if (quiz.isEmpty()) return ResponseEntity.notFound().build();
        var quizResource = QuizResourceFromEntityAssembler.toResourceFromEntity(quiz.get());
        return ResponseEntity.ok(quizResource);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
        var command = new DeleteQuizCommand(quizId);
        quizCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}