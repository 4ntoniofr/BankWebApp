package es.taw.grupo25.dto;

import es.taw.grupo25.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private Integer id;
    private Date fechaInicio;
    private List<ChatEntity> chatsById;
    private EstadoCliente estadoClienteByEstadoCliente;
    private Direccion direccionByDireccion;
    private Usuario usuarioByUsuarioId;
    private Persona personaByPersonaId;
    private RolCliente rolClienteByRolClienteId;
    private List<TransaccionEntity> transaccionsById;

    private EmpleadoEntity empleadoByAutorizador;

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

    public EstadoCliente getEstadoClienteByEstadoCliente() {
        return estadoClienteByEstadoCliente;
    }

    public void setEstadoClienteByEstadoCliente(EstadoCliente estadoClienteByEstadoCliente) {
        this.estadoClienteByEstadoCliente = estadoClienteByEstadoCliente;
    }

    public Direccion getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(Direccion direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public Usuario getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(Usuario usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public Persona getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(Persona personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }

    public RolCliente getRolClienteByRolClienteId() {
        return rolClienteByRolClienteId;
    }

    public void setRolClienteByRolClienteId(RolCliente rolClienteByRolClienteId) {
        this.rolClienteByRolClienteId = rolClienteByRolClienteId;
    }

    public List<TransaccionEntity> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(List<TransaccionEntity> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public EmpleadoEntity getEmpleadoByAutorizador() {
        return empleadoByAutorizador;
    }

    public void setEmpleadoByAutorizador(EmpleadoEntity empleadoByAutorizador) {
        this.empleadoByAutorizador = empleadoByAutorizador;
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
