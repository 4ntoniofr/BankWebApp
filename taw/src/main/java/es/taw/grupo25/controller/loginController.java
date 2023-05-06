package es.taw.grupo25.controller;

import es.taw.grupo25.dto.Empleado;
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
public class loginController {

    @Autowired
    protected UsuarioService usuarioService;

    @GetMapping("/")
    public String showMain(HttpSession session){
        String urlTo = "main";

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null){
            urlTo = "redirect:/login";
        }

        return urlTo;
    }

    @GetMapping("/login")
    public String doLogin(HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String urlto = "login";
        if(usuario != null){
            urlto = "redirect:/";
        }
        return urlto;
    }

    @PostMapping("/login")
    public String doAutenticar(@RequestParam("usuario") String user,
                    @RequestParam("clave") String contrasena,
                    Model model, HttpSession session){
        String urlTo = "redirect:/";
        Usuario usuario = usuarioService.doAutenticarUsuario(user, contrasena);
        if(usuario==null){
            model.addAttribute("error", "Credenciales Incorrectas");
            urlTo="/login";
        }else{
            session.setAttribute("usuario", usuario);
            setAsistente(session, usuario);
        }
        return urlTo;
    }

    private void setAsistente(HttpSession session, Usuario usuario){
        Empleado empleadoFromUsuario = usuario.getEmpleadosById();
        if (empleadoFromUsuario != null && empleadoFromUsuario.getRolEmpleadoByRolEmpleadoId().getId().equals(2)) {
            session.setAttribute("asistente", empleadoFromUsuario);
        }
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
