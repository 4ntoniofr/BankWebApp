package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "TRANSACCION", schema = "grupo25", catalog = "")
public class TransaccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "FECHA_INSTRUCCION", nullable = true)
    private Date fechaInstruccion;
    @Basic
    @Column(name = "FECHA_EJECUCION", nullable = true)
    private Date fechaEjecucion;
    @OneToOne(mappedBy = "transaccionByTransaccion")
    private CambioDivisaEntity cambioDivisasById;
    @OneToOne(mappedBy = "transaccionByTransaccion")
    private PagoEntity pagosById;
    @ManyToOne
    @JoinColumn(name = "CUENTA_ORIGEN", referencedColumnName = "ID", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaOrigen;
    @ManyToOne
    @JoinColumn(name = "CUENTA_DESTINO", referencedColumnName = "ID", nullable = false)
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
