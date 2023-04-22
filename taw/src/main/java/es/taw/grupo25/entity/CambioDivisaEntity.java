package es.taw.grupo25.entity;

import es.taw.grupo25.dto.CambioDivisa;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CAMBIO_DIVISA", schema = "grupo25", catalog = "")
public class CambioDivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "TRANSACCION", referencedColumnName = "ID", nullable = false)
    private TransaccionEntity transaccionByTransaccion;
    @ManyToOne
    @JoinColumn(name = "MONEDA_COMPRA", referencedColumnName = "ID", nullable = false)
    private MonedaEntity monedaByMonedaCompra;
    @ManyToOne
    @JoinColumn(name = "MONEDA_VENTA", referencedColumnName = "ID", nullable = false)
    private MonedaEntity monedaByMonedaVenta;
    @OneToOne(mappedBy = "cambioDivisaByCambioDivisa")
    private PagoEntity pagosById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CambioDivisaEntity that = (CambioDivisaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TransaccionEntity getTransaccionByTransaccion() {
        return transaccionByTransaccion;
    }

    public void setTransaccionByTransaccion(TransaccionEntity transaccionByTransaccion) {
        this.transaccionByTransaccion = transaccionByTransaccion;
    }

    public MonedaEntity getMonedaByMonedaCompra() {
        return monedaByMonedaCompra;
    }

    public void setMonedaByMonedaCompra(MonedaEntity monedaByMonedaCompra) {
        this.monedaByMonedaCompra = monedaByMonedaCompra;
    }

    public MonedaEntity getMonedaByMonedaVenta() {
        return monedaByMonedaVenta;
    }

    public void setMonedaByMonedaVenta(MonedaEntity monedaByMonedaVenta) {
        this.monedaByMonedaVenta = monedaByMonedaVenta;
    }

    public PagoEntity getPagosById() {
        return pagosById;
    }

    public void setPagosById(PagoEntity pagosById) {
        this.pagosById = pagosById;
    }

    public CambioDivisa toDTO(){
        CambioDivisa cambioDivisa = new CambioDivisa();
        cambioDivisa.setId(this.id);
        cambioDivisa.setTransaccionByTransaccion(this.transaccionByTransaccion.toDTO());
        cambioDivisa.setMonedaByMonedaCompra(this.monedaByMonedaCompra.toDTO());
        cambioDivisa.setMonedaByMonedaVenta(this.monedaByMonedaVenta.toDTO());
        return cambioDivisa;
    }
}
