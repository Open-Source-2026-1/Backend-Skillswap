package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.DeleteDonationCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.UpdateDonationStatusCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.DonationCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.DonationQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/donations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Donations", description = "Donations Management Endpoints")
public class DonationsController {

    private final DonationCommandService donationCommandService;
    private final DonationQueryService donationQueryService;

    public DonationsController(DonationCommandService donationCommandService,
                               DonationQueryService donationQueryService) {
        this.donationCommandService = donationCommandService;
        this.donationQueryService = donationQueryService;
    }

    @PostMapping
    public ResponseEntity<DonationResource> createDonation(@RequestBody CreateDonationResource resource) {
        var command = CreateDonationCommandFromResourceAssembler.toCommandFromResource(resource);
        var donation = donationCommandService.handle(command);
        if (donation.isEmpty()) return ResponseEntity.badRequest().build();
        var donationResource = DonationResourceFromEntityAssembler.toResourceFromEntity(donation.get());
        return new ResponseEntity<>(donationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonationResource>> getAllDonations() {
        var query = new GetAllDonationsQuery();
        var donations = donationQueryService.handle(query);
        var resources = donations.stream()
                .map(DonationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{donationId}")
    public ResponseEntity<DonationResource> getDonationById(@PathVariable Long donationId) {
        var query = new GetDonationByIdQuery(donationId);
        var donation = donationQueryService.handle(query);
        if (donation.isEmpty()) return ResponseEntity.notFound().build();
        var resource = DonationResourceFromEntityAssembler.toResourceFromEntity(donation.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<DonationResource>> getDonationsByDonorId(@PathVariable Long donorId) {
        var query = new GetDonationsByDonorIdQuery(donorId);
        var donations = donationQueryService.handle(query);
        var resources = donations.stream()
                .map(DonationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<DonationResource>> getDonationsByTutorId(@PathVariable Long tutorId) {
        var query = new GetDonationsByTutorIdQuery(tutorId);
        var donations = donationQueryService.handle(query);
        var resources = donations.stream()
                .map(DonationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{donationId}/status")
    public ResponseEntity<DonationResource> updateDonationStatus(@PathVariable Long donationId,
                                                                 @RequestBody UpdateDonationStatusResource resource) {
        var command = new UpdateDonationStatusCommand(donationId, resource.status());
        var donation = donationCommandService.handle(command);
        if (donation.isEmpty()) return ResponseEntity.notFound().build();
        var donationResource = DonationResourceFromEntityAssembler.toResourceFromEntity(donation.get());
        return ResponseEntity.ok(donationResource);
    }

    @DeleteMapping("/{donationId}")
    public ResponseEntity<?> deleteDonation(@PathVariable Long donationId) {
        var command = new DeleteDonationCommand(donationId);
        donationCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}