/**
 * @author Jose Fco Artacho
 */
package es.taw.grupo25.controller;

import es.taw.grupo25.dto.*;
import es.taw.grupo25.service.*;
import es.taw.grupo25.ui.FiltroSociosEmpresa;
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
    @Autowired
    private TransaccionService transaccionService;
    @Autowired
    private RolClienteService rolClienteService;

    @GetMapping("/")
    public String showOptions(){
        return "empresa/index";
    }

    @GetMapping("/registrar")
    public String doRegister(Model model, HttpSession session){
        String urlTo = "empresa/register";

        if(session.getAttribute("usuario") != null){
            urlTo = "empresa/index";
            model.addAttribute("error", "La acción seleccionada no está disponible");
        }else{
            model.addAttribute("registroEmpresa", new FormularioRegistroEmpresa());
        }

        return urlTo;
    }

    @PostMapping("/registrar")
    public String registerEmpresa(@ModelAttribute("registroEmpresa") FormularioRegistroEmpresa registroEmpresa){
        this.empresaService.registrarEmpresa(registroEmpresa);

        return "redirect:/empresa/";
    }

    @GetMapping("/addAsociado")
    public String showAddAsociado(Model model, HttpSession session){
        String urlTo = "empresa/addAsociado";
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getClientesById().getEmpresasById() == null){
            urlTo = "empresa/index";
            model.addAttribute("error", "La acción seleccionada no está disponible");
        }else{
            model.addAttribute("registroAsociado", new FormularioRegistroAsociado());
            model.addAttribute("roles", this.rolClienteService.findRolesEmpresa());
        }

        return urlTo;
    }

    @PostMapping("/addAsociado")
    public String addAsociado(@ModelAttribute("registroAsociado") FormularioRegistroAsociado registroAsociado,
                              HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Empresa empresa;

        if(usuario.getClientesById().getEmpresasById() != null){
            empresa = usuario.getClientesById().getEmpresasById();
        }else{
            empresa = usuario.getClientesById().getEmpresaByEmpresaSocio();
        }
        this.empresaService.registrarAutorizado(registroAsociado, empresa);

        return "redirect:/empresa/";
    }

    @GetMapping("/login")
    public String getLogin(HttpSession session, Model model){
        String urlTo = "empresa/login";

        if(session.getAttribute("usuario") != null){
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
            session.setAttribute("usuario", user);
        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/updateAsociado")
    public String getUpdateForm(Model model, HttpSession session){
        String urlTo = "/empresa/addAsociado";
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getClientesById().getRolClienteByRolClienteId().getRol().equals("INDIVIDUAL")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            FormularioRegistroAsociado registroAsociado = new FormularioRegistroAsociado();
            registroAsociado.setClienteAsociado(usuario.getClientesById());
            registroAsociado.setPersonaAsociado(usuario.getClientesById().getPersonaByPersonaId());
            registroAsociado.setUsuarioAsociado(usuario);
            model.addAttribute("registroAsociado", registroAsociado);
        }

        return urlTo;
    }

    @GetMapping("/sociosEmpresa")
    public String getSociosEmpresa(Model model, HttpSession session){
        return mostrarSocios(model, session);
    }

    @PostMapping("/sociosEmpresa/filtro")
    public String getSociosFiltrados(Model model, HttpSession session,
                                     @ModelAttribute("filtro") FiltroSociosEmpresa filtro){
        return mostrarSocios(model, session, filtro);
    }

    private String mostrarSocios(Model model, HttpSession session, FiltroSociosEmpresa... filtro){
        String urlTo = "empresa/sociosEmpresa";
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getClientesById() == null ||
                usuario.getClientesById().getEmpresaByEmpresaSocio() == null ||
                !usuario.getClientesById().getRolClienteByRolClienteId().getRol().equals("SOCIO")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            List<Cliente> socios;
            if(filtro.length == 0){
                socios = this.clienteService.buscarSociosConPersonaPorEmpresa(usuario.getClientesById().getEmpresaByEmpresaSocio());
                model.addAttribute("filtro", new FiltroSociosEmpresa());
            }else{
                socios = this.clienteService.buscarSociosConPersonaPorEmpresaFiltro(usuario.getClientesById().getEmpresaByEmpresaSocio(), filtro[0]);
            }
            model.addAttribute("socios", socios);
            model.addAttribute("empresa", usuario.getClientesById().getEmpresaByEmpresaSocio());

            model.addAttribute("roles", this.rolClienteService.findRolesEmpresa());
            model.addAttribute("estados", this.estadoClienteService.findAll());
        }
        return urlTo;
    }

    @GetMapping("/bloquearSocio")
    public String doBloquearSocio(Model model, HttpSession session,
                                  @RequestParam("idCliente") Integer idCliente){
        String urlTo = "redirect:/empresa/sociosEmpresa";
        Usuario solicitante = (Usuario) session.getAttribute("usuario");

        if(solicitante == null || solicitante.getClientesById() == null ||
                solicitante.getClientesById().getEmpresasById() != null ||
                !solicitante.getClientesById().getRolClienteByRolClienteId().getRol().equals("SOCIO")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            Cliente bloqueado = this.clienteService.findById(idCliente);
            if(bloqueado == null){
                urlTo = "empresa/index";
                model.addAttribute("error", "Cliente a bloquear no existente");
            }else if(!bloqueado.getEmpresaByEmpresaSocio().equals(solicitante.getClientesById().getEmpresaByEmpresaSocio())){
                urlTo = "empresa/index";
                model.addAttribute("error", "Cliente a bloquear perteneciente a otra empresa");
            }else{
                bloqueado.setEstadoClienteByEstadoCliente(this.estadoClienteService.findByEstado("BLOQUEADO"));
                this.clienteService.guardarCliente(bloqueado);
                if(bloqueado.equals(solicitante.getClientesById())){
                    solicitante.setClientesById(bloqueado);
                    session.setAttribute("usuario", solicitante);
                }
            }
        }

        return urlTo;
    }

    @GetMapping("/solicitudDesbloqueo")
    public String doSolicitudDesbloqueo(HttpSession session, Model model){
        String urlTo = "redirect:/empresa/";
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getClientesById() == null ||
                usuario.getClientesById().getEstadoClienteByEstadoCliente() == null ||
            !usuario.getClientesById().getEstadoClienteByEstadoCliente().getEstado().equals("BLOQUEADO")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            usuario.getClientesById().setEstadoClienteByEstadoCliente(this.estadoClienteService.findByEstado("SOLICITADO"));
            this.clienteService.guardarCliente(usuario.getClientesById());
            session.setAttribute("usuario", usuario);
        }

        return urlTo;
    }

    @GetMapping("/transferencias")
    public String doMostrarTransferencias(Model model, HttpSession session){
        String urlTo = "empresa/transferencias";
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null || usuario.getClientesById() == null ||
                usuario.getClientesById().getEmpresasById() != null ||
                usuario.getClientesById().getRolClienteByRolClienteId().getRol().equals("INDIVIDUAL")){
            urlTo = "empresa/index";
            model.addAttribute("error", "Accion no permitida");
        }else{
            List<Transaccion> transaccions = this.transaccionService.findAllByCliente(usuario.getClientesById().getEmpresaByEmpresaSocio().getClienteByClienteId());
            model.addAttribute("transacciones", transaccions);
        }

        return urlTo;
    }
}
