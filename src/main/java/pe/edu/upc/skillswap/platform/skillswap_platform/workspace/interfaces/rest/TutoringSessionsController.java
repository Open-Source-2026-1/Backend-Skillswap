package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.DeleteTutoringSessionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.GetAllTutoringSessionsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.GetTutoringSessionByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.GetTutoringSessionsByLearnerIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.GetTutoringSessionsByTutorIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.CreateTutoringSessionResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.TutoringSessionResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.UpdateTutoringSessionStatusResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform.CreateTutoringSessionCommandFromResourceAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform.TutoringSessionResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/tutoring-sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tutoring Sessions", description = "Tutoring Sessions Management Endpoints")
public class TutoringSessionsController {

    private final TutoringSessionCommandService tutoringSessionCommandService;
    private final TutoringSessionQueryService tutoringSessionQueryService;

    public TutoringSessionsController(TutoringSessionCommandService tutoringSessionCommandService,
                                      TutoringSessionQueryService tutoringSessionQueryService) {
        this.tutoringSessionCommandService = tutoringSessionCommandService;
        this.tutoringSessionQueryService = tutoringSessionQueryService;
    }

    @PostMapping
    public ResponseEntity<TutoringSessionResource> createTutoringSession(
            @RequestBody CreateTutoringSessionResource resource) {
        var command = CreateTutoringSessionCommandFromResourceAssembler.toCommandFromResource(resource);
        var session = tutoringSessionCommandService.handle(command);
        if (session.isEmpty()) return ResponseEntity.badRequest().build();
        var sessionResource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return new ResponseEntity<>(sessionResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TutoringSessionResource>> getAllTutoringSessions() {
        var query = new GetAllTutoringSessionsQuery();
        var sessions = tutoringSessionQueryService.handle(query);
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<TutoringSessionResource> getTutoringSessionById(@PathVariable Long sessionId) {
        var query = new GetTutoringSessionByIdQuery(sessionId);
        var session = tutoringSessionQueryService.handle(query);
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<TutoringSessionResource>> getTutoringSessionsByLearnerId(
            @PathVariable Long learnerId) {
        var query = new GetTutoringSessionsByLearnerIdQuery(learnerId);
        var sessions = tutoringSessionQueryService.handle(query);
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<TutoringSessionResource>> getTutoringSessionsByTutorId(
            @PathVariable Long tutorId) {
        var query = new GetTutoringSessionsByTutorIdQuery(tutorId);
        var sessions = tutoringSessionQueryService.handle(query);
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{sessionId}/status")
    public ResponseEntity<TutoringSessionResource> updateTutoringSessionStatus(
            @PathVariable Long sessionId,
            @RequestBody UpdateTutoringSessionStatusResource resource) {
        var command = new pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands
                .UpdateTutoringSessionStatusCommand(sessionId, resource.status());
        var session = tutoringSessionCommandService.handle(command);
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return ResponseEntity.ok(sessionResource);
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<?> deleteTutoringSession(@PathVariable Long sessionId) {
        var command = new DeleteTutoringSessionCommand(sessionId);
        tutoringSessionCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}