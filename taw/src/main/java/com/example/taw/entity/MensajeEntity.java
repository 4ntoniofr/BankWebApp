package com.example.taw.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Mensaje", schema = "grupo25", catalog = "")
public class MensajeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "texto", nullable = false, length = -1)
    private String texto;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;
    @ManyToOne
    @JoinColumn(name = "emisor", referencedColumnName = "id", nullable = false)
    private PersonaEntity personaByEmisor;
    @OneToOne
    @JoinColumn(name = "Chat_id", referencedColumnName = "id", nullable = false)
    private ChatEntity chatByChatId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MensajeEntity that = (MensajeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(texto, that.texto) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, fecha);
    }

    public PersonaEntity getPersonaByEmisor() {
        return personaByEmisor;
    }

    public void setPersonaByEmisor(PersonaEntity personaByEmisor) {
        this.personaByEmisor = personaByEmisor;
    }

    public ChatEntity getChatByChatId() {
        return chatByChatId;
    }

    public void setChatByChatId(ChatEntity chatByChatId) {
        this.chatByChatId = chatByChatId;
    }
}
