package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Chat;
import es.taw.grupo25.dto.Cliente;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "CHAT", schema = "grupo25", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "EMPLEADO_ID", referencedColumnName = "ID", nullable = false)
    private EmpleadoEntity empleadoByEmpleadoId;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByClienteId;
    @Basic
    @Column(name = "FECHA_CIERRE")
    private Timestamp fechaCierre;
    @OneToMany(mappedBy = "chatByChat")
    private List<MensajeEntity> mensajesById;

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

    public EmpleadoEntity getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(EmpleadoEntity empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public ClienteEntity getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteEntity clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public Chat toDTO(){
        Chat chat = new Chat();
        chat.setId(this.id);
        chat.setFechaCierre(this.fechaCierre);
        chat.setClienteByClienteId(this.clienteByClienteId.toDTO());
        chat.setEmpleadoByEmpleadoId(this.empleadoByEmpleadoId.toDTO());
        chat.setMensajesById(this.mensajesById.stream().map(msj -> msj.toDTO(chat)).collect(Collectors.toList()));
        return chat;
    }
}
