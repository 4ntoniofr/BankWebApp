package es.taw.grupo25.controller;

import es.taw.grupo25.entity.PersonaEntity;
import es.taw.grupo25.entity.UsuarioEntity;
import es.taw.grupo25.repository.MensajeRepository;
import es.taw.grupo25.repository.PersonaRepository;
import es.taw.grupo25.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cliente")
public class clienteController {

    @Autowired
    private PersonaRepository rep_persona;

    @Autowired
    private UsuarioRepository rep_usuario;

    @GetMapping("/")
    public String cliente(Model model, HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlto = "cliente/cliente";
        //if(usuario==null){
        //    urlto="redirect:/cliente/login";
        //}
        return urlto;
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session)
    {
        System.out.println(rep_usuario.findAll());
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlto = "cliente/login";
        if(usuario != null){
            urlto = "redirect:/cliente/";
        }
        return urlto;
    }

    @GetMapping("/perfil")
    public String perfil(Model model){
        System.out.println(rep_usuario.findAll());
        //model.addAttribute("persona", rep_persona.findAll().get(0));
        return "cliente/perfil";
    }

    @GetMapping("/operaciones")
    public String listarOperaciones(Model model){
        return "cliente/operaciones";
    }

    @GetMapping("/divisas")
    public String cambiarDivisas(Model model){
        return "cliente/divisas";
    }

    @GetMapping("/transferencia")
    public String hacerTransferencia(Model model){
        return "cliente/transferencia";
    }

    @GetMapping("/desbloqueo")
    public String intentarDesbloqueo(Model model){
        return "cliente/desbloqueo";
    }

}
