package es.taw.grupo25.service;

import es.taw.grupo25.dto.EstadoCliente;
import es.taw.grupo25.entity.EstadoClienteEntity;
import es.taw.grupo25.repository.EstadoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoClienteService {
    @Autowired
    protected EstadoClienteRepository estadoClienteRepository;

    public EstadoCliente findByEstado(String estado){
        EstadoClienteEntity estadoClienteEntity = estadoClienteRepository.findByEstado(estado);
        return estadoClienteEntity==null?null:estadoClienteEntity.toDTO();
    }

    public List<EstadoCliente> findAll(){
        return listaEntidadesADTO(this.estadoClienteRepository.findAll());
    }

    private List<EstadoCliente> listaEntidadesADTO(List<EstadoClienteEntity> entidades){
        List<EstadoCliente> estadoClientes = new ArrayList<>();
        for (EstadoClienteEntity estado: entidades) {
            estadoClientes.add(estado.toDTO());
        }
        return estadoClientes;
    }
}
