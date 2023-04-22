package es.taw.grupo25.service;

import es.taw.grupo25.dto.EstadoCliente;
import es.taw.grupo25.entity.EstadoClienteEntity;
import es.taw.grupo25.repository.EstadoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoClienteService {
    @Autowired
    protected EstadoClienteRepository estadoClienteRepository;

    public EstadoCliente findByEstado(String estado){
        EstadoClienteEntity estadoClienteEntity = estadoClienteRepository.findByEstado(estado);
        return estadoClienteEntity==null?null:estadoClienteEntity.toDTO();
    }
}
