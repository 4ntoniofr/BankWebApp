package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Pago", schema = "grupo25", catalog = "")
public class PagoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "moneda", nullable = false, length = 45)
    private String moneda;
    @Basic
    @Column(name = "cantidad", nullable = false, length = 45)
    private String cantidad;
    @OneToOne
    @JoinColumn(name = "Transaccion_id", referencedColumnName = "id", nullable = false)
    private TransaccionEntity transaccionByTransaccionId;
    @OneToOne
    @JoinColumn(name = "CambioDivisa_id", referencedColumnName = "id")
    private CambioDivisaEntity cambioDivisaByCambioDivisaId;

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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
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

    public TransaccionEntity getTransaccionByTransaccionId() {
        return transaccionByTransaccionId;
    }

    public void setTransaccionByTransaccionId(TransaccionEntity transaccionByTransaccionId) {
        this.transaccionByTransaccionId = transaccionByTransaccionId;
    }

    public CambioDivisaEntity getCambioDivisaByCambioDivisaId() {
        return cambioDivisaByCambioDivisaId;
    }

    public void setCambioDivisaByCambioDivisaId(CambioDivisaEntity cambioDivisaByCambioDivisaId) {
        this.cambioDivisaByCambioDivisaId = cambioDivisaByCambioDivisaId;
    }
}
