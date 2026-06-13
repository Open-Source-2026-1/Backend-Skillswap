package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionId;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Message save(Message message);
    Optional<Message> findById(Long id);
    List<Message> findBySessionId(SessionId sessionId);
    void deleteById(Long id);
}