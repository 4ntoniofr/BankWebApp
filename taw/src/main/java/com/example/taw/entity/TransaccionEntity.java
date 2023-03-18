package com.example.taw.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Transaccion", schema = "grupo25", catalog = "")
public class TransaccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "fechaInstruccion", nullable = true)
    private Date fechaInstruccion;
    @Basic
    @Column(name = "fechaEjecucion", nullable = true)
    private Date fechaEjecucion;
    @OneToOne(mappedBy = "transaccionByTransaccionId")
    private CambioDivisaEntity cambioDivisasById;
    @OneToOne(mappedBy = "transaccionByTransaccionId")
    private PagoEntity pagosById;
    @ManyToOne
    @JoinColumn(name = "cuentaOrigen", referencedColumnName = "id", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaOrigen;
    @ManyToOne
    @JoinColumn(name = "cuentaDestino", referencedColumnName = "id", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaDestino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Date fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaccionEntity that = (TransaccionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fechaInstruccion, that.fechaInstruccion) && Objects.equals(fechaEjecucion, that.fechaEjecucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInstruccion, fechaEjecucion);
    }

    public CambioDivisaEntity getCambioDivisasById() {
        return cambioDivisasById;
    }

    public void setCambioDivisasById(CambioDivisaEntity cambioDivisasById) {
        this.cambioDivisasById = cambioDivisasById;
    }

    public PagoEntity getPagosById() {
        return pagosById;
    }

    public void setPagosById(PagoEntity pagosById) {
        this.pagosById = pagosById;
    }

    public CuentaBancariaEntity getCuentaBancariaByCuentaOrigen() {
        return cuentaBancariaByCuentaOrigen;
    }

    public void setCuentaBancariaByCuentaOrigen(CuentaBancariaEntity cuentaBancariaByCuentaOrigen) {
        this.cuentaBancariaByCuentaOrigen = cuentaBancariaByCuentaOrigen;
    }

    public CuentaBancariaEntity getCuentaBancariaByCuentaDestino() {
        return cuentaBancariaByCuentaDestino;
    }

    public void setCuentaBancariaByCuentaDestino(CuentaBancariaEntity cuentaBancariaByCuentaDestino) {
        this.cuentaBancariaByCuentaDestino = cuentaBancariaByCuentaDestino;
    }
}
