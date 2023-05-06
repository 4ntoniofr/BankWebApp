/**
 * @author Antonio Fernandez Rodriguez - 50%
 * @author Jose Fco Artacho - 50%
 */

package es.taw.grupo25.repository;

import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Integer> {

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador = null")
    public List<ClienteEntity> clientesNoAutorizados();

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador != null")
    public List<ClienteEntity> clientesAutorizados();

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador != null and c.personaByPersonaId != null and (c.personaByPersonaId.nombre like CONCAT('%', :filtro, '%') or c.personaByPersonaId.primerApellido like CONCAT('%', :filtro, '%') or c.personaByPersonaId.segundoApellido like CONCAT('%', :filtro, '%') or c.personaByPersonaId.dni like CONCAT('%', :filtro, '%'))")
    public List<ClienteEntity> buscarIndividualesPorNombre(@Param("filtro") String filtro);

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador != null and c.empresasById != null and c.empresasById.nombre like CONCAT('%', :filtro, '%')")
    public List<ClienteEntity> buscarEmpresaPorNombre(@Param("filtro") String filtro);

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador != null and c.estadoClienteByEstadoCliente.estado = :filtro")
    public List<ClienteEntity> buscarPorEstado(@Param("filtro") String filtro);

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador != null and c.personaByPersonaId != null and (c.personaByPersonaId.nombre like CONCAT('%', :filtro, '%') or c.personaByPersonaId.primerApellido like CONCAT('%', :filtro, '%') or c.personaByPersonaId.segundoApellido like CONCAT('%', :filtro, '%') or c.personaByPersonaId.dni like CONCAT('%', :filtro, '%')) and c.estadoClienteByEstadoCliente.estado = :estado")
    public List<ClienteEntity> buscarIndividualesPorNombreYEstado(@Param("filtro") String filtro, @Param("estado") String estado);

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador != null and c.empresasById != null and c.empresasById.nombre like CONCAT('%', :filtro, '%') and c.estadoClienteByEstadoCliente.estado = :estado")
    public List<ClienteEntity> buscarEmpresaPorNombreYEstado(@Param("filtro") String filtro, @Param("estado") String estado);

    @Query("select distinct c from ClienteEntity c where exists(select ci from CuentaInternaEntity ci where ci.clienteByPropietario = c and ci.estadoCuentaByEstadoCuenta.estado like 'ACTIVA') and not exists (select ci from CuentaInternaEntity ci join ci.cuentaBancariaByCuentaBancaria cb left join cb.transaccionsById_Entrantes t where ci.estadoCuentaByEstadoCuenta.estado LIKE 'ACTIVA' and ci.clienteByPropietario = c and t.fechaEjecucion >= :fechaLimite) and not exists (select ci from CuentaInternaEntity ci join ci.cuentaBancariaByCuentaBancaria cb left join cb.transaccionsById_Salientes t where ci.estadoCuentaByEstadoCuenta.estado LIKE 'ACTIVA' and ci.clienteByPropietario = c and t.fechaEjecucion >= :fechaLimite)")
    public List<ClienteEntity> buscarInactivos(@Param("fechaLimite") Date fecha);

    //@Query("select c from ClienteEntity c where exists (select ci from CuentaInternaEntity ci join ci.cuentaBancariaByCuentaBancaria.transaccionsById_Salientes t where ci.estadoCuentaByEstadoCuenta.estado LIKE 'ACTIVA' and ci.clienteByPropietario = c and t.cuentaBancariaByCuentaDestino.cuentaExternasById.sospechosa = 1)")
    @Query("select c from ClienteEntity c where exists (select t from TransaccionEntity t where t.cuentaBancariaByCuentaOrigen.cuentaInternasById.clienteByPropietario = c and t.cuentaBancariaByCuentaDestino.cuentaExternasById.sospechosa = 1)")
    public List<ClienteEntity> buscarSospechosos();

    @Query("select c from ClienteEntity c  left join fetch c.personaByPersonaId where c.empresaByEmpresaSocio.id = :id")
    public List<ClienteEntity> buscarSociosConPersonaPorEmpresa(@Param("id") Integer id);

    @Query("select c from ClienteEntity c  left join fetch c.personaByPersonaId where c.empresaByEmpresaSocio.id = :id and c.rolClienteByRolClienteId.rol = :rol")
    public List<ClienteEntity> buscarSociosConPersonaPorEmpresaRol(@Param("id") Integer id, @Param("rol") String rol);

    @Query("select c from ClienteEntity c  left join fetch c.personaByPersonaId where c.empresaByEmpresaSocio.id = :id and c.estadoClienteByEstadoCliente.estado = :estado")
    public List<ClienteEntity> buscarSociosConPersonaPorEmpresaEstado(@Param("id") Integer id, @Param("estado") String estado);

    @Query("select c from ClienteEntity c  left join fetch c.personaByPersonaId where c.empresaByEmpresaSocio.id = :id and c.rolClienteByRolClienteId.rol = :rol and c.estadoClienteByEstadoCliente.estado = :estado")
    public List<ClienteEntity> buscarSociosConPersonaPorEmpresaRolEstado(@Param("id") Integer id, @Param("rol") String rol, @Param("estado") String estado);

    @Query("select c from ClienteEntity c where c.empresaByEmpresaSocio.id = :id")
    public List<ClienteEntity> buscarSociosPorEmpresa(@Param("id") Integer id);

    @Query("select c.empleadoByAutorizador from ClienteEntity c where c.id = :id")
    public EmpleadoEntity getAutorizadorFromCliente(@Param("id") Integer id);
}
