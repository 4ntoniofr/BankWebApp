package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Chat", schema = "grupo25", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)
    private ClienteEntity clienteByCliente;
    @ManyToOne
    @JoinColumn(name = "asistente", referencedColumnName = "id", nullable = false)
    private AsistenteEntity asistenteByAsistente;
    @OneToOne(mappedBy = "chatByChatId")
    private MensajeEntity mensajesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public AsistenteEntity getAsistenteByAsistente() {
        return asistenteByAsistente;
    }

    public void setAsistenteByAsistente(AsistenteEntity asistenteByAsistente) {
        this.asistenteByAsistente = asistenteByAsistente;
    }

    public MensajeEntity getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(MensajeEntity mensajesById) {
        this.mensajesById = mensajesById;
    }
}
