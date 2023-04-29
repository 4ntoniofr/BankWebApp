package es.taw.grupo25.repository;

import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Integer> {

    @Query("select t from TransaccionEntity t where t.cuentaBancariaByCuentaOrigen.id = :id")
    public List<TransaccionEntity> findTransactionsForOrigin(@Param("id") Integer id);

    @Query("select t from TransaccionEntity t where t.cuentaBancariaByCuentaDestino.id = :id")
    public List<TransaccionEntity> findTransactionsForDestination(@Param("id") Integer id);

    @Query("select t from TransaccionEntity  t where t.cuentaBancariaByCuentaDestino.id = :id or t.cuentaBancariaByCuentaOrigen.id = :id")
    public List<TransaccionEntity> findAllTransactionsById(@Param("id") Integer id);

    @Query("select t from TransaccionEntity t where t.clienteByCliente.id = :id")
    public List<TransaccionEntity> findByCliente(@Param("id") Integer id);

    @Query("select t from TransaccionEntity t where t.cuentaBancariaByCuentaOrigen.id = :idCuenta and t.clienteByCliente = :cliente")
    public List<TransaccionEntity> findAllByIdCuentaAndCliente(@Param("idCuenta") Integer idCuenta,
                                                               @Param("cliente") ClienteEntity cliente);
}
