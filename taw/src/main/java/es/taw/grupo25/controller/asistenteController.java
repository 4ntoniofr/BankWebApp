package es.taw.grupo25.controller;

import es.taw.grupo25.dto.*;
import es.taw.grupo25.service.ChatService;
import es.taw.grupo25.service.MensajeService;
import es.taw.grupo25.service.UsuarioService;
import es.taw.grupo25.ui.FiltroChats;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jorge Camacho García
 */
@Controller()
@RequestMapping("/asistente")
public class asistenteController {

    @Autowired
    protected ChatService chatService;

    @Autowired
    protected MensajeService mensajeService;

    @Autowired
    protected UsuarioService usuarioService;

    @GetMapping("/")
    public String doPaginaAsistente(Model model) {
        return "redirect:/asistente/chats";
    }

    @GetMapping("/chats")
    public String getChats(Model model, HttpSession session) {
        return this.procesarFiltrado(null, model, session);
    }

    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroChats filtro, Model model, HttpSession session) {
        return this.procesarFiltrado(filtro, model, session);
    }

    public String procesarFiltrado(FiltroChats filtro, Model model, HttpSession session) {

        Empleado empleadoQueSoy = (Empleado) session.getAttribute("asistente");
        if (empleadoQueSoy == null) {
            return "redirect:/asistente/iniciar-sesion";
        } else {
            model.addAttribute("empleado", empleadoQueSoy);
            List<Chat> chats = chatService.findChatsByEmpleadoId(empleadoQueSoy.getId());

            if (filtro == null) {
                filtro = new FiltroChats();
            } else {
                // Filtros
                if (!filtro.getNombre().isEmpty()) {
                    String nombre = filtro.getNombre().toLowerCase();
                    chats = chats.stream().filter(obj -> obj.getClienteByClienteId().getPersonaByPersonaId().getNombre().toLowerCase().contains(nombre)).collect(Collectors.toList());
                }
                if (!filtro.getUltimoMensajeAntesDe().isEmpty()) {
                    LocalDateTime fecha = LocalDateTime.parse(filtro.getUltimoMensajeAntesDe());
                    chats = chats.stream().filter(obj -> obj.getMensajesById().size() > 0 && obj.getMensajesById().get(obj.getMensajesById().size() - 1).getFecha().toLocalDateTime().isAfter(fecha)).collect(Collectors.toList());
                }
                if (filtro.isSoloAbiertos()) {
                    chats = chats.stream().filter(obj -> obj.getFechaCierre() == null).collect(Collectors.toList());
                }

                // Orden
                if (filtro.getOrderBy().equals("1")) {
                    chats.sort(Comparator.comparing(chat -> chat.getMensajesById().isEmpty() ? Timestamp.valueOf(LocalDateTime.MAX) : chat.getMensajesById().get(chat.getMensajesById().size() - 1).getFecha()));
                    Collections.reverse(chats);
                } else if (filtro.getOrderBy().equals("2")) {
                    chats.sort(Comparator.comparing(chat -> chat.getClienteByClienteId().getPersonaByPersonaId().getNombre()));
                } else if (filtro.getOrderBy().equals("3")) {
                    chats.sort(Comparator.comparing(Chat::getId));
                } else if (filtro.getOrderBy().equals("4")) {
                    chats.sort(Comparator.comparing(Chat::getId));
                    Collections.reverse(chats);
                }
            }

            model.addAttribute("chats", chats);
            model.addAttribute("filtro", filtro);
        }

        return "/asistente/chats";
    }


    @GetMapping("/chat")
    public String getChat(Model model, @RequestParam("id") Integer chatId, HttpSession session) {
        Chat chat = chatService.findById(chatId);
        Empleado empleadoQueSoy = (Empleado) session.getAttribute("asistente");

        if (chat == null || empleadoQueSoy == null || chat.getEmpleadoByEmpleadoId().getId() != empleadoQueSoy.getId()) {
            return "redirect:/asistente/chats";
        }
        model.addAttribute("chat", chat);

        List<Mensaje> mensajes = chat.getMensajesById();
        model.addAttribute("mensajes", mensajes);

        Mensaje siguienteMensaje = new Mensaje();
        model.addAttribute("siguienteMensaje", siguienteMensaje);

        Cliente cliente = chat.getClienteByClienteId();
        model.addAttribute("cliente", cliente);

        model.addAttribute("rol", "a");

        return "/asistente/chat";
    }

    @PostMapping("/enviar-mensaje")
    public String doEnviarMensaje(@ModelAttribute("siguienteMensaje") Mensaje mensaje) {
        mensajeService.enviarMensaje(mensaje);
        return "redirect:/asistente/chat?id=" + mensaje.getIdChatNuevoMensaje();
    }

    /* SE PODRÍA IMPLEMENTAR ESTA FUNCIÓN PARA QUE EL ASISTENTE PUEDA CERRAR TAMBIÉN EL CHAT, PERO NO SE ESPECIFICA ESTO
    @PostMapping("/cerrar-chat")
    public String doCerrarChat(Model mode, @RequestParam("id") Integer chatId){
        Chat chat = chatService.findById(chatId);
        chatService.cerrarChat(chat);

        return "redirect:/asistente/chats";
    }*/

    /*
    @GetMapping("/iniciar-sesion")
    public String getIniciarSesion() {
        return "/asistente/login";
    }

    @PostMapping("/iniciar-sesion")
    public String doIniciarSesion(@RequestParam("usuario") String user, @RequestParam("clave") String contrasena, Model model, HttpSession session) {
        String urlTo = "/asistente/login";
        Usuario usuario = this.usuarioService.doAutenticarUsuario(user, contrasena);
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
        } else {
            Empleado empleadoFromUsuario = usuario.getEmpleadosById();
            if (empleadoFromUsuario == null) {
                model.addAttribute("error", "No eres empleado");
            } else if (!empleadoFromUsuario.getRolEmpleadoByRolEmpleadoId().getId().equals(2)) {
                model.addAttribute("error", "No eres asistente");
            } else {
                urlTo = "redirect:/asistente/";
                session.setAttribute("asistente", empleadoFromUsuario);
            }
        }

        return urlTo;
    }

    @GetMapping("/cerrar-sesion")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/asistente/";
    }
    */

}