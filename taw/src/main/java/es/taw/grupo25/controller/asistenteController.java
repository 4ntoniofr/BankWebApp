package es.taw.grupo25.controller;

import es.taw.grupo25.entity.ChatEntity;
import es.taw.grupo25.entity.MensajeEntity;
import es.taw.grupo25.repository.ChatRepository;
import es.taw.grupo25.repository.ClienteRepository;
import es.taw.grupo25.repository.EmpleadoRepository;
import es.taw.grupo25.repository.MensajeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/chats-empleado")
    public String getChats(Model model, HttpSession session){

        // TODO: hay que cambiar que el repository busque por el ID del empleado que este en la sesión
        List<ChatEntity> chats = chatRepository.findAll();
        model.addAttribute("chats", chats);

        // TODO: hay que cambiar que en vez de finAll sea por el id de los chats
        List<MensajeEntity> mensajes = mensajeRepository.findAll();
        model.addAttribute("mensajes", mensajes);

        // TODO: de alguna manera tengo que pasarle mi entidad como empleado para solo conseguir mis chats
        String empleadoAsistenteId = "1";
        model.addAttribute("empleadoAsistenteId", empleadoAsistenteId);

        MensajeEntity siguienteMensaje = new MensajeEntity();
        model.addAttribute("siguienteMensaje", siguienteMensaje);

        return "/asistente/chats";
    }
}
