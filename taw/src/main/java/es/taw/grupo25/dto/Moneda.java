package es.taw.grupo25.dto;

import es.taw.grupo25.entity.CambioDivisaEntity;
import es.taw.grupo25.entity.CuentaInternaEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class Moneda {
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
        return id.equals(moneda1.id) && moneda.equals(moneda1.moneda) && cambioEuro.equals(moneda1.cambioEuro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, cambioEuro);
    }
}
