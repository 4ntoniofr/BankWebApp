package es.taw.grupo25.controller;

import es.taw.grupo25.dto.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.taw.grupo25.service.UsuarioService;


@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    protected UsuarioService usuarioService;

    @GetMapping("")
    public String doLogin(HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlto = "cliente/login";
        if(usuario != null){
            urlto = "redirect:/cliente";
        }
        return urlto;
    }

    @PostMapping("")
    public String doAutenticar(@RequestParam("usuario") String user,
                    @RequestParam("clave") String contrasena,
                    Model model, HttpSession session){
        String urlTo = "redirect:/cliente";
        Usuario usuario = usuarioService.doAutenticarUsuario(user, contrasena);
        if(usuario==null){
            model.addAttribute("error", "Credenciales Incorrectas");
            urlTo="/cliente/login";
        }else{
            session.setAttribute("usuario", usuario);
        }
        return urlTo;
    }
}
