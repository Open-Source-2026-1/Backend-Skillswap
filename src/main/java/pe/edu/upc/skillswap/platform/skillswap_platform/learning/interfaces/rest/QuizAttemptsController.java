package pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.CompleteQuizAttemptCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.commands.DeleteQuizAttemptCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizAttemptCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.domain.services.QuizAttemptQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.learning.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/quiz-attempts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Quiz Attempts", description = "Quiz Attempts Management Endpoints")
public class QuizAttemptsController {

    private final QuizAttemptCommandService quizAttemptCommandService;
    private final QuizAttemptQueryService quizAttemptQueryService;

    public QuizAttemptsController(QuizAttemptCommandService quizAttemptCommandService,
                                  QuizAttemptQueryService quizAttemptQueryService) {
        this.quizAttemptCommandService = quizAttemptCommandService;
        this.quizAttemptQueryService = quizAttemptQueryService;
    }

    @PostMapping
    public ResponseEntity<QuizAttemptResource> createQuizAttempt(
            @RequestBody CreateQuizAttemptResource resource) {
        var command = CreateQuizAttemptCommandFromResourceAssembler.toCommandFromResource(resource);
        var attempt = quizAttemptCommandService.handle(command);
        if (attempt.isEmpty()) return ResponseEntity.badRequest().build();
        var attemptResource = QuizAttemptResourceFromEntityAssembler.toResourceFromEntity(attempt.get());
        return new ResponseEntity<>(attemptResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuizAttemptResource>> getAllQuizAttempts() {
        var query = new GetAllQuizAttemptsQuery();
        var attempts = quizAttemptQueryService.handle(query);
        var resources = attempts.stream()
                .map(QuizAttemptResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<QuizAttemptResource> getQuizAttemptById(@PathVariable Long attemptId) {
        var query = new GetQuizAttemptByIdQuery(attemptId);
        var attempt = quizAttemptQueryService.handle(query);
        if (attempt.isEmpty()) return ResponseEntity.notFound().build();
        var resource = QuizAttemptResourceFromEntityAssembler.toResourceFromEntity(attempt.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<QuizAttemptResource>> getQuizAttemptsByLearnerId(
            @PathVariable Long learnerId) {
        var query = new GetQuizAttemptsByLearnerIdQuery(learnerId);
        var attempts = quizAttemptQueryService.handle(query);
        var resources = attempts.stream()
                .map(QuizAttemptResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizAttemptResource>> getQuizAttemptsByQuizId(
            @PathVariable Long quizId) {
        var query = new GetQuizAttemptsByQuizIdQuery(quizId);
        var attempts = quizAttemptQueryService.handle(query);
        var resources = attempts.stream()
                .map(QuizAttemptResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{attemptId}/complete")
    public ResponseEntity<QuizAttemptResource> completeQuizAttempt(@PathVariable Long attemptId,
                                                                   @RequestBody CompleteQuizAttemptResource resource) {
        var command = new CompleteQuizAttemptCommand(attemptId, resource.score());
        var attempt = quizAttemptCommandService.handle(command);
        if (attempt.isEmpty()) return ResponseEntity.notFound().build();
        var attemptResource = QuizAttemptResourceFromEntityAssembler.toResourceFromEntity(attempt.get());
        return ResponseEntity.ok(attemptResource);
    }

    @DeleteMapping("/{attemptId}")
    public ResponseEntity<?> deleteQuizAttempt(@PathVariable Long attemptId) {
        var command = new DeleteQuizAttemptCommand(attemptId);
        quizAttemptCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}