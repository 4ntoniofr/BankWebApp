package es.taw.grupo25.dto;


import es.taw.grupo25.entity.CuentaInternaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable {
    private Integer id;
    private Date fechaInicio;
    private EstadoCliente estadoClienteByEstadoCliente;
    private Direccion direccionByDireccion;
    private Usuario usuarioByUsuarioId;
    private Persona personaByPersonaId;
    private RolCliente rolClienteByRolClienteId;
    private Empresa empresasById;

    private List<CuentaInterna> cuentaInternasById;

    private Empleado empleadoByAutorizador;


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


    public Empresa getEmpresasById() {
        return this.empresasById;
    }

    public void setEmpresasById(Empresa empresasById) {
        this.empresasById = empresasById;
    }

    public Empleado getEmpleadoByAutorizador() {
        return empleadoByAutorizador;
    }

    public void setEmpleadoByAutorizador(Empleado empleadoByAutorizador) {
        this.empleadoByAutorizador = empleadoByAutorizador;
    }

    public List<CuentaInterna> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(List<CuentaInterna> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }

    public void setCuentaInternasEntitiesById(List<CuentaInternaEntity> cuentaInternasById) {
        List<CuentaInterna> cuentaInternas = new ArrayList<>();
        for (CuentaInternaEntity cuenta : cuentaInternasById) {
            cuentaInternas.add(cuenta.toDTO());
        }
        this.cuentaInternasById = cuentaInternas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id) &&
                fechaInicio.equals(cliente.fechaInicio) &&
                estadoClienteByEstadoCliente.equals(cliente.estadoClienteByEstadoCliente) &&
                direccionByDireccion.equals(cliente.direccionByDireccion) &&
                usuarioByUsuarioId.equals(cliente.usuarioByUsuarioId) &&
                Objects.equals(personaByPersonaId, cliente.personaByPersonaId) &&
                rolClienteByRolClienteId.equals(cliente.rolClienteByRolClienteId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInicio, estadoClienteByEstadoCliente, direccionByDireccion, usuarioByUsuarioId, personaByPersonaId, rolClienteByRolClienteId);
    }

}