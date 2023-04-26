package es.taw.grupo25.service;

import es.taw.grupo25.entity.ChatEntity;
import es.taw.grupo25.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    //TODO: cambiar a entidad reformateada y cambiar importe
    public List<ChatEntity> findChatsByEmpleadoId(Integer id){
        return chatRepository.findChatsByEmpleadoId(id);
    }

    public ChatEntity findChatAbiertoByClienteId(Integer id){
        return chatRepository.findChatAbiertoByClienteId(id);
    }

    public ChatEntity findById(Integer id){
        return chatRepository.findById(id).orElse(null);
    }


}
