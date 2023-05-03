package es.taw.grupo25.service;

import es.taw.grupo25.dto.Direccion;
import es.taw.grupo25.entity.DireccionEntity;
import es.taw.grupo25.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Valentín García Rosas
 *
 */

@Service
public class DireccionService {

    @Autowired
    DireccionRepository direccionRepository;

    public void saveDireccion(Direccion direccion){
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setId(direccion.getId());
        direccionEntity.setCalle(direccion.getCalle());
        direccionEntity.setNumero(direccion.getNumero());
        direccionEntity.setCiudad(direccion.getCiudad());
        direccionEntity.setCodigoPostal(direccion.getCodigoPostal());
        direccionEntity.setPais(direccion.getPais());
        direccionRepository.save(direccionEntity);
        direccion.setId(direccionEntity.getId());
    }
}
