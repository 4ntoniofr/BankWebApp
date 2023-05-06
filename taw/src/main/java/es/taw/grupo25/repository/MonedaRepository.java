/**
 * @author Antonio Fernandez Rodriguez
 */

package es.taw.grupo25.repository;

import es.taw.grupo25.entity.MonedaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonedaRepository extends JpaRepository<MonedaEntity, Integer> {
}
