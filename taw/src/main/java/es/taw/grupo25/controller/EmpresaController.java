package es.taw.grupo25.controller;

import es.taw.grupo25.entity.EstadoClienteEntity;
import es.taw.grupo25.entity.RolClienteEntity;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.ui.FormularioRegistroEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private ClienteRepository clienteRep;
    @Autowired
    private PersonaRepository personaRep;
    @Autowired
    private UsuarioRepository usuarioRep;
    @Autowired
    private EmpresaRepository empresaRep;
    @Autowired
    private RolClienteRepository rolClienteRep;
    @Autowired
    private EstadoClienteRepository estadoClienteRep;
    @Autowired
    private DireccionRepository direccionRep;

    @GetMapping("/")
    public String showOptions(){
        return "empresa/index";
    }

    @GetMapping("/registrar")
    public String doRegister(Model model){
        model.addAttribute("registroEmpresa", new FormularioRegistroEmpresa());
        return "empresa/register";
    }

    @PostMapping("/registrar")
    public String registerEmpresa(@ModelAttribute("registroEmpresa") FormularioRegistroEmpresa registroEmpresa){
        EstadoClienteEntity estadoEmpresa = this.estadoClienteRep.findByEstado("ACTIVO");
        registroEmpresa.getClienteEmpresa().setEstadoClienteByEstadoCliente(estadoEmpresa);
        this.usuarioRep.save(registroEmpresa.getUsuarioEmpresa());
        this.direccionRep.save(registroEmpresa.getClienteEmpresa().getDireccionByDireccion());
        this.clienteRep.save(registroEmpresa.getClienteEmpresa());
        this.empresaRep.save(registroEmpresa.getEmpresa());

        RolClienteEntity rolCliente = this.rolClienteRep.findByRol("AUTORIZADO");
        EstadoClienteEntity estadoAutorizado = this.estadoClienteRep.findByEstado("ACTIVO");
        registroEmpresa.getAsociadoEmpresa().getClienteAsociado().setRolClienteByRolClienteId(rolCliente);
        registroEmpresa.getAsociadoEmpresa().getClienteAsociado().setEstadoClienteByEstadoCliente(estadoAutorizado);
        this.personaRep.save(registroEmpresa.getAsociadoEmpresa().getPersonaAsociado());
        this.usuarioRep.save(registroEmpresa.getAsociadoEmpresa().getUsuarioAsociado());
        this.direccionRep.save(registroEmpresa.getAsociadoEmpresa().getClienteAsociado().getDireccionByDireccion());
        this.clienteRep.save(registroEmpresa.getAsociadoEmpresa().getClienteAsociado());

        return "redirect:/empresa/";
    }
}
