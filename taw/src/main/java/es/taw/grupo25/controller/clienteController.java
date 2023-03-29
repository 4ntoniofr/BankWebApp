package es.taw.grupo25.controller;

import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.ui.FormularioRegistroCliente;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/cliente")
public class clienteController {

    @Autowired
    private PersonaRepository rep_persona;

    @Autowired
    private UsuarioRepository rep_usuario;

    @Autowired
    private EstadoClienteRepository rep_estado_cliente;

    @Autowired
    private DireccionRepository rep_direccion;

    @Autowired
    private ClienteRepository rep_cliente;

    @Autowired
    private RolClienteRepository rep_rol_cliente;

    @GetMapping("")
    public String cliente(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlto = "cliente/cliente";
        if(usuario==null){
            urlto="redirect:/login";
        }
        return urlto;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, HttpSession session){
        String urlto = "cliente/perfil";
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        System.out.println("usuario: " + usuario);
        if(usuario == null){
            urlto = "redirect:/login";
        }else{
            model.addAttribute("persona", usuario.getClientesById().getPersonaByPersonaId());
        }
        return urlto;
    }

    @GetMapping("/operaciones")
    public String listarOperaciones(Model model, HttpSession session){
        return "cliente/operaciones";
    }

    @GetMapping("/divisas")
    public String cambiarDivisas(Model model, HttpSession session){
        return "cliente/divisas";
    }

    @GetMapping("/transferencia")
    public String hacerTransferencia(Model model, HttpSession session){
        return "cliente/transferencia";
    }

    @GetMapping("/desbloqueo")
    public String intentarDesbloqueo(Model model, HttpSession session){
        return "cliente/desbloqueo";
    }

    @GetMapping("/register")
    public String registerCliente(Model model, HttpSession session){
        model.addAttribute("registroCliente", new FormularioRegistroCliente());
        return "cliente/register";
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute("registroCliente") FormularioRegistroCliente registroCliente, HttpSession session){
        EstadoClienteEntity estadoCliente = rep_estado_cliente.findByEstado("ACTIVO");
        RolClienteEntity rolCliente = rep_rol_cliente.findByRol("INDIVIDUAL");
        registroCliente.getCliente().setEstadoClienteByEstadoCliente(estadoCliente);
        registroCliente.getCliente().setRolClienteByRolClienteId(rolCliente);
        this.rep_persona.save(registroCliente.getPersona());
        this.rep_usuario.save(registroCliente.getUsuario());
        this.rep_direccion.save(registroCliente.getCliente().getDireccionByDireccion());
        this.rep_cliente.save(registroCliente.getCliente());


        return "redirect:/cliente/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/cliente";
    }


}
