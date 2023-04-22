package es.taw.grupo25.dto;

import java.util.Objects;

public class Pago {
    private Integer id;
    private String moneda;
    private Double cantidad;
    private Transaccion transaccionByTransaccion;

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

    public Transaccion getTransaccionByTransaccion() {
        return transaccionByTransaccion;
    }

    public void setTransaccionByTransaccion(Transaccion transaccionByTransaccion) {
        this.transaccionByTransaccion = transaccionByTransaccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return id.equals(pago.id) && moneda.equals(pago.moneda) && cantidad.equals(pago.cantidad) && transaccionByTransaccion.equals(pago.transaccionByTransaccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, cantidad, transaccionByTransaccion);
    }
}
