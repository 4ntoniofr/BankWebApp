/**
 * @author Antonio Fernandez Rodriguez - 50%
 * @author Valentin Garcia Rosas - 50%
 */

package es.taw.grupo25.repository;

import es.taw.grupo25.entity.CuentaInternaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaInternaRepository extends JpaRepository<CuentaInternaEntity, Integer> {

    @Query("select c from CuentaInternaEntity c where c.clienteByPropietario.id = :id")
    public List<CuentaInternaEntity> findCuentasInternasForClient(@Param("id") Integer id);

    @Query("select c from CuentaInternaEntity c where c.estadoCuentaByEstadoCuenta.estado = 'SOLICITADO'")
    public List<CuentaInternaEntity> findCuentaInternasSolicitantes();
}
