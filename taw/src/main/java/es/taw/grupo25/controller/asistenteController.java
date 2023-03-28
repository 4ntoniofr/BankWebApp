package es.taw.grupo25.controller;

import es.taw.grupo25.entity.ChatEntity;
import es.taw.grupo25.entity.EmpleadoEntity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // TODO: conseguir de verdad el id que tengo como empleado
        String idQueTengoComoEmpleado = "1";

        Map<ChatEntity, List<MensajeEntity>> chatsConMensajes = new HashMap<>();

        List<ChatEntity> chats = chatRepository.findChatsByEmpleadoId(idQueTengoComoEmpleado);
        for(ChatEntity chat : chats){
            // TODO: hay que cambiar que en vez de finAll sea por el id de los chats
            List<MensajeEntity> mensajesDelChat = mensajeRepository.findAll();

            chatsConMensajes.put(chat, mensajesDelChat);
        }

        model.addAttribute("chatsConMensajes", chatsConMensajes);

        EmpleadoEntity empleadoQueSoy = empleadoRepository.findById(idQueTengoComoEmpleado);
        List<EmpleadoEntity> todosEmpleados = empleadoRepository.findAll();
        model.addAttribute("empleado", empleadoQueSoy);

        // TODO: mediante método POST gestionar la creación de un mensaje
        MensajeEntity siguienteMensaje = new MensajeEntity();
        model.addAttribute("siguienteMensaje", siguienteMensaje);

        return "/asistente/chats";
    }
}
