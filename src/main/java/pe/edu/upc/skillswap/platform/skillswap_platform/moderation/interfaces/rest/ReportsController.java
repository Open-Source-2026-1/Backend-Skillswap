package pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.DeleteReportCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.valueobjects.ReportStatus;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.services.ReportQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.interfaces.rest.transform.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.moderation.domain.model.commands.UpdateReportStatusCommand;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Reports Management Endpoints")
public class ReportsController {

    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    public ReportsController(ReportCommandService reportCommandService,
                             ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    @PostMapping
    public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource resource) {
        var command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var report = reportCommandService.handle(command);
        if (report.isEmpty()) return ResponseEntity.badRequest().build();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
        return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReportResource>> getAllReports() {
        var query = new GetAllReportsQuery();
        var reports = reportQueryService.handle(query);
        var resources = reports.stream()
                .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResource> getReportById(@PathVariable Long reportId) {
        var query = new GetReportByIdQuery(reportId);
        var report = reportQueryService.handle(query);
        if (report.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/reporter/{reporterId}")
    public ResponseEntity<List<ReportResource>> getReportsByReporterId(@PathVariable Long reporterId) {
        var query = new GetReportsByReporterIdQuery(reporterId);
        var reports = reportQueryService.handle(query);
        var resources = reports.stream()
                .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReportResource>> getReportsByStatus(@PathVariable ReportStatus status) {
        var query = new GetReportsByStatusQuery(status);
        var reports = reportQueryService.handle(query);
        var resources = reports.stream()
                .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{reportId}/status")
    public ResponseEntity<ReportResource> updateReportStatus(@PathVariable Long reportId,
                                                             @RequestBody UpdateReportStatusResource resource) {
        var command = new UpdateReportStatusCommand(reportId, resource.status());
        var report = reportCommandService.handle(command);
        if (report.isEmpty()) return ResponseEntity.notFound().build();
        var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
        return ResponseEntity.ok(reportResource);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable Long reportId) {
        var command = new DeleteReportCommand(reportId);
        reportCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}