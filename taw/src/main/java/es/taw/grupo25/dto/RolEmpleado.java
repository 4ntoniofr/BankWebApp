package es.taw.grupo25.dto;

import es.taw.grupo25.entity.RolEmpleadoEntity;

import java.io.Serializable;
import java.util.Objects;

public class RolEmpleado implements Serializable {
    private Integer id;
    private String rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEmpleado that = (RolEmpleado) o;
        return Objects.equals(id, that.id) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rol);
    }

}
