package es.taw.grupo25.service;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(getEntity(cliente));
    }

    private ClienteEntity getEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId(cliente.getId());
        clienteEntity.setFechaInicio(cliente.getFechaInicio());

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

    public List<Cliente> clientesNoAutorizados() {
        List<ClienteEntity> clientes = this.clienteRepository.clientesNoAutorizados();
        return listaEntidadesADTO(clientes);
    }

    public List<Cliente> clientesAutorizados() {
        List<ClienteEntity> clientes = this.clienteRepository.clientesAutorizados();
        return listaEntidadesADTO(clientes);
    }

    private List<Cliente> listaEntidadesADTO(List<ClienteEntity> lista) {
        List<Cliente> dtos = new ArrayList<>();
        for (ClienteEntity cliente : lista) {
            dtos.add(cliente.toDTO());
        }
        return dtos;
    }

    public Cliente findById(int id) {
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return null;
        }
        return cliente.toDTO();
    }

    public void borrarCliente(Integer id) {
        this.clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarIndividualesPorNombre(String texto) {
        return listaEntidadesADTO(this.clienteRepository.buscarIndividualesPorNombre(texto));
    }

    public List<Cliente> buscarEmpresaPorNombre(String texto) {
        return listaEntidadesADTO(this.clienteRepository.buscarEmpresaPorNombre(texto));
    }

    public List<Cliente> buscarPorEstado(String estadoCliente) {
        return listaEntidadesADTO(this.clienteRepository.buscarPorEstado(estadoCliente));
    }

    public List<Cliente> buscarIndividualesPorNombreYEstado(String texto, String estadoCliente) {
        return listaEntidadesADTO(this.clienteRepository.buscarIndividualesPorNombreYEstado(texto, estadoCliente));
    }

    public List<Cliente> buscarEmpresaPorNombreYEstado(String texto, String estadoCliente) {
        return listaEntidadesADTO(this.clienteRepository.buscarEmpresaPorNombreYEstado(texto, estadoCliente));
    }

    public List<Cliente> buscarInactivos(Date date) {
        return listaEntidadesADTO(this.clienteRepository.buscarInactivos(date));
    }

    public List<Cliente> buscarSospechosos() {
        return listaEntidadesADTO(this.clienteRepository.buscarSospechosos());
    }
}
