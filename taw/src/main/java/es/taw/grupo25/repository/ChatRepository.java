package es.taw.grupo25.repository;

import es.taw.grupo25.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
}
