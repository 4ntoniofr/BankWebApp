package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Transaccion;
import jakarta.persistence.*;

import java.sql.Timestamp;
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
    private Timestamp fechaInstruccion;
    @Basic
    @Column(name = "FECHA_EJECUCION", nullable = true)
    private Timestamp fechaEjecucion;
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
    @ManyToOne
    @JoinColumn(name = "CLIENTE", referencedColumnName = "ID")
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

    public ClienteEntity getClienteByCliente() {
        return clienteByCliente;
    }

    public void setClienteByCliente(ClienteEntity clienteByCliente) {
        this.clienteByCliente = clienteByCliente;
    }

    public Transaccion toDTO(){
        Transaccion transaccion = new Transaccion();

        transaccion.setId(this.id);
        transaccion.setFechaInstruccion(this.fechaInstruccion);
        transaccion.setFechaEjecucion(this.fechaEjecucion);
        transaccion.setPagosById(this.pagosById);
        transaccion.setCuentaBancariaByCuentaOrigen(this.cuentaBancariaByCuentaOrigen.toDTO());
        transaccion.setCuentaBancariaByCuentaDestino(this.cuentaBancariaByCuentaDestino.toDTO());
        transaccion.setClienteByCliente(this.clienteByCliente);
        transaccion.setCambioDivisa(this.cambioDivisasById!=null);

        return transaccion;
    }
}
