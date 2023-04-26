package es.taw.grupo25.repository;

import es.taw.grupo25.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {

    @Query("select m from MensajeEntity m where m.chatByChat.id = :id")
    public List<MensajeEntity> findMensajesByChatId(@Param("id") Integer chatId);
}
