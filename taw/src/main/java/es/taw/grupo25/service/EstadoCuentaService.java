package es.taw.grupo25.service;

import es.taw.grupo25.dto.EstadoCuenta;
import es.taw.grupo25.entity.EstadoCuentaEntity;
import es.taw.grupo25.repository.EstadoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Valentín García Rosas
 *
 */
@Service
public class EstadoCuentaService {

    @Autowired
    EstadoCuentaRepository estadoCuentaRepository;

    public EstadoCuenta findById(Integer id){
        EstadoCuentaEntity estado = this.estadoCuentaRepository.findById(id).orElse(null);
        return estado==null?null:estado.toDTO();
    }

    public EstadoCuenta findByEstado(String estado){
        EstadoCuentaEntity estadoCuentaEntity = estadoCuentaRepository.findByEstado(estado);
        return estado==null?null:estadoCuentaEntity.toDTO();
    }

    public EstadoCuentaEntity getEntity(EstadoCuenta estadoCuenta){
        EstadoCuentaEntity estadoCuentaEntity = new EstadoCuentaEntity();

        estadoCuentaEntity.setId(estadoCuenta.getId());
        estadoCuentaEntity.setEstado(estadoCuenta.getEstado());

        return estadoCuentaEntity;
    }

}
