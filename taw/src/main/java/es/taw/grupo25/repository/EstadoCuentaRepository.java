package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EstadoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstadoCuentaRepository extends JpaRepository<EstadoCuentaEntity, Integer> {

    @Query("select e from EstadoCuentaEntity e where e.estado=:estado")
    public EstadoCuentaEntity findByEstado(@Param("estado") String estado);

}
