package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CAMBIO_DIVISA", schema = "grupo25", catalog = "")
public class CambioDivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MONEDA_VENTA", nullable = false, length = 45)
    private String monedaVenta;
    @Basic
    @Column(name = "MONEDA_COMPRA", nullable = false, length = 45)
    private String monedaCompra;
    @Basic
    @Column(name = "TIPO_CAMBIO", nullable = false, length = 45)
    private String tipoCambio;
    @Basic
    @Column(name = "CANTIDAD_COMPRA", nullable = false, length = 45)
    private String cantidadCompra;
    @Basic
    @Column(name = "CANTIDAD_VENTA", nullable = false, length = 45)
    private String cantidadVenta;
    @OneToOne
    @JoinColumn(name = "TRANSACCION", referencedColumnName = "ID", nullable = false)
    private TransaccionEntity transaccionByTransaccion;
    @OneToOne(mappedBy = "cambioDivisaByCambioDivisa")
    private PagoEntity pagosById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonedaVenta() {
        return monedaVenta;
    }

    public void setMonedaVenta(String monedaVenta) {
        this.monedaVenta = monedaVenta;
    }

    public String getMonedaCompra() {
        return monedaCompra;
    }

    public void setMonedaCompra(String monedaCompra) {
        this.monedaCompra = monedaCompra;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(String cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public String getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(String cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CambioDivisaEntity that = (CambioDivisaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(monedaVenta, that.monedaVenta) && Objects.equals(monedaCompra, that.monedaCompra) && Objects.equals(tipoCambio, that.tipoCambio) && Objects.equals(cantidadCompra, that.cantidadCompra) && Objects.equals(cantidadVenta, that.cantidadVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monedaVenta, monedaCompra, tipoCambio, cantidadCompra, cantidadVenta);
    }

    public TransaccionEntity getTransaccionByTransaccion() {
        return transaccionByTransaccion;
    }

    public void setTransaccionByTransaccion(TransaccionEntity transaccionByTransaccion) {
        this.transaccionByTransaccion = transaccionByTransaccion;
    }

    public PagoEntity getPagosById() {
        return pagosById;
    }

    public void setPagosById(PagoEntity pagosById) {
        this.pagosById = pagosById;
    }
}
