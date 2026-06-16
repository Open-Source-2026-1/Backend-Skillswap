package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CloseReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetActiveReportsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetAllReportsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetReportByIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetReportsByReportedUserQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.GetResolvedReportsQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.CreateReportResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.ReportResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.UpdateReportResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.UpdateReportCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH })
@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Report Management Endpoints")
public class ReportsController {

  private final ReportQueryService reportQueryService;
  private final ReportCommandService reportCommandService;

  public ReportsController(ReportQueryService reportQueryService, ReportCommandService reportCommandService) {
    this.reportQueryService = reportQueryService;
    this.reportCommandService = reportCommandService;
  }

  @PostMapping
  public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource resource) {

    var createReportCommand = CreateReportCommandFromResourceAssembler
        .toCommandFromResource(resource);
    var reportId = this.reportCommandService.handle(createReportCommand);

    if (reportId.equals(0L)) {
      return ResponseEntity.badRequest().build();
    }

    var getReportByIdQuery = new GetReportByIdQuery(reportId);
    var optionalReport = this.reportQueryService.handle(getReportByIdQuery);

    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(optionalReport.get());
    return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ReportResource>> getAllReports() {
    var getAllReportsQuery = new GetAllReportsQuery();
    var reports = this.reportQueryService.handle(getAllReportsQuery);
    var reportResources = reports.stream()
        .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(reportResources);
  }

  @GetMapping("/{reportId}")
  public ResponseEntity<ReportResource> getReportById(@PathVariable Long reportId) {
    var getReportByIdQuery = new GetReportByIdQuery(reportId);
    var optionalReport = this.reportQueryService.handle(getReportByIdQuery);
    if (optionalReport.isEmpty())
      return ResponseEntity.badRequest().build();
    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(optionalReport.get());
    return ResponseEntity.ok(reportResource);
  }

  @GetMapping("/active")
  public ResponseEntity<List<ReportResource>> getActiveReports() {
    var getActiveReportsQuery = new GetActiveReportsQuery();
    var reports = this.reportQueryService.handle(getActiveReportsQuery);
    var reportResources = reports.stream()
        .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(reportResources);
  }

  @GetMapping("/resolved")
  public ResponseEntity<List<ReportResource>> getResolvedReports() {
    var getResolvedReportsQuery = new GetResolvedReportsQuery();
    var reports = this.reportQueryService.handle(getResolvedReportsQuery);
    var reportResources = reports.stream()
        .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(reportResources);
  }

  @GetMapping("/by-reported-user/{reportedUserId}")
  public ResponseEntity<List<ReportResource>> getReportsByReportedUser(@PathVariable Long reportedUserId) {
    var getReportsByReportedUserQuery = new GetReportsByReportedUserQuery(reportedUserId);
    var reports = this.reportQueryService.handle(getReportsByReportedUserQuery);
    var reportResources = reports.stream()
        .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.ok(reportResources);
  }

  @PutMapping("/{reportId}")
  public ResponseEntity<ReportResource> updateReport(@PathVariable Long reportId, @RequestBody UpdateReportResource resource) {
    var updateReportCommand = UpdateReportCommandFromResourceAssembler.toCommandFromResource(reportId, resource);
    var optionalReport = this.reportCommandService.handle(updateReportCommand);

    if (optionalReport.isEmpty())
      return ResponseEntity.badRequest().build();
    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(optionalReport.get());
    return ResponseEntity.ok(reportResource);
  }

  @PatchMapping("/{reportId}/close")
  public ResponseEntity<ReportResource> closeReport(@PathVariable Long reportId) {
    var closeReportCommand = new CloseReportCommand(reportId);
    var optionalReport = this.reportCommandService.handle(closeReportCommand);

    if (optionalReport.isEmpty())
      return ResponseEntity.badRequest().build();
    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(optionalReport.get());
    return ResponseEntity.ok(reportResource);
  }

  @DeleteMapping("/{reportId}")
  public ResponseEntity<?> deleteReport(@PathVariable Long reportId) {
    var deleteReportCommand = new DeleteReportCommand(reportId);
    this.reportCommandService.handle(deleteReportCommand);
    return ResponseEntity.noContent().build();
  }
}
