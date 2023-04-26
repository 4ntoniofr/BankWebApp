package es.taw.grupo25.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class Mensaje {

    private Integer id;

    private String texto;

    private Timestamp fecha;

    private Boolean leido;


    private Persona personaByEmisor;

    private Chat chatByChat;

    // NECESARIO PARA EL FORMULARIO DE SIGUIENTE MENSAJE, DE LO CONTRARIO NO SE PUEDE OBTENER NADA

    String idPersonaEmisorNuevoMensaje;
    String idChatNuevoMensaje;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje that = (Mensaje) o;
        return Objects.equals(id, that.id) && Objects.equals(texto, that.texto) && Objects.equals(fecha, that.fecha) && Objects.equals(leido, that.leido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, fecha, leido);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public Persona getPersonaByEmisor() {
        return personaByEmisor;
    }

    public void setPersonaByEmisor(Persona personaByEmisor) {
        this.personaByEmisor = personaByEmisor;
    }

    public Chat getChatByChat() {
        return chatByChat;
    }

    public void setChatByChat(Chat chatByChat) {
        this.chatByChat = chatByChat;
    }

    public String getIdPersonaEmisorNuevoMensaje() {
        return idPersonaEmisorNuevoMensaje;
    }

    public void setIdPersonaEmisorNuevoMensaje(String idPersonaEmisorNuevoMensaje) {
        this.idPersonaEmisorNuevoMensaje = idPersonaEmisorNuevoMensaje;
    }

    public String getIdChatNuevoMensaje() {
        return idChatNuevoMensaje;
    }

    public void setIdChatNuevoMensaje(String idChatNuevoMensaje) {
        this.idChatNuevoMensaje = idChatNuevoMensaje;
    }
}
