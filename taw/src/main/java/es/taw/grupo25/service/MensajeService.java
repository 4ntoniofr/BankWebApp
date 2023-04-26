package es.taw.grupo25.service;

import es.taw.grupo25.dto.Mensaje;
import es.taw.grupo25.entity.MensajeEntity;
import es.taw.grupo25.repository.ChatRepository;
import es.taw.grupo25.repository.MensajeRepository;

import es.taw.grupo25.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jorge Camacho García
 */
@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ChatRepository chatRepository;

    //TODO: Cambiar el tipo de entidad y cambiar importes

    public List<Mensaje> findMensajesByChatId(Integer chatId){
        List<MensajeEntity> mensajesEntities = mensajeRepository.findMensajesByChatId(chatId);
        // return mensajesEntities.stream().map(MensajeEntity::toDTO) ;
        return null;
    }

    /*
    public void save(MensajeEntity mensaje){
        mensajeRepository.save(mensaje);
    }
    */

    public void enviarMensaje(Mensaje mensaje){
        MensajeEntity mensajeEntity = new MensajeEntity();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(System.currentTimeMillis());
        mensajeEntity.setFecha(sqlDate);
        mensajeEntity.setLeido(false);
        mensajeEntity.setTexto(mensaje.getTexto());
        mensajeEntity.setChatByChat(chatRepository.findById(Integer.valueOf(mensaje.getIdChatNuevoMensaje())).orElse(null));
        mensajeEntity.setPersonaByEmisor(personaRepository.findById(Integer.valueOf(mensaje.getIdPersonaEmisorNuevoMensaje())).orElse(null));

        this.mensajeRepository.save(mensajeEntity);
    }

}
