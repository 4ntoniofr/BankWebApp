package es.taw.grupo25.dto;
import java.io.Serializable;
import java.util.Objects;

public class Moneda implements Serializable {
    private Integer id;
    private String moneda;
    private Double cambioEuro;

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

    public Double getCambioEuro() {
        return cambioEuro;
    }

    public void setCambioEuro(Double cambioEuro) {
        this.cambioEuro = cambioEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda1 = (Moneda) o;
        return id.equals(moneda1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, cambioEuro);
    }
}
