package es.taw.grupo25.controller;

import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.ui.FormularioRegistroAsociado;
import es.taw.grupo25.ui.FormularioRegistroEmpresa;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showOptions(Model model){
        model.addAttribute("empresas",this.empresaRep.findAll());
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

    @GetMapping("/addAsociado")
    public String showAddAsociado(Model model){
        model.addAttribute("registroAsociado", new FormularioRegistroAsociado());
        return "empresa/addAsociado";
    }

    @PostMapping("/addAsociado")
    public String addAsociado(@ModelAttribute("registroAsociado") FormularioRegistroAsociado registroAsociado,
                              HttpSession session){
        RolClienteEntity rolCliente = this.rolClienteRep.findByRol("AUTORIZADO");
        EstadoClienteEntity estadoAutorizado = this.estadoClienteRep.findByEstado("ACTIVO");
        registroAsociado.getClienteAsociado().setRolClienteByRolClienteId(rolCliente);
        registroAsociado.getClienteAsociado().setEstadoClienteByEstadoCliente(estadoAutorizado);

        EmpresaEntity empresa = ((ClienteEntity) session.getAttribute("cliente")).getEmpresasById();
        registroAsociado.getClienteAsociado().setEmpresaByEmpresaSocio(empresa);

        this.personaRep.save(registroAsociado.getPersonaAsociado());
        this.usuarioRep.save(registroAsociado.getUsuarioAsociado());
        this.direccionRep.save(registroAsociado.getClienteAsociado().getDireccionByDireccion());
        this.clienteRep.save(registroAsociado.getClienteAsociado());

        return "redirect:/empresa/";
    }

    @GetMapping("/login")
    public String getLogin(HttpSession session){
        String urlTo = "/empresa/login";

        if(session.getAttribute("cliente") != null){
            urlTo = "redirect:/empresa/";
        }

        return urlTo;
    }

    @PostMapping("/login")
    public String doLogin(HttpSession session,
                          @RequestParam("username") String username,
                          @RequestParam("pwd") String password){

        String urlTo = "redirect:/empresa/";
        UsuarioEntity user = this.usuarioRep.autenticar(username,password);

        if(user == null || user.getClientesById() == null){

        }else{
            session.setAttribute("cliente", user.getClientesById());
        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/empresa/";
    }

    @GetMapping("/updateAsociado")
    public String getUpdateForm(Model model, HttpSession session){
        String urlTo = "/empresa/addAsociado";
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");

        if(cliente == null || cliente.getRolClienteByRolClienteId().getRol().equals("INDIVIDUAL")){
            urlTo = "redirect:/empresa";
        }else{
            FormularioRegistroAsociado registroAsociado = new FormularioRegistroAsociado();
            registroAsociado.setClienteAsociado(cliente);
            registroAsociado.setPersonaAsociado(cliente.getPersonaByPersonaId());
            registroAsociado.setUsuarioAsociado(cliente.getUsuarioByUsuarioId());
            model.addAttribute("registroAsociado", registroAsociado);
        }

        return urlTo;
    }
}
