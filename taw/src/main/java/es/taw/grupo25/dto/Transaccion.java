package es.taw.grupo25.dto;

import es.taw.grupo25.entity.CambioDivisaEntity;
import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.CuentaBancariaEntity;
import es.taw.grupo25.entity.PagoEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

public class Transaccion {
    private Integer id;
    private Timestamp fechaInstruccion;
    private Timestamp fechaEjecucion;
    private PagoEntity pagosById;
    private CuentaBancaria cuentaBancariaByCuentaOrigen;
    private CuentaBancaria cuentaBancariaByCuentaDestino;
    private ClienteEntity clienteByCliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Timestamp fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Timestamp getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Timestamp fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public PagoEntity getPagosById() {
        return pagosById;
    }

    public void setPagosById(PagoEntity pagosById) {
        this.pagosById = pagosById;
    }

    public CuentaBancaria getCuentaBancariaByCuentaOrigen() {
        return cuentaBancariaByCuentaOrigen;
    }

    public void setCuentaBancariaByCuentaOrigen(CuentaBancaria cuentaBancariaByCuentaOrigen) {
        this.cuentaBancariaByCuentaOrigen = cuentaBancariaByCuentaOrigen;
    }

    public CuentaBancaria getCuentaBancariaByCuentaDestino() {
        return cuentaBancariaByCuentaDestino;
    }

    public void setCuentaBancariaByCuentaDestino(CuentaBancaria cuentaBancariaByCuentaDestino) {
        this.cuentaBancariaByCuentaDestino = cuentaBancariaByCuentaDestino;
    }

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return id.equals(that.id) && Objects.equals(fechaInstruccion, that.fechaInstruccion) && Objects.equals(fechaEjecucion, that.fechaEjecucion) && Objects.equals(pagosById, that.pagosById) && cuentaBancariaByCuentaOrigen.equals(that.cuentaBancariaByCuentaOrigen) && cuentaBancariaByCuentaDestino.equals(that.cuentaBancariaByCuentaDestino) && Objects.equals(clienteByCliente, that.clienteByCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInstruccion, fechaEjecucion, pagosById, cuentaBancariaByCuentaOrigen, cuentaBancariaByCuentaDestino, clienteByCliente);
    }
}
