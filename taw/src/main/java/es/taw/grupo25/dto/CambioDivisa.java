package es.taw.grupo25.dto;

import es.taw.grupo25.entity.MonedaEntity;
import es.taw.grupo25.entity.PagoEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class CambioDivisa implements Serializable {
    private Integer id;
    private Transaccion transaccionByTransaccion;
    private Moneda monedaByMonedaCompra;
    private Moneda monedaByMonedaVenta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaccion getTransaccionByTransaccion() {
        return transaccionByTransaccion;
    }

    public void setTransaccionByTransaccion(Transaccion transaccionByTransaccion) {
        this.transaccionByTransaccion = transaccionByTransaccion;
    }

    public Moneda getMonedaByMonedaCompra() {
        return monedaByMonedaCompra;
    }

    public void setMonedaByMonedaCompra(Moneda monedaByMonedaCompra) {
        this.monedaByMonedaCompra = monedaByMonedaCompra;
    }

    public Moneda getMonedaByMonedaVenta() {
        return monedaByMonedaVenta;
    }

    public void setMonedaByMonedaVenta(Moneda monedaByMonedaVenta) {
        this.monedaByMonedaVenta = monedaByMonedaVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CambioDivisa that = (CambioDivisa) o;
        return id.equals(that.id) && transaccionByTransaccion.equals(that.transaccionByTransaccion) && monedaByMonedaCompra.equals(that.monedaByMonedaCompra) && monedaByMonedaVenta.equals(that.monedaByMonedaVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transaccionByTransaccion, monedaByMonedaCompra, monedaByMonedaVenta);
    }
}
