package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Integer> {
}
