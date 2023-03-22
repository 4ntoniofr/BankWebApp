package com.example.taw.entity;

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
    @OneToOne
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @OneToOne
    @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID", nullable = false)
    private PersonaEntity personaByPersonaId;
    @ManyToOne
    @JoinColumn(name = "ROL_EMPLEADO_ID", referencedColumnName = "ID", nullable = false)
    private RolEmpleadoEntity rolEmpleadoByRolEmpleadoId;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByClienteId;

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

    public ClienteEntity getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteEntity clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }
}
