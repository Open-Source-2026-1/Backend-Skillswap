package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.GetReviewsByTutorIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources.CreateReviewResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.interfaces.rest.resources.MessageResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.interfaces.rest.transform.ResponseEntityAssembler;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Review Management Endpoints")
public class ReviewsController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    public ReviewsController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewResource resource) {
        var command = CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = reviewCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                reviewId -> new MessageResource("Review created successfully with ID: " + reviewId),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<?> getReviewsByTutorId(@PathVariable Long tutorId) {
        var result = reviewQueryService.handle(new GetReviewsByTutorIdQuery(tutorId));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                reviews -> reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}