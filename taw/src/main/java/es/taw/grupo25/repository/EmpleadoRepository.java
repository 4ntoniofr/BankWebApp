package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Integer> {
}
