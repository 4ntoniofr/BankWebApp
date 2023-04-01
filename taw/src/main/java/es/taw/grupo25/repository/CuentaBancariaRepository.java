package es.taw.grupo25.repository;

import es.taw.grupo25.entity.CuentaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {

    @Query("select c from CuentaBancariaEntity c where c.iban = :iban")
    public CuentaBancariaEntity findByIban(@Param("iban") String iban);
}
