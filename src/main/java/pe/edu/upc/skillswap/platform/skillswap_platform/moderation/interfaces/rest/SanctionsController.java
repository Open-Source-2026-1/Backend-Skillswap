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

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/sanctions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sanctions", description = "Sanction Management Endpoints")
public class SanctionsController {

  private final SanctionQueryService sanctionQueryService;
  private final SanctionCommandService sanctionCommandService;

  public SanctionsController(SanctionQueryService sanctionQueryService,
                             SanctionCommandService sanctionCommandService) {
    this.sanctionQueryService = sanctionQueryService;
    this.sanctionCommandService = sanctionCommandService;
  }

  @PostMapping
  public ResponseEntity<SanctionResource> createSanction(@RequestBody CreateSanctionResource resource) {
    var command = CreateSanctionCommandFromResourceAssembler.toCommandFromResource(resource);
    var sanctionId = this.sanctionCommandService.handle(command);
    if (sanctionId.equals(0L)) return ResponseEntity.badRequest().build();
    var sanction = this.sanctionQueryService.handle(new GetSanctionByIdQuery(sanctionId));
    if (sanction.isEmpty()) return ResponseEntity.badRequest().build();
    var sanctionResource = SanctionResourceFromEntityAssembler.toResourceFromEntity(sanction.get());
    return new ResponseEntity<>(sanctionResource, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<SanctionResource>> getAllSanctions() {
    var sanctions = this.sanctionQueryService.handle(new GetAllSanctionsQuery());
    var resources = sanctions.stream()
            .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/{sanctionId}")
  public ResponseEntity<SanctionResource> getSanctionById(@PathVariable Long sanctionId) {
    var sanction = this.sanctionQueryService.handle(new GetSanctionByIdQuery(sanctionId));
    if (sanction.isEmpty()) return ResponseEntity.notFound().build();
    var resource = SanctionResourceFromEntityAssembler.toResourceFromEntity(sanction.get());
    return ResponseEntity.ok(resource);
  }

  @GetMapping("/by-report/{reportId}")
  public ResponseEntity<List<SanctionResource>> getSanctionsByReport(@PathVariable Long reportId) {
    var sanctions = this.sanctionQueryService.handle(new GetSanctionsByReportIdQuery(reportId));
    var resources = sanctions.stream()
            .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/by-user/{userId}")
  public ResponseEntity<List<SanctionResource>> getSanctionsByUser(@PathVariable Long userId) {
    var sanctions = this.sanctionQueryService.handle(new GetSanctionsByUserQuery(userId));
    var resources = sanctions.stream()
            .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @PutMapping("/{sanctionId}")
  public ResponseEntity<SanctionResource> updateSanction(@PathVariable Long sanctionId,
                                                         @RequestBody UpdateSanctionResource resource) {
    var command = UpdateSanctionCommandFromResourceAssembler.toCommandFromResource(sanctionId, resource);
    var sanction = this.sanctionCommandService.handle(command);
    if (sanction.isEmpty()) return ResponseEntity.notFound().build();
    var sanctionResource = SanctionResourceFromEntityAssembler.toResourceFromEntity(sanction.get());
    return ResponseEntity.ok(sanctionResource);
  }

  @DeleteMapping("/{sanctionId}")
  public ResponseEntity<?> deleteSanction(@PathVariable Long sanctionId) {
    var command = new DeleteSanctionCommand(sanctionId);
    this.sanctionCommandService.handle(command);
    return ResponseEntity.noContent().build();
  }
}