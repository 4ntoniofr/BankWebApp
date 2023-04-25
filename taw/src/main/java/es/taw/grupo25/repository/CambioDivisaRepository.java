package es.taw.grupo25.repository;

import es.taw.grupo25.entity.CambioDivisaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CambioDivisaRepository extends JpaRepository<CambioDivisaEntity, Integer> {
    @Query("select c from CambioDivisaEntity c where c.transaccionByTransaccion.cuentaBancariaByCuentaOrigen.id=:id")
    List<CambioDivisaEntity> findByCuentaId(@Param("id") Integer id);
}
