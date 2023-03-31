package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EstadoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuentaEntity, Integer> {
}
