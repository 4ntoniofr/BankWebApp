package es.taw.grupo25.repository;

import es.taw.grupo25.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {
    @Query("select p from PagoEntity p where p.transaccionByTransaccion.cuentaBancariaByCuentaOrigen.id=:id or p.transaccionByTransaccion.cuentaBancariaByCuentaDestino.id=:id")
    public List<PagoEntity> findByCuentaId(@Param("id")Integer id);
}
