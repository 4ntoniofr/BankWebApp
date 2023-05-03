package es.taw.grupo25.service;

import es.taw.grupo25.dto.*;
import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpresaEntity;
import es.taw.grupo25.entity.EstadoClienteEntity;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.ui.FormularioRegistroAsociado;
import es.taw.grupo25.ui.FormularioRegistroEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmpresaRepository empresaRep;
    @Autowired
    private EstadoClienteRepository estadoClienteRep;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private RolClienteService rolClienteService;
    @Autowired
    private EstadoClienteService estadoClienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    public void guardarEmpresa(Empresa empresa){
        EmpresaEntity entity = getEntity(empresa);
        this.empresaRep.save(entity);
        empresa.setId(entity.getId());
    }

    private EmpresaEntity getEntity(Empresa empresa){
        EmpresaEntity entity = new EmpresaEntity();

        entity.setId(empresa.getId());
        entity.setNombre(empresa.getNombre());
        entity.setFechaCierre(empresa.getFechaCierre());

        ClienteEntity cliente = this.clienteRepository.findById(empresa.getClienteByClienteId().getId()).orElse(null);
        entity.setClienteByClienteId(cliente);

        return entity;
    }

    public void registrarEmpresa(FormularioRegistroEmpresa registroEmpresa){
        RolCliente rolCliente = this.rolClienteService.findByRol("AUTORIZADO");
        EstadoClienteEntity estadoEmpresa = this.estadoClienteRep.findByEstado("ACTIVO");

        registroEmpresa.getClienteEmpresa().setEstadoClienteByEstadoCliente(estadoEmpresa.toDTO());
        registroEmpresa.getClienteEmpresa().setRolClienteByRolClienteId(rolCliente);
        registroEmpresa.getClienteEmpresa().setUsuarioByUsuarioId(registroEmpresa.getUsuarioEmpresa());

        this.usuarioService.guardarUsuario(registroEmpresa.getUsuarioEmpresa());
        this.direccionService.saveDireccion(registroEmpresa.getClienteEmpresa().getDireccionByDireccion());
        this.clienteService.guardarCliente(registroEmpresa.getClienteEmpresa());
        this.guardarEmpresa(registroEmpresa.getEmpresa());

        registrarAutorizado(registroEmpresa.getAsociadoEmpresa(), registroEmpresa.getEmpresa());
    }

    public void registrarAutorizado(FormularioRegistroAsociado registroAsociado, Empresa empresa){
        EstadoCliente estadoAutorizado = this.estadoClienteService.findByEstado("ACTIVO");

        RolCliente rolCliente;
        if(registroAsociado.getRol().isEmpty()){
            rolCliente = this.rolClienteService.findByRol("SOCIO");
        }else{
            rolCliente = this.rolClienteService.findByRol(registroAsociado.getRol());
        }
        registroAsociado.getClienteAsociado().setRolClienteByRolClienteId(rolCliente);
        registroAsociado.getClienteAsociado().setEstadoClienteByEstadoCliente(estadoAutorizado);
        registroAsociado.getClienteAsociado().setEmpresaByEmpresaSocio(empresa);
        registroAsociado.getUsuarioAsociado().setClientesById(registroAsociado.getClienteAsociado());

        this.personaService.guardarPersona(registroAsociado.getPersonaAsociado());
        this.usuarioService.guardarUsuario(registroAsociado.getUsuarioAsociado());
        this.direccionService.saveDireccion(registroAsociado.getClienteAsociado().getDireccionByDireccion());
        this.clienteService.guardarCliente(registroAsociado.getClienteAsociado());
    }

    public List<Cliente> getListaSocios(Empresa empresa){
        List<Cliente> socios = this.clienteService.buscarSociosPorEmpresa(empresa);
        return socios;
    }
}
