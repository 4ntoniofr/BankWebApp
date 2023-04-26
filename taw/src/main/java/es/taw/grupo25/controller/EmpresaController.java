package es.taw.grupo25.controller;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Empresa;
import es.taw.grupo25.dto.EstadoCliente;
import es.taw.grupo25.dto.Usuario;
import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.service.ClienteService;
import es.taw.grupo25.service.EmpresaService;
import es.taw.grupo25.service.EstadoClienteService;
import es.taw.grupo25.service.UsuarioService;
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
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private EstadoClienteService estadoClienteService;

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

        this.empresaService.registrarEmpresa(registroEmpresa);
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
        Empresa empresa = ((Cliente) session.getAttribute("cliente")).getEmpresasById();
        this.empresaService.registrarAutorizado(registroAsociado, empresa);

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
        Usuario user = this.usuarioService.doAutenticarUsuario(username,password);

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
        Cliente cliente = (Cliente) session.getAttribute("cliente");

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
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if(cliente == null || cliente.getEmpresaByEmpresaSocio() == null){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            List<Cliente> socios = this.clienteService.buscarSociosConPersonaPorEmpresa(cliente.getEmpresasById());
            model.addAttribute("socios", socios);
            model.addAttribute("empresa", cliente.getEmpresaByEmpresaSocio());
        }

        return urlTo;
    }

    @GetMapping("/bloquearSocio")
    public String doBloquearSocio(Model model, HttpSession session,
                                  @RequestParam("idCliente") Integer idCliente){
        String urlTo = "redirect:/empresa/sociosEmpresa";
        Cliente solicitante = (Cliente) session.getAttribute("cliente");

        if(solicitante == null || solicitante.getEmpresasById() != null ||
                solicitante.getRolClienteByRolClienteId().getRol().equals("INDIVIDUAL")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            Cliente bloqueado = this.clienteService.findById(idCliente);
            if(bloqueado == null){
                urlTo = "empresa/index";
                model.addAttribute("error", "Cliente a bloquear no existente");
            }else if(!bloqueado.getEmpresaByEmpresaSocio().equals(solicitante.getEmpresaByEmpresaSocio())){
                urlTo = "empresa/index";
                model.addAttribute("error", "Cliente a bloquear perteneciente a otra empresa");
            }else{
                bloqueado.setEstadoClienteByEstadoCliente(this.estadoClienteService.findByEstado("BLOQUEADO"));
                this.clienteService.guardarCliente(bloqueado);
            }
        }

        return urlTo;
    }

    @GetMapping("/solicitudDesbloqueo")
    public String doSolicitudDesbloqueo(HttpSession session, Model model){
        String urlTo = "redirect:/empresa/";
        Cliente cliente = (Cliente) session.getAttribute("cliente");

        if(cliente == null || cliente.getEstadoClienteByEstadoCliente() == null ||
            !cliente.getEstadoClienteByEstadoCliente().getEstado().equals("BLOQUEADO")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            cliente.setEstadoClienteByEstadoCliente(this.estadoClienteService.findByEstado("SOLICITADO"));
            this.clienteService.guardarCliente(cliente);
            session.setAttribute("cliente", cliente);
        }

        return urlTo;
    }
}
