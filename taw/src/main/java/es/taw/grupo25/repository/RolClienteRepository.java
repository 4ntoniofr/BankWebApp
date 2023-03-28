package es.taw.grupo25.repository;

import es.taw.grupo25.entity.RolClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolClienteRepository extends JpaRepository<RolClienteEntity,Integer> {
    public RolClienteEntity findByRol(String rol);
}
