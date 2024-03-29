package es.taw.grupo25.repository;

import es.taw.grupo25.entity.RolClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolClienteRepository extends JpaRepository<RolClienteEntity,Integer> {
    public RolClienteEntity findByRol(String rol);

    @Query("select r from RolClienteEntity r where r.rol != 'INDIVIDUAL'")
    public List<RolClienteEntity> findRolesEmpresa();
}
