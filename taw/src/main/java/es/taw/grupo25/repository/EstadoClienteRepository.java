package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EstadoClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoClienteRepository extends JpaRepository<EstadoClienteEntity,Integer> {
    public EstadoClienteEntity findByEstado(String estado);
}
