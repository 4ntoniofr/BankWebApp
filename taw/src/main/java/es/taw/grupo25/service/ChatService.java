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
}
