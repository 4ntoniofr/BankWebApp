package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Pago;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PAGO", schema = "grupo25", catalog = "")
public class PagoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MONEDA", nullable = false, length = 45)
    private String moneda;
    @Basic
    @Column(name = "CANTIDAD", nullable = false, precision = 0)
    private Double cantidad;
    @OneToOne
    @JoinColumn(name = "TRANSACCION", referencedColumnName = "ID", nullable = false)
    private TransaccionEntity transaccionByTransaccion;
    @OneToOne
    @JoinColumn(name = "CAMBIO_DIVISA", referencedColumnName = "ID")
    private CambioDivisaEntity cambioDivisaByCambioDivisa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagoEntity that = (PagoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moneda, that.moneda) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, cantidad);
    }

    public TransaccionEntity getTransaccionByTransaccion() {
        return transaccionByTransaccion;
    }

    public void setTransaccionByTransaccion(TransaccionEntity transaccionByTransaccion) {
        this.transaccionByTransaccion = transaccionByTransaccion;
    }

    public CambioDivisaEntity getCambioDivisaByCambioDivisa() {
        return cambioDivisaByCambioDivisa;
    }

    public void setCambioDivisaByCambioDivisa(CambioDivisaEntity cambioDivisaByCambioDivisa) {
        this.cambioDivisaByCambioDivisa = cambioDivisaByCambioDivisa;
    }

    public Pago toDTO(){
        Pago pago = new Pago();

        pago.setId(this.id);
        pago.setMoneda(this.moneda);
        pago.setCantidad(this.cantidad);
        pago.setTransaccionByTransaccion(this.transaccionByTransaccion.toDTO());

        return pago;
    }
}
