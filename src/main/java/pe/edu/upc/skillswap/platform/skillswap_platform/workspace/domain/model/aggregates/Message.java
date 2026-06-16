package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.skillswap.platform.skillswap_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.commands.CreateMessageCommand;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends AuditableAbstractAggregateRoot<Message> {

    @Getter
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Getter
    @NotNull
    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Getter
    @NotNull
    @Column(name = "session_id", nullable = false)
    private Long sessionId;

    @Getter
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    public Message() {}

    public Message(CreateMessageCommand command) {
        this.content = command.content();
        this.senderId = command.senderId();
        this.sessionId = command.sessionId();
        this.sentAt = LocalDateTime.now();
    }
}