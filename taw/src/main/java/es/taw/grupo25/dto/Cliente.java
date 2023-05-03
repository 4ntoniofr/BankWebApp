package es.taw.grupo25.dto;

import es.taw.grupo25.entity.CuentaInternaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author José Francisco Artacho Martin - 33%
 * @author Antonio Fernández Rodriguez - 33%
 * @author Valentín García Rosas - 33%
 *
 */

public class Cliente implements Serializable {
    private Integer id;
    private Date fechaInicio;
    private EstadoCliente estadoClienteByEstadoCliente;
    private Direccion direccionByDireccion;
    private Usuario usuarioByUsuarioId;
    private Persona personaByPersonaId;
    private RolCliente rolClienteByRolClienteId;
    private Empresa empresaByEmpresaSocio;
    private Empresa empresasById;
    private List<Transaccion> transaccionsById;

    private List<CuentaInterna> cuentaInternasById;

    private boolean autorizador;

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

    public Empresa getEmpresaByEmpresaSocio() {
        return empresaByEmpresaSocio;
    }

    public void setEmpresaByEmpresaSocio(Empresa empresaByEmpresaSocio) {
        this.empresaByEmpresaSocio = empresaByEmpresaSocio;
    }

    public Empresa getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(Empresa empresasById) {
        this.empresasById = empresasById;
    }

    public List<Transaccion> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(List<Transaccion> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public List<CuentaInterna> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public boolean getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(boolean autorizador) {
        this.autorizador = autorizador;
    }

    public void setCuentaInternasById(List<CuentaInterna> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }

    public void setCuentaInternasEntitiesById(List<CuentaInternaEntity> cuentaInternasById, Cliente cliente) {
        List<CuentaInterna> cuentaInternas = new ArrayList<>();
        for (CuentaInternaEntity cuenta : cuentaInternasById) {
            cuentaInternas.add(cuenta.toDTO(cliente));
        }
        this.cuentaInternasById = cuentaInternas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}