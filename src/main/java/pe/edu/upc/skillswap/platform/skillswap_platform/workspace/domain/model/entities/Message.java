package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.aggregates.TutoringSession;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionId;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "session_id", nullable = false))
    })
    private SessionId sessionId;

    @Setter
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", insertable = false, updatable = false)
    private TutoringSession session;

    public Message() {
        this.sentAt = LocalDateTime.now();
    }

    public Message(String content, Long senderId, Long sessionId) {
        this();
        this.content = content;
        this.senderId = senderId;
        this.sessionId = new SessionId(sessionId);
    }
}