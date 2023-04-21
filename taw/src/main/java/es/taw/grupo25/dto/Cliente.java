package es.taw.grupo25.dto;

import es.taw.grupo25.entity.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private Integer id;
    private Date fechaInicio;
    private List<ChatEntity> chatsById;
    private EstadoClienteEntity estadoClienteByEstadoCliente;
    private DireccionEntity direccionByDireccion;
    private UsuarioEntity usuarioByUsuarioId;
    private PersonaEntity personaByPersonaId;
    private RolClienteEntity rolClienteByRolClienteId;
    private List<TransaccionEntity> transaccionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public EstadoClienteEntity getEstadoClienteByEstadoCliente() {
        return estadoClienteByEstadoCliente;
    }

    public void setEstadoClienteByEstadoCliente(EstadoClienteEntity estadoClienteByEstadoCliente) {
        this.estadoClienteByEstadoCliente = estadoClienteByEstadoCliente;
    }

    public DireccionEntity getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionEntity direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
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

    public RolClienteEntity getRolClienteByRolClienteId() {
        return rolClienteByRolClienteId;
    }

    public void setRolClienteByRolClienteId(RolClienteEntity rolClienteByRolClienteId) {
        this.rolClienteByRolClienteId = rolClienteByRolClienteId;
    }

    public List<TransaccionEntity> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(List<TransaccionEntity> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id) && fechaInicio.equals(cliente.fechaInicio) && Objects.equals(chatsById, cliente.chatsById) && estadoClienteByEstadoCliente.equals(cliente.estadoClienteByEstadoCliente) && direccionByDireccion.equals(cliente.direccionByDireccion) && usuarioByUsuarioId.equals(cliente.usuarioByUsuarioId) && Objects.equals(personaByPersonaId, cliente.personaByPersonaId) && rolClienteByRolClienteId.equals(cliente.rolClienteByRolClienteId) && Objects.equals(transaccionsById, cliente.transaccionsById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInicio, chatsById, estadoClienteByEstadoCliente, direccionByDireccion, usuarioByUsuarioId, personaByPersonaId, rolClienteByRolClienteId, transaccionsById);
    }
}
