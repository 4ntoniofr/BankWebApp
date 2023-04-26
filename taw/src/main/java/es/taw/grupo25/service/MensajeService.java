package es.taw.grupo25.service;

import es.taw.grupo25.entity.MensajeEntity;
import es.taw.grupo25.repository.MensajeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    //TODO: Cambiar el tipo de entidad y cambiar importes

    public List<MensajeEntity> findMensajesByChatId(Integer chatId){
        return mensajeRepository.findMensajesByChatId(chatId);
    }

    public void save(MensajeEntity mensaje){
        mensajeRepository.save(mensaje);
    }


}
