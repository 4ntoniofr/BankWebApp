package es.taw.grupo25.controller;

import es.taw.grupo25.entity.UsuarioEntity;
import es.taw.grupo25.repository.RolClienteRepository;
import es.taw.grupo25.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    protected UsuarioRepository rep;

    @GetMapping("")
    public String doLogin(HttpSession session){
        UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
        String urlto = "cliente/login";
        if(usuario != null){
            urlto = "redirect:/cliente";
        }
        return urlto;
    }

    @PostMapping("")
    public String d(@RequestParam("usuario") String user,
                    @RequestParam("clave") String contrasena,
                    Model model, HttpSession session){
        String urlTo = "redirect:/cliente";
        UsuarioEntity usuario = rep.autenticar(user, contrasena);
        if(usuario==null){
            model.addAttribute("error", "Credenciales Incorrectas");
            urlTo="redirect:/";
        }else{
            session.setAttribute("usuario", usuario);
        }
        return urlTo;
    }
}
