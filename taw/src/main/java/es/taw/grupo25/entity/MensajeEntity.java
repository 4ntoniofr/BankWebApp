package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "MENSAJE", schema = "grupo25", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "TEXTO", nullable = false, length = -1)
    private String texto;
    @Basic
    @Column(name = "FECHA", nullable = false)
    private Timestamp fecha;
    @Basic
    @Column(name = "LEIDO", nullable = false)
    private Boolean leido;
    @ManyToOne
    @JoinColumn(name = "EMISOR", referencedColumnName = "ID", nullable = false)
    private PersonaEntity personaByEmisor;
    @ManyToOne
    @JoinColumn(name = "CHAT", referencedColumnName = "ID", nullable = false)
    private ChatEntity chatByChat;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(texto, that.texto) && Objects.equals(fecha, that.fecha) && Objects.equals(leido, that.leido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, fecha, leido);
    }

    public PersonaEntity getPersonaByEmisor() {
        return personaByEmisor;
    }

    public void setPersonaByEmisor(PersonaEntity personaByEmisor) {
        this.personaByEmisor = personaByEmisor;
    }

    public ChatEntity getChatByChat() {
        return chatByChat;
    }

    public void setChatByChat(ChatEntity chatByChat) {
        this.chatByChat = chatByChat;
    }
}
