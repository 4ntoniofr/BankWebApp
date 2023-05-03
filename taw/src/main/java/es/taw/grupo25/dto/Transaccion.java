package es.taw.grupo25.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.taw.grupo25.entity.*;
import es.taw.grupo25.service.CuentaBancariaService;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Transaccion implements Serializable {
    private Integer id;
    private Timestamp fechaInstruccion;
    private Timestamp fechaEjecucion;
    private PagoEntity pagosById;

    @JsonIgnoreProperties({"transaccionsById_Entrantes", "transaccionsById_Salientes"})
    private CuentaBancaria cuentaBancariaByCuentaOrigen;
    @JsonIgnoreProperties({"transaccionsById_Entrantes", "transaccionsById_Salientes"})
    private CuentaBancaria cuentaBancariaByCuentaDestino;
    private Cliente clienteByCliente;

    private CambioDivisaEntity cambioDivisa;

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

    public Cliente getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(Cliente clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public CambioDivisaEntity getCambioDivisa() {
        return cambioDivisa;
    }

    public void setCambioDivisa(CambioDivisaEntity cambioDivisa) {
        this.cambioDivisa = cambioDivisa;
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
