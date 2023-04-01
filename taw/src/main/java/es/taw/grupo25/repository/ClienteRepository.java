package es.taw.grupo25.repository;

import es.taw.grupo25.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Integer> {

    @Query("select c from ClienteEntity c where c.empleadoByAutorizador = null")
    public List<ClienteEntity> clientesNoAutorizados();
}
