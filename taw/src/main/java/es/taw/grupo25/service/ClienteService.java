package es.taw.grupo25.service;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    protected ClienteRepository clienteRepository;

    public ClienteEntity getEntity(Cliente cliente){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId(cliente.getId());
        clienteEntity.setFechaInicio(cliente.getFechaInicio());
        clienteEntity.setChatsById(cliente.getChatsById());
        clienteEntity.setEstadoClienteByEstadoCliente(cliente.getEstadoClienteByEstadoCliente());
        clienteEntity.setDireccionByDireccion(cliente.getDireccionByDireccion());
        clienteEntity.setUsuarioByUsuarioId(cliente.getUsuarioByUsuarioId());
        clienteEntity.setPersonaByPersonaId(cliente.getPersonaByPersonaId());
        clienteEntity.setRolClienteByRolClienteId(cliente.getRolClienteByRolClienteId());
        clienteEntity.setTransaccionsById(cliente.getTransaccionsById());

        return clienteEntity;
    }
}
