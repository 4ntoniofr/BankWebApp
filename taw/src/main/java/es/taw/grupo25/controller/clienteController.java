package es.taw.grupo25.controller;

import es.taw.grupo25.repository.PersonaRepository;
import es.taw.grupo25.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class clienteController {

    @Autowired
    private UsuarioRepository rep_usuario;

    @Autowired
    private PersonaRepository rep_persona;

    @GetMapping("/cliente")
    public String cliente(Model model){
        return "/cliente/cliente";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "/cliente/login";
    }

    @GetMapping("/perfil")
    public String perfil(Model model){
        model.addAttribute("persona", rep_persona.findAll().get(0));
        return "/cliente/perfil";
    }

}
