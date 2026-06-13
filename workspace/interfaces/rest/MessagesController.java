package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.DeleteMessageCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.queries.GetAllMessagesBySessionIdQuery;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.services.MessageQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.CreateMessageResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.resources.MessageResource;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform.CreateMessageCommandFromResourceAssembler;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.interfaces.rest.transform.MessageResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
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
        var message = messageCommandService.handle(command);
        if (message.isEmpty()) return ResponseEntity.badRequest().build();
        var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(message.get());
        return new ResponseEntity<>(messageResource, HttpStatus.CREATED);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<MessageResource>> getMessagesBySessionId(@PathVariable Long sessionId) {
        var query = new GetAllMessagesBySessionIdQuery(sessionId);
        var messages = messageQueryService.handle(query);
        var resources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long messageId) {
        var command = new DeleteMessageCommand(messageId);
        messageCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
}