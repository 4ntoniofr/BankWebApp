package es.taw.grupo25.controller;

import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import es.taw.grupo25.ui.FiltroChats;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller()
@RequestMapping("/asistente")
public class asistenteController {

    @Autowired
    protected ChatRepository chatRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected EmpleadoRepository empleadoRepository;

    @Autowired
    protected MensajeRepository mensajeRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String doPaginaAsistente(Model model) {
        return "/asistente/asistente";
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

        EmpleadoEntity empleadoQueSoy = (EmpleadoEntity) session.getAttribute("asistente");
        if (empleadoQueSoy == null) {
            return "redirect:/asistente/iniciar-sesion";
        } else {
            model.addAttribute("empleado", empleadoQueSoy);
            List<ChatEntity> chats = chatRepository.findChatsByEmpleadoId(empleadoQueSoy.getId());

            if (filtro == null || filtro.getNombre().isEmpty() && filtro.getUltimoMensajeAntesDe().isEmpty() && filtro.isAbierto()) {
                filtro = new FiltroChats();
            } else {

                if(!filtro.getNombre().isEmpty()){
                    String nombre = filtro.getNombre();
                    chats = chats.stream().filter(obj -> obj.getClienteByClienteId().getPersonaByPersonaId().getNombre().contains(nombre)).collect(Collectors.toList());
                }
                if(!filtro.getUltimoMensajeAntesDe().isEmpty()){
                    LocalDate fecha = LocalDate.parse(filtro.getUltimoMensajeAntesDe());
                    chats = chats.stream().filter(obj -> obj.getMensajesById().get(0).getFecha().toLocalDateTime().toLocalDate().isAfter(fecha)).collect(Collectors.toList());
                }
                if(!filtro.isAbierto()){
                    //TODO: cambiar el entity añadiendo el booleano
                    //chats = chats.stream().filter(obj -> obj..collect(Collectors.toList());
                }
            }

            model.addAttribute("chats", chats);
            model.addAttribute("filtro", filtro);
        }

        return "/asistente/chats";
    }


    @GetMapping("/chat")
    public String getChat(Model model, @RequestParam("id") Integer chatId) {
        ChatEntity chat = chatRepository.findById(chatId).orElse(null);
        if (chat == null) {
            // TODO: Página con un mensaje de error y con un enlace para redirigir a todos los chats
            return "redirect:/chats";
        }
        model.addAttribute("chat", chat);

        List<MensajeEntity> mensajes = mensajeRepository.findMensajesByChatId(chatId);
        model.addAttribute("mensajes", mensajes);

        MensajeEntity siguienteMensaje = new MensajeEntity();
        model.addAttribute("siguienteMensaje", siguienteMensaje);

        ClienteEntity cliente = chat.getClienteByClienteId();
        model.addAttribute("cliente", cliente);

        model.addAttribute("rol", "a");


        return "/asistente/chat";
    }


    @PostMapping("/enviar-mensaje")
    public String doEnviarMensaje(@ModelAttribute("siguienteMensaje") MensajeEntity mensaje) {

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(System.currentTimeMillis());
        mensaje.setFecha(sqlDate);
        mensaje.setLeido(false);
        this.mensajeRepository.save(mensaje);

        return "redirect:/asistente/chat?id=" + mensaje.getChatByChat().getId();
    }

    @GetMapping("/iniciar-sesion")
    public String getIniciarSesion() {
        return "/asistente/login";
    }

    @PostMapping("/iniciar-sesion")
    public String doIniciarSesion(@RequestParam("usuario") String user, @RequestParam("clave") String contrasena, Model model, HttpSession session) {
        String urlTo = "/asistente/login";
        UsuarioEntity usuario = this.usuarioRepository.autenticar(user, contrasena);
        if (usuario == null) {
            model.addAttribute("error", "Credenciales incorrectas");
        } else {
            EmpleadoEntity empleadoFromUsuario = usuario.getEmpleadosById();
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

}