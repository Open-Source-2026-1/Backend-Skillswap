package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteSanctionCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetAllSanctionsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetSanctionByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetSanctionsByReportIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetSanctionsByUserQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.SanctionQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.CreateSanctionResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.SanctionResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.UpdateSanctionResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.CreateSanctionCommandFromResourceAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.SanctionResourceFromEntityAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.UpdateSanctionCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/sanctions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sanctions", description = "Sanction Management Endpoints")
public class SanctionsController {

  private final SanctionQueryService sanctionQueryService;
  private final SanctionCommandService sanctionCommandService;

  public SanctionsController(SanctionQueryService sanctionQueryService, SanctionCommandService sanctionCommandService) {
    this.sanctionQueryService = sanctionQueryService;
    this.sanctionCommandService = sanctionCommandService;
  }

  @PostMapping
  public ResponseEntity<SanctionResource> createSanction(@RequestBody CreateSanctionResource resource) {

    var createSanctionCommand = CreateSanctionCommandFromResourceAssembler
        .toCommandFromResource(resource);
    var sanctionId = this.sanctionCommandService.handle(createSanctionCommand);

    if (sanctionId.equals(0L)) {
      return ResponseEntity.badRequest().build();
    }

    var getSanctionByIdQuery = new GetSanctionByIdQuery(sanctionId);
    var optionalSanction = this.sanctionQueryService.handle(getSanctionByIdQuery);

    var sanctionResource = SanctionResourceFromEntityAssembler.toResourceFromEntity(optionalSanction.get());
    return new ResponseEntity<>(sanctionResource, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<SanctionResource>> getAllSanctions() {
    var getAllSanctionsQuery = new GetAllSanctionsQuery();
    var sanctions = this.sanctionQueryService.handle(getAllSanctionsQuery);
    var sanctionResources = sanctions.stream()
        .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(sanctionResources);
  }

  @GetMapping("/{sanctionId}")
  public ResponseEntity<SanctionResource> getSanctionById(@PathVariable Long sanctionId) {
    var getSanctionByIdQuery = new GetSanctionByIdQuery(sanctionId);
    var optionalSanction = this.sanctionQueryService.handle(getSanctionByIdQuery);
    if (optionalSanction.isEmpty())
      return ResponseEntity.badRequest().build();
    var sanctionResource = SanctionResourceFromEntityAssembler.toResourceFromEntity(optionalSanction.get());
    return ResponseEntity.ok(sanctionResource);
  }

  @GetMapping("/by-report/{reportId}")
  public ResponseEntity<List<SanctionResource>> getSanctionsByReport(@PathVariable Long reportId) {
    var getSanctionsByReportIdQuery = new GetSanctionsByReportIdQuery(reportId);
    var sanctions = this.sanctionQueryService.handle(getSanctionsByReportIdQuery);
    var sanctionResources = sanctions.stream()
        .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(sanctionResources);
  }

  @GetMapping("/by-user/{userId}")
  public ResponseEntity<List<SanctionResource>> getSanctionsByUser(@PathVariable Long userId) {
    var getSanctionsByUserQuery = new GetSanctionsByUserQuery(userId);
    var sanctions = this.sanctionQueryService.handle(getSanctionsByUserQuery);
    var sanctionResources = sanctions.stream()
        .map(SanctionResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(sanctionResources);
  }

  @PutMapping("/{sanctionId}")
  public ResponseEntity<SanctionResource> updateSanction(@PathVariable Long sanctionId, @RequestBody UpdateSanctionResource resource) {
    var updateSanctionCommand = UpdateSanctionCommandFromResourceAssembler.toCommandFromResource(sanctionId, resource);
    var optionalSanction = this.sanctionCommandService.handle(updateSanctionCommand);

    if (optionalSanction.isEmpty())
      return ResponseEntity.badRequest().build();
    var sanctionResource = SanctionResourceFromEntityAssembler.toResourceFromEntity(optionalSanction.get());
    return ResponseEntity.ok(sanctionResource);
  }

  @DeleteMapping("/{sanctionId}")
  public ResponseEntity<?> deleteSanction(@PathVariable Long sanctionId) {
    var deleteSanctionCommand = new DeleteSanctionCommand(sanctionId);
    this.sanctionCommandService.handle(deleteSanctionCommand);
    return ResponseEntity.noContent().build();
  }
}
