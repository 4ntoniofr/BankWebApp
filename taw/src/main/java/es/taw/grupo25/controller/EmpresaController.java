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

import java.util.List;

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
    public String doRegister(Model model, HttpSession session){
        String urlTo = "empresa/register";

        if(session.getAttribute("cliente") != null){
            urlTo = "empresa/index";
            model.addAttribute("error", "La acción seleccionada no está disponible");
        }else{
            model.addAttribute("registroEmpresa", new FormularioRegistroEmpresa());
        }

        return urlTo;
    }

    @PostMapping("/registrar")
    public String registerEmpresa(@ModelAttribute("registroEmpresa") FormularioRegistroEmpresa registroEmpresa,
                                  HttpSession session){
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
        registroEmpresa.getAsociadoEmpresa().getClienteAsociado().setEmpresaByEmpresaSocio(registroEmpresa.getEmpresa());
        this.personaRep.save(registroEmpresa.getAsociadoEmpresa().getPersonaAsociado());
        this.usuarioRep.save(registroEmpresa.getAsociadoEmpresa().getUsuarioAsociado());
        this.direccionRep.save(registroEmpresa.getAsociadoEmpresa().getClienteAsociado().getDireccionByDireccion());
        this.clienteRep.save(registroEmpresa.getAsociadoEmpresa().getClienteAsociado());

        session.setAttribute("cliente", registroEmpresa.getClienteEmpresa());

        return "redirect:/empresa/";
    }

    @GetMapping("/addAsociado")
    public String showAddAsociado(Model model, HttpSession session){
        String urlTo = "empresa/addAsociado";
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");

        if(cliente == null || cliente.getEmpresasById() == null){
            urlTo = "empresa/index";
            model.addAttribute("error", "La acción seleccionada no está disponible");
        }else{
            model.addAttribute("registroAsociado", new FormularioRegistroAsociado());
        }

        return urlTo;
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
    public String getLogin(HttpSession session, Model model){
        String urlTo = "empresa/login";

        if(session.getAttribute("cliente") != null){
            urlTo = "empresa/index";
            model.addAttribute("error", "Usuario ya registrado");
        }

        return urlTo;
    }

    @PostMapping("/login")
    public String doLogin(HttpSession session,
                          @RequestParam("username") String username,
                          @RequestParam("pwd") String password,
                          Model model){

        String urlTo = "redirect:/empresa/";
        UsuarioEntity user = this.usuarioRep.autenticar(username,password);

        if(user == null || user.getClientesById() == null){
            urlTo = "empresa/login";
            String error;
            if(user == null){
                error = "Usuario o contraseña incorrectos";
            }else{
                error = "El usuario seleccionado no es un cliente";
            }
            model.addAttribute("error", error);
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
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            FormularioRegistroAsociado registroAsociado = new FormularioRegistroAsociado();
            registroAsociado.setClienteAsociado(cliente);
            registroAsociado.setPersonaAsociado(cliente.getPersonaByPersonaId());
            registroAsociado.setUsuarioAsociado(cliente.getUsuarioByUsuarioId());
            model.addAttribute("registroAsociado", registroAsociado);
        }

        return urlTo;
    }

    @GetMapping("/sociosEmpresa")
    public String getSociosEmpresa(Model model, HttpSession session){
        String urlTo = "empresa/sociosEmpresa";
        ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");

        if(cliente == null || cliente.getEmpresaByEmpresaSocio() == null){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            List<ClienteEntity> socios = cliente.getEmpresaByEmpresaSocio().getClientesById_Socios();
            model.addAttribute("socios", socios);
            model.addAttribute("empresa", cliente.getEmpresaByEmpresaSocio());
        }

        return urlTo;
    }

    @GetMapping("/bloquearSocio")
    public String doBloquearSocio(Model model, HttpSession session,
                                  @RequestParam("idCliente") Integer idCliente){
        String urlTo = "empresa/sociosEmpresa";
        ClienteEntity solicitante = (ClienteEntity) session.getAttribute("cliente");

        if(solicitante == null || solicitante.getEmpresasById() != null ||
                solicitante.getRolClienteByRolClienteId().getRol().equals("INDIVIDUAL")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            ClienteEntity bloqueado = this.clienteRep.findById(idCliente).orElse(null);
            if(bloqueado == null){
                urlTo = "empresa/index";
                model.addAttribute("error", "Cliente a bloquear no existente");
            }else if(!bloqueado.getEmpresaByEmpresaSocio().equals(solicitante.getEmpresaByEmpresaSocio())){
                urlTo = "empresa/index";
                model.addAttribute("error", "Cliente a bloquear perteneciente a otra empresa");
            }else{
                bloqueado.setEstadoClienteByEstadoCliente(this.estadoClienteRep.findByEstado("BLOQUEADO"));
                this.clienteRep.save(bloqueado);
            }
        }

        return urlTo;
    }
}
