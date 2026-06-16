package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.UpdateTutoringSessionStatusCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.TutoringSessionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.PATCH})
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
        var sessionId = this.tutoringSessionCommandService.handle(command);
        if (sessionId.equals(0L)) return ResponseEntity.badRequest().build();
        var session = this.tutoringSessionQueryService.handle(new GetTutoringSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.badRequest().build();
        var sessionResource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return new ResponseEntity<>(sessionResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TutoringSessionResource>> getAllTutoringSessions() {
        var sessions = this.tutoringSessionQueryService.handle(new GetAllTutoringSessionsQuery());
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<TutoringSessionResource> getTutoringSessionById(@PathVariable Long sessionId) {
        var session = this.tutoringSessionQueryService.handle(new GetTutoringSessionByIdQuery(sessionId));
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<TutoringSessionResource>> getTutoringSessionsByLearnerId(
            @PathVariable Long learnerId) {
        var sessions = this.tutoringSessionQueryService.handle(new GetTutoringSessionsByLearnerIdQuery(learnerId));
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<TutoringSessionResource>> getTutoringSessionsByTutorId(
            @PathVariable Long tutorId) {
        var sessions = this.tutoringSessionQueryService.handle(new GetTutoringSessionsByTutorIdQuery(tutorId));
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TutoringSessionResource>> getTutoringSessionsByStatus(
            @PathVariable String status) {
        var sessions = this.tutoringSessionQueryService.handle(new GetTutoringSessionsByStatusQuery(status));
        var resources = sessions.stream()
                .map(TutoringSessionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<TutoringSessionResource> updateTutoringSession(
            @PathVariable Long sessionId,
            @RequestBody UpdateTutoringSessionResource resource) {
        var command = UpdateTutoringSessionCommandFromResourceAssembler.toCommandFromResource(sessionId, resource);
        var session = this.tutoringSessionCommandService.handle(command);
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return ResponseEntity.ok(sessionResource);
    }

    @PatchMapping("/{sessionId}/status")
    public ResponseEntity<TutoringSessionResource> updateTutoringSessionStatus(
            @PathVariable Long sessionId,
            @RequestBody UpdateTutoringSessionStatusResource resource) {
        var command = new UpdateTutoringSessionStatusCommand(sessionId, resource.status());
        var session = this.tutoringSessionCommandService.handle(command);
        if (session.isEmpty()) return ResponseEntity.notFound().build();
        var sessionResource = TutoringSessionResourceFromEntityAssembler.toResourceFromEntity(session.get());
        return ResponseEntity.ok(sessionResource);
    }
}