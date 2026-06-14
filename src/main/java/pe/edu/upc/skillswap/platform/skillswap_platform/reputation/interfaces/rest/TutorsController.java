package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.DeleteTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.UpdateTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetAllTutorsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetTutorByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.TutorCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.TutorQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources.CreateTutorResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform.CreateTutorCommandFromResourceAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform.TutorResourceFromEntityAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.interfaces.rest.resources.MessageResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.interfaces.rest.transform.ResponseEntityAssembler;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/tutors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tutors", description = "Tutor Management Endpoints")
public class TutorsController {

    private final TutorCommandService tutorCommandService;
    private final TutorQueryService tutorQueryService;

    public TutorsController(TutorCommandService tutorCommandService, TutorQueryService tutorQueryService) {
        this.tutorCommandService = tutorCommandService;
        this.tutorQueryService = tutorQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createTutor(@RequestBody CreateTutorResource resource) {
        var command = CreateTutorCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = tutorCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                tutorId -> {
                    var tutor = tutorQueryService.handle(new GetTutorByIdQuery(tutorId)).getOrElse(null);
                    return TutorResourceFromEntityAssembler.toResourceFromEntity(tutor);
                },
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllTutors() {
        var result = tutorQueryService.handle(new GetAllTutorsQuery());
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                tutors -> tutors.stream().map(TutorResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<?> getTutorById(@PathVariable Long tutorId) {
        var result = tutorQueryService.handle(new GetTutorByIdQuery(tutorId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TutorResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<?> updateTutor(@PathVariable Long tutorId, @RequestBody CreateTutorResource resource) {
        var command = new UpdateTutorCommand(tutorId, resource.fullName(), resource.university());
        var result = tutorCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TutorResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{tutorId}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long tutorId) {
        var result = tutorCommandService.handle(new DeleteTutorCommand(tutorId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                success -> new MessageResource("Tutor successfully deleted."),
                HttpStatus.OK
        );
    }
}