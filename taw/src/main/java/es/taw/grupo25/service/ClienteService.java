package es.taw.grupo25.service;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Empleado;
import es.taw.grupo25.dto.Empresa;
import es.taw.grupo25.dto.Transaccion;
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
    @Autowired
    protected TransaccionRepository transaccionRepository;
    @Autowired
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected EmpleadoRepository empleadoRepository;

    public void guardarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = getEntity(cliente);
        clienteRepository.save(clienteEntity);
        cliente.setId(clienteEntity.getId());
    }

    public void autorizarCliente(Integer idCliente, Integer idGestor) {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        EmpleadoEntity gestor = this.empleadoRepository.findById(idGestor).orElse(null);
        cliente.setEmpleadoByAutorizador(gestor);
        this.clienteRepository.save(cliente);
    }

    private ClienteEntity getEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId(cliente.getId());
        clienteEntity.setFechaInicio(cliente.getFechaInicio());
        //clienteEntity.setChatsById(cliente.getChatsById());

        List<TransaccionEntity> transacciones = new ArrayList<>();
        if (cliente.getTransaccionsById() != null) {
            for (Transaccion t : cliente.getTransaccionsById()) {
                transacciones.add(this.transaccionRepository.findById(t.getId()).orElse(null));
            }
        }
        clienteEntity.setTransaccionsById(transacciones);

        DireccionEntity direccionEntity = direccionRepository.findById(cliente.getDireccionByDireccion().getId()).orElse(null);
        clienteEntity.setDireccionByDireccion(direccionEntity);

        EstadoClienteEntity estadoClienteEntity = estadoClienteRepository.findByEstado(cliente.getEstadoClienteByEstadoCliente().getEstado());
        clienteEntity.setEstadoClienteByEstadoCliente(estadoClienteEntity);

        UsuarioEntity usuarioEntity;
        if (cliente.getUsuarioByUsuarioId() == null) {
            usuarioEntity = this.usuarioRepository.getUsuarioFromCliente(cliente.getId());
        } else {
            usuarioEntity = this.usuarioRepository.findById(cliente.getUsuarioByUsuarioId().getId()).orElse(null);
        }
        clienteEntity.setUsuarioByUsuarioId(usuarioEntity);

        PersonaEntity personaEntity = cliente.getPersonaByPersonaId() == null ? null :
                personaRepository.findById(cliente.getPersonaByPersonaId().getId()).orElse(null);
        clienteEntity.setPersonaByPersonaId(personaEntity);

        RolClienteEntity rolClienteEntity = rolClienteRepository.findByRol(cliente.getRolClienteByRolClienteId().getRol());
        clienteEntity.setRolClienteByRolClienteId(rolClienteEntity);

        EmpresaEntity empresa = cliente.getEmpresasById() == null ? null :
                empresaRepository.findById(cliente.getEmpresasById().getId()).orElse(null);
        clienteEntity.setEmpresasById(empresa);

        EmpresaEntity empresaSocio = cliente.getEmpresaByEmpresaSocio() == null ? null :
                empresaRepository.findById(cliente.getEmpresaByEmpresaSocio().getId()).orElse(null);
        clienteEntity.setEmpresaByEmpresaSocio(empresaSocio);

        EmpleadoEntity autorizador = !cliente.getAutorizador() ? null :
                clienteRepository.getAutorizadorFromCliente(cliente.getId());
        clienteEntity.setEmpleadoByAutorizador(autorizador);

        return clienteEntity;
    }

    public List<Cliente> buscarSociosConPersonaPorEmpresa(Empresa empresa) {
        return listaEntidadesADTO(this.clienteRepository.buscarSociosConPersonaPorEmpresa(empresa.getId()));
    }

    public Cliente findById(Integer id) {
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        return cliente == null ? null : cliente.toDTO();
    }

    public static List<Cliente> listaEntidadesADTO(List<ClienteEntity> clientes) {
        List<Cliente> dtos = new ArrayList<>();
        for (ClienteEntity entity : clientes) {
            dtos.add(entity.toDTO());
        }
        return dtos;
    }

    public static List<Cliente> listaEntidadesADTOConEmpresa(List<ClienteEntity> clientes, Empresa empresa) {
        List<Cliente> dtos = new ArrayList<>();
        for (ClienteEntity entity : clientes) {
            dtos.add(entity.toDTO());
        }
        return dtos;
    }

    public List<Cliente> clientesNoAutorizados() {
        List<ClienteEntity> clientes = this.clienteRepository.clientesNoAutorizados();
        return listaEntidadesADTO(clientes);
    }

    public List<Cliente> clientesAutorizados() {
        List<ClienteEntity> clientes = this.clienteRepository.clientesAutorizados();
        return listaEntidadesADTO(clientes);
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

    public List<Cliente> buscarSociosPorEmpresa(Empresa empresa) {
        return listaEntidadesADTO(this.clienteRepository.buscarSociosPorEmpresa(empresa.getId()));
    }
}
