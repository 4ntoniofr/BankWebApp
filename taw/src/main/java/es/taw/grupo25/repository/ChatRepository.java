package es.taw.grupo25.repository;

import es.taw.grupo25.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where c.empleadoByEmpleadoId.id = :id")
    public List<Chat> findChatsByEmpleadoId(@Param("id") Integer id);

    @Query("select c from Chat c where c.clienteByClienteId.id = :id and c.fechaCierre = null")
    public Chat findChatAbiertoByClienteId(@Param("id") Integer id);


}
