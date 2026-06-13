package pe.edu.upc.skillswap.platform.skillswap_platform.workspace.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.entities.Message;
import pe.edu.upc.skillswap.platform.skillswap_platform.workspace.domain.model.valueobjects.SessionId;

import java.util.List;

@Repository
public interface MessageJpaRepository extends JpaRepository<Message, Long> {
    List<Message> findBySessionId(SessionId sessionId);
}