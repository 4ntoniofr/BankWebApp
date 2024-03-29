package es.taw.grupo25.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Valentín García Rosas
 *
 */

public class EstadoCuenta implements Serializable {
    private Integer id;
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoCuenta that = (EstadoCuenta) o;
        return id.equals(that.id) && estado.equals(that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado);
    }
}
