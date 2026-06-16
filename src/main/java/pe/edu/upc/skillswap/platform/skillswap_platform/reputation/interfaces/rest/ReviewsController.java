package pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.commands.DeleteReviewCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.domain.services.ReviewQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.reputation.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Reviews Management Endpoints")
public class ReviewsController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    public ReviewsController(ReviewCommandService reviewCommandService,
                             ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource) {
        var command = CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource);
        var reviewId = this.reviewCommandService.handle(command);
        if (reviewId.equals(0L)) return ResponseEntity.badRequest().build();
        var review = this.reviewQueryService.handle(new GetReviewByIdQuery(reviewId));
        if (review.isEmpty()) return ResponseEntity.badRequest().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return new ResponseEntity<>(reviewResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        var reviews = this.reviewQueryService.handle(new GetAllReviewsQuery());
        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable Long reviewId) {
        var review = this.reviewQueryService.handle(new GetReviewByIdQuery(reviewId));
        if (review.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByTutorId(@PathVariable Long tutorId) {
        var reviews = this.reviewQueryService.handle(new GetReviewsByTutorIdQuery(tutorId));
        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByLearnerId(@PathVariable Long learnerId) {
        var reviews = this.reviewQueryService.handle(new GetReviewsByLearnerIdQuery(learnerId));
        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long reviewId,
                                                       @RequestBody UpdateReviewResource resource) {
        var command = UpdateReviewCommandFromResourceAssembler.toCommandFromResource(reviewId, resource);
        var review = this.reviewCommandService.handle(command);
        if (review.isEmpty()) return ResponseEntity.notFound().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(reviewResource);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        var command = new DeleteReviewCommand(reviewId);
        this.reviewCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}