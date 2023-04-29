package es.taw.grupo25.service;

import es.taw.grupo25.dto.Chat;
import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Mensaje;
import es.taw.grupo25.entity.ChatEntity;
import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpleadoEntity;
import es.taw.grupo25.entity.MensajeEntity;
import es.taw.grupo25.repository.ChatRepository;
import es.taw.grupo25.repository.ClienteRepository;
import es.taw.grupo25.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Jorge Camacho García
 */
@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Chat> findChatsByEmpleadoId(Integer id){
        return (chatRepository.findChatsByEmpleadoId(id)).stream().map(ChatEntity::toDTO).collect(Collectors.toList());
    }

    public List<Chat> findChatsAbiertosByClienteId(Integer id){
        List<ChatEntity> chats = chatRepository.findChatsAbiertosByClienteId(id);
        if(chats != null)
            return chats.stream().map(ChatEntity::toDTO).collect(Collectors.toList());
        else
            return null;
    }

    public Chat findById(Integer id){
        ChatEntity chat = chatRepository.findById(id).orElse(null);
        return chat==null?null:chat.toDTO();
    }


    public void cerrarChat(Chat chat) {
        ChatEntity chatEntity = chatRepository.findById(chat.getId()).orElse(null);
        if(chatEntity != null){
            chatEntity.setFechaCierre(new Timestamp(System.currentTimeMillis()));
            chatRepository.save(chatEntity);
        }
    }

    public List<Chat> findChatsCerradosByClienteId(Integer id) {
        List<ChatEntity> chats = chatRepository.findChatsCerradosByClienteId(id);
        if(chats != null)
            return chats.stream().map(ChatEntity::toDTO).collect(Collectors.toList());
        else
            return null;
    }

    public Chat generarNuevoChatConAsistenteAleatorio(Cliente cliente) {
        ChatEntity nuevoChat = new ChatEntity();

        ClienteEntity clienteEntity = clienteRepository.findById(cliente.getId()).orElse(null);

        List<EmpleadoEntity> asistentesDisponibles = empleadoRepository.asistentesDisponibles(clienteEntity.getPersonaByPersonaId().getId());
        EmpleadoEntity asistenteEntity = asistentesDisponibles.get(new Random().nextInt(asistentesDisponibles.size()));

        if(clienteEntity != null && asistenteEntity != null){
            nuevoChat.setMensajesById(new ArrayList<>());
            nuevoChat.setClienteByClienteId(clienteEntity);
            nuevoChat.setEmpleadoByEmpleadoId(asistenteEntity);
            chatRepository.save(nuevoChat);
        }else{
            nuevoChat = null;
        }


        return nuevoChat==null?null:nuevoChat.toDTO();
    }
}
