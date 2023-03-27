package es.taw.grupo25.repository;

import es.taw.grupo25.entity.PersonaEntity;
import es.taw.grupo25.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {
}
