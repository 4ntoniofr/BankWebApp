package es.taw.grupo25.service;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected EstadoClienteRepository estadoClienteRepository;
    @Autowired
    protected UsuarioRepository usuarioRepository;
    @Autowired
    protected RolClienteRepository rolClienteRepository;
    @Autowired
    protected PersonaRepository personaRepository;
    @Autowired
    protected DireccionRepository direccionRepository;

    public void guardarCliente(Cliente cliente){
        clienteRepository.save(getEntity(cliente));
    }

    private ClienteEntity getEntity(Cliente cliente){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId(cliente.getId());
        clienteEntity.setFechaInicio(cliente.getFechaInicio());
        clienteEntity.setChatsById(cliente.getChatsById());
        clienteEntity.setTransaccionsById(cliente.getTransaccionsById());

        DireccionEntity direccionEntity = direccionRepository.findById(cliente.getDireccionByDireccion().getId()).orElse(null);
        clienteEntity.setDireccionByDireccion(direccionEntity);

        EstadoClienteEntity estadoClienteEntity = estadoClienteRepository.findByEstado(cliente.getEstadoClienteByEstadoCliente().getEstado());
        clienteEntity.setEstadoClienteByEstadoCliente(estadoClienteEntity);

        UsuarioEntity usuarioEntity = usuarioRepository.findById(cliente.getUsuarioByUsuarioId().getId()).orElse(null);
        clienteEntity.setUsuarioByUsuarioId(usuarioEntity);

        PersonaEntity personaEntity = personaRepository.findById(cliente.getPersonaByPersonaId().getId()).orElse(null);
        clienteEntity.setPersonaByPersonaId(personaEntity);

        RolClienteEntity rolClienteEntity = rolClienteRepository.findByRol(cliente.getRolClienteByRolClienteId().getRol());
        clienteEntity.setRolClienteByRolClienteId(rolClienteEntity);


        return clienteEntity;
    }
}
