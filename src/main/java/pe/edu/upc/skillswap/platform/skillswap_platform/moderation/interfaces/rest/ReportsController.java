package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.CloseReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Report Management Endpoints")
public class ReportsController {

  private final ReportQueryService reportQueryService;
  private final ReportCommandService reportCommandService;

  public ReportsController(ReportQueryService reportQueryService,
                           ReportCommandService reportCommandService) {
    this.reportQueryService = reportQueryService;
    this.reportCommandService = reportCommandService;
  }

  @PostMapping
  public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource resource) {
    var command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
    var reportId = this.reportCommandService.handle(command);
    if (reportId.equals(0L)) return ResponseEntity.badRequest().build();
    var report = this.reportQueryService.handle(new GetReportByIdQuery(reportId));
    if (report.isEmpty()) return ResponseEntity.badRequest().build();
    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
    return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ReportResource>> getAllReports() {
    var reports = this.reportQueryService.handle(new GetAllReportsQuery());
    var resources = reports.stream()
            .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/{reportId}")
  public ResponseEntity<ReportResource> getReportById(@PathVariable Long reportId) {
    var report = this.reportQueryService.handle(new GetReportByIdQuery(reportId));
    if (report.isEmpty()) return ResponseEntity.notFound().build();
    var resource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
    return ResponseEntity.ok(resource);
  }

  @GetMapping("/active")
  public ResponseEntity<List<ReportResource>> getActiveReports() {
    var reports = this.reportQueryService.handle(new GetActiveReportsQuery());
    var resources = reports.stream()
            .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/resolved")
  public ResponseEntity<List<ReportResource>> getResolvedReports() {
    var reports = this.reportQueryService.handle(new GetResolvedReportsQuery());
    var resources = reports.stream()
            .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @GetMapping("/by-reported-user/{reportedUserId}")
  public ResponseEntity<List<ReportResource>> getReportsByReportedUser(
          @PathVariable Long reportedUserId) {
    var reports = this.reportQueryService.handle(new GetReportsByReportedUserQuery(reportedUserId));
    var resources = reports.stream()
            .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
    return ResponseEntity.ok(resources);
  }

  @PutMapping("/{reportId}")
  public ResponseEntity<ReportResource> updateReport(@PathVariable Long reportId,
                                                     @RequestBody UpdateReportResource resource) {
    var command = UpdateReportCommandFromResourceAssembler.toCommandFromResource(reportId, resource);
    var report = this.reportCommandService.handle(command);
    if (report.isEmpty()) return ResponseEntity.notFound().build();
    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
    return ResponseEntity.ok(reportResource);
  }

  @PatchMapping("/{reportId}/close")
  public ResponseEntity<ReportResource> closeReport(@PathVariable Long reportId) {
    var command = new CloseReportCommand(reportId);
    var report = this.reportCommandService.handle(command);
    if (report.isEmpty()) return ResponseEntity.notFound().build();
    var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
    return ResponseEntity.ok(reportResource);
  }

  @DeleteMapping("/{reportId}")
  public ResponseEntity<?> deleteReport(@PathVariable Long reportId) {
    var command = new DeleteReportCommand(reportId);
    this.reportCommandService.handle(command);
    return ResponseEntity.noContent().build();
  }
}