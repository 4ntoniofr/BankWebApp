package es.taw.grupo25.repository;

import es.taw.grupo25.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Integer> {

    @Query("select t from TransaccionEntity t where t.cuentaBancariaByCuentaOrigen.id = :id")
    public List<TransaccionEntity> findTransaccionsForOrigin(@Param("id") Integer id);

    @Query("select t from TransaccionEntity t where t.cuentaBancariaByCuentaDestino.id = :id")
    public List<TransaccionEntity> findTransaccionsForDestination(@Param("id") Integer id);

}
