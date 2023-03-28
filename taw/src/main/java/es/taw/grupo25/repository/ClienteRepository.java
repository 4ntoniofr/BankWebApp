package es.taw.grupo25.repository;

import es.taw.grupo25.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Integer> {
}
