package es.taw.grupo25.repository;

import es.taw.grupo25.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Integer> {

    @Query("select e from EmpleadoEntity e where e.id = :id")
    public EmpleadoEntity findById(@Param("id")String id);
}
