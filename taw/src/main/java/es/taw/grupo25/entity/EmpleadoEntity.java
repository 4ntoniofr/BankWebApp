package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Autorizacion;
import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Empleado;
import es.taw.grupo25.dto.Usuario;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EMPLEADO", schema = "grupo25", catalog = "")
public class EmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "empleadoByEmpleadoId")
    private List<AutorizacionEntity> autorizacionsById;
    @OneToMany(mappedBy = "empleadoByEmpleadoId")
    private List<ChatEntity> chatsById;
    @OneToMany(mappedBy = "empleadoByAutorizador")
    private List<ClienteEntity> clientesById;
    @OneToOne
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @OneToOne
    @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID", nullable = false)
    private PersonaEntity personaByPersonaId;
    @ManyToOne
    @JoinColumn(name = "ROL_EMPLEADO_ID", referencedColumnName = "ID", nullable = false)
    private RolEmpleadoEntity rolEmpleadoByRolEmpleadoId;

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
        EmpleadoEntity that = (EmpleadoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<AutorizacionEntity> getAutorizacionsById() {
        return autorizacionsById;
    }

    public void setAutorizacionsById(List<AutorizacionEntity> autorizacionsById) {
        this.autorizacionsById = autorizacionsById;
    }

    public List<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public List<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public PersonaEntity getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(PersonaEntity personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }

    public RolEmpleadoEntity getRolEmpleadoByRolEmpleadoId() {
        return rolEmpleadoByRolEmpleadoId;
    }

    public void setRolEmpleadoByRolEmpleadoId(RolEmpleadoEntity rolEmpleadoByRolEmpleadoId) {
        this.rolEmpleadoByRolEmpleadoId = rolEmpleadoByRolEmpleadoId;
    }

    public Empleado toDTO() {
        Empleado empleado = new Empleado();
        empleado.setId(this.id);
        empleado.setRolEmpleadoByRolEmpleadoId(this.rolEmpleadoByRolEmpleadoId.toDTO());
        empleado.setPersonaByPersonaId(this.personaByPersonaId.toDTO());
        empleado.setAutorizacionsEntitieById(this.autorizacionsById, empleado);
        if(clientesById!=null)empleado.setClientesEntitiesById(this.clientesById, empleado.getUsuarioByUsuarioId(), empleado);
        return empleado;
    }

    public Empleado toDTO(Usuario usuario) {
        Empleado empleado = new Empleado();
        empleado.setId(this.id);
        empleado.setRolEmpleadoByRolEmpleadoId(this.rolEmpleadoByRolEmpleadoId.toDTO());
        empleado.setPersonaByPersonaId(this.personaByPersonaId.toDTO());
        empleado.setUsuarioByUsuarioId(usuario);
        empleado.setAutorizacionsEntitieById(this.autorizacionsById, empleado);
        if(clientesById!=null)empleado.setClientesEntitiesById(this.clientesById, empleado.getUsuarioByUsuarioId(), empleado);
        return empleado;
    }
}
