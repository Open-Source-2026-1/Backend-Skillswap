package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Messages", description = "Messages Management Endpoints")
public class MessagesController {

    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    public MessagesController(MessageCommandService messageCommandService,
                              MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.messageQueryService = messageQueryService;
    }

    @PostMapping
    public ResponseEntity<MessageResource> createMessage(@RequestBody CreateMessageResource resource) {
        var command = CreateMessageCommandFromResourceAssembler.toCommandFromResource(resource);
        var messageId = this.messageCommandService.handle(command);
        if (messageId.equals(0L)) return ResponseEntity.badRequest().build();
        var message = this.messageQueryService.handle(new GetMessageByIdQuery(messageId));
        if (message.isEmpty()) return ResponseEntity.badRequest().build();
        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(message.get());
        return new ResponseEntity<>(messageResource, HttpStatus.CREATED);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<MessageResource>> getMessagesBySessionId(@PathVariable Long sessionId) {
        var messages = this.messageQueryService.handle(new GetAllMessagesBySessionIdQuery(sessionId));
        var resources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageResource> getMessageById(@PathVariable Long messageId) {
        var message = this.messageQueryService.handle(new GetMessageByIdQuery(messageId));
        if (message.isEmpty()) return ResponseEntity.notFound().build();
        var resource = MessageResourceFromEntityAssembler.toResourceFromEntity(message.get());
        return ResponseEntity.ok(resource);
    }
}