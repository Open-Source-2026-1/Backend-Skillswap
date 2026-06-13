package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/sanctions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sanctions", description = "Sanctions Management Endpoints")
public class SanctionsController {

    private final SanctionCommandService sanctionCommandService;
    private final SanctionQueryService sanctionQueryService;

    public SanctionsController(SanctionCommandService sanctionCommandService,
                               SanctionQueryService sanctionQueryService) {
        this.sanctionCommandService = sanctionCommandService;
        this.sanctionQueryService = sanctionQueryService;
    }

    @PostMapping
    public ResponseEntity<SanctionResource> createSanction(@RequestBody CreateSanctionResource resource) {
        var command = CreateSanctionCommandFromResourceAssembler.toCommandFromResource(resource);
        var sanction = sanctionCommandService.handle(command);
        if (sanction.isEmpty()) return ResponseEntity.badRequest().build();
        var sanctionResource = SanctionResourceFromEntityAssembler.toResourceFromEntity(sanction.get());
        return new ResponseEntity<>(sanctionResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SanctionResource>> getAllSanctions() {
        var query = new GetAllSanctionsQuery();
        var sanctions = sanctionQueryService.handle(query);
        var resources = sanctions.stream()
                .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{sanctionId}")
    public ResponseEntity<SanctionResource> getSanctionById(@PathVariable Long sanctionId) {
        var query = new GetSanctionByIdQuery(sanctionId);
        var sanction = sanctionQueryService.handle(query);
        if (sanction.isEmpty()) return ResponseEntity.notFound().build();
        var resource = SanctionResourceFromEntityAssembler.toResourceFromEntity(sanction.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SanctionResource>> getSanctionsByUserId(@PathVariable Long userId) {
        var query = new GetSanctionsByUserIdQuery(userId);
        var sanctions = sanctionQueryService.handle(query);
        var resources = sanctions.stream()
                .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{sanctionId}")
    public ResponseEntity<?> deleteSanction(@PathVariable Long sanctionId) {
        var command = new DeleteSanctionCommand(sanctionId);
        sanctionCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}