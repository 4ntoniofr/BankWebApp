package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CambioDivisa", schema = "grupo25", catalog = "")
public class CambioDivisaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "monedaVenta", nullable = false, length = 45)
    private String monedaVenta;
    @Basic
    @Column(name = "monedaCompra", nullable = false, length = 45)
    private String monedaCompra;
    @Basic
    @Column(name = "tipoDeCambio", nullable = false, length = 45)
    private String tipoDeCambio;
    @Basic
    @Column(name = "cantidadCompra", nullable = false, length = 45)
    private String cantidadCompra;
    @Basic
    @Column(name = "cantidadVenta", nullable = false, length = 45)
    private String cantidadVenta;
    @OneToOne
    @JoinColumn(name = "Transaccion_id", referencedColumnName = "id", nullable = false)
    private TransaccionEntity transaccionByTransaccionId;
    @OneToOne(mappedBy = "cambioDivisaByCambioDivisaId")
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

    public String getTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(String tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
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
        return Objects.equals(id, that.id) && Objects.equals(monedaVenta, that.monedaVenta) && Objects.equals(monedaCompra, that.monedaCompra) && Objects.equals(tipoDeCambio, that.tipoDeCambio) && Objects.equals(cantidadCompra, that.cantidadCompra) && Objects.equals(cantidadVenta, that.cantidadVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, monedaVenta, monedaCompra, tipoDeCambio, cantidadCompra, cantidadVenta);
    }

    public TransaccionEntity getTransaccionByTransaccionId() {
        return transaccionByTransaccionId;
    }

    public void setTransaccionByTransaccionId(TransaccionEntity transaccionByTransaccionId) {
        this.transaccionByTransaccionId = transaccionByTransaccionId;
    }

    public PagoEntity getPagosById() {
        return pagosById;
    }

    public void setPagosById(PagoEntity pagosById) {
        this.pagosById = pagosById;
    }
}
