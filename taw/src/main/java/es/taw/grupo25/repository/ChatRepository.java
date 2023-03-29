package es.taw.grupo25.repository;

import es.taw.grupo25.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {

    @Query("select c from ChatEntity c where c.empleadoByEmpleadoId.id = :id")
    public List<ChatEntity> findChatsByEmpleadoId(@Param("id") String id);
}