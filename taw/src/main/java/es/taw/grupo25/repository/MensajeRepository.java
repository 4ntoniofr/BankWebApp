package es.taw.grupo25.repository;

import es.taw.grupo25.entity.MensajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<MensajeEntity, Integer> {
}
