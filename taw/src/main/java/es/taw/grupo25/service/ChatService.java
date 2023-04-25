package es.taw.grupo25.service;

import es.taw.grupo25.entity.Chat;
import es.taw.grupo25.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    //TODO: cambiar a entidad reformateada y cambiar importe
    public List<Chat> findChatsByEmpleadoId(Integer id){
        return chatRepository.findChatsByEmpleadoId(id);
    }

    public Chat findChatAbiertoByClienteId(Integer id){
        return chatRepository.findChatAbiertoByClienteId(id);
    }

    public Chat findById(Integer id){
        return chatRepository.findById(id).orElse(null);
    }


}
