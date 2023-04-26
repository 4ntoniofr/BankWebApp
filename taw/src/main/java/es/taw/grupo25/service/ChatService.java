package es.taw.grupo25.service;

import es.taw.grupo25.dto.Chat;
import es.taw.grupo25.entity.ChatEntity;
import es.taw.grupo25.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    //TODO: cambiar a entidad reformateada y cambiar importe
    public List<Chat> findChatsByEmpleadoId(Integer id){
        return (chatRepository.findChatsByEmpleadoId(id)).stream().map(ChatEntity::toDTO).collect(Collectors.toList());
    }

    public ChatEntity findChatAbiertoByClienteId(Integer id){
        return chatRepository.findChatAbiertoByClienteId(id);
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
}
