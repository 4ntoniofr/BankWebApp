package es.taw.grupo25.repository;

import es.taw.grupo25.entity.CuentaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Integer> {
}
