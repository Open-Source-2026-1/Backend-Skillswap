package pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.commands.DeleteTutorCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services.TutorCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.domain.services.TutorQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.discovery.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/tutors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Tutors", description = "Tutors Management Endpoints")
public class TutorsController {

    private final TutorCommandService tutorCommandService;
    private final TutorQueryService tutorQueryService;

    public TutorsController(TutorCommandService tutorCommandService,
                            TutorQueryService tutorQueryService) {
        this.tutorCommandService = tutorCommandService;
        this.tutorQueryService = tutorQueryService;
    }

    @PostMapping
    public ResponseEntity<TutorResource> createTutor(@RequestBody CreateTutorResource resource) {
        var command = CreateTutorCommandFromResourceAssembler.toCommandFromResource(resource);
        var tutorId = this.tutorCommandService.handle(command);
        if (tutorId.equals(0L)) return ResponseEntity.badRequest().build();
        var tutor = this.tutorQueryService.handle(new GetTutorByIdQuery(tutorId));
        if (tutor.isEmpty()) return ResponseEntity.badRequest().build();
        var tutorResource = TutorResourceFromEntityAssembler.toResourceFromEntity(tutor.get());
        return new ResponseEntity<>(tutorResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TutorResource>> getAllTutors() {
        var tutors = this.tutorQueryService.handle(new GetAllTutorsQuery());
        var resources = tutors.stream()
                .map(TutorResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorResource> getTutorById(@PathVariable Long tutorId) {
        var tutor = this.tutorQueryService.handle(new GetTutorByIdQuery(tutorId));
        if (tutor.isEmpty()) return ResponseEntity.notFound().build();
        var resource = TutorResourceFromEntityAssembler.toResourceFromEntity(tutor.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/available/{available}")
    public ResponseEntity<List<TutorResource>> getTutorsByAvailability(@PathVariable Boolean available) {
        var tutors = this.tutorQueryService.handle(new GetTutorsByAvailabilityQuery(available));
        var resources = tutors.stream()
                .map(TutorResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/university/{university}")
    public ResponseEntity<List<TutorResource>> getTutorsByUniversity(@PathVariable String university) {
        var tutors = this.tutorQueryService.handle(new GetTutorsByUniversityQuery(university));
        var resources = tutors.stream()
                .map(TutorResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<TutorResource>> getTutorsBySpecialty(@PathVariable String specialty) {
        var tutors = this.tutorQueryService.handle(new GetTutorsBySpecialtyQuery(specialty));
        var resources = tutors.stream()
                .map(TutorResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<TutorResource> updateTutor(@PathVariable Long tutorId,
                                                     @RequestBody UpdateTutorResource resource) {
        var command = UpdateTutorCommandFromResourceAssembler.toCommandFromResource(tutorId, resource);
        var tutor = this.tutorCommandService.handle(command);
        if (tutor.isEmpty()) return ResponseEntity.notFound().build();
        var tutorResource = TutorResourceFromEntityAssembler.toResourceFromEntity(tutor.get());
        return ResponseEntity.ok(tutorResource);
    }

    @DeleteMapping("/{tutorId}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long tutorId) {
        var command = new DeleteTutorCommand(tutorId);
        this.tutorCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}