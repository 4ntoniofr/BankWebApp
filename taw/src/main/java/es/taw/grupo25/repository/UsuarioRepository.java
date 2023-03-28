package es.taw.grupo25.repository;

import es.taw.grupo25.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("select u from UsuarioEntity u where u.usuario = :user and u.password = :password")
    public UsuarioEntity autenticar (@Param("user") String user, @Param("password")String password);
}
