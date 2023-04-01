package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EstadoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuentaEntity, Integer> {

    public EstadoCuentaEntity findByEstado(String estado);

}
