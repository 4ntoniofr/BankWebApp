package es.taw.grupo25.dto;

import java.util.Objects;

public class RolCliente {
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
        RolCliente that = (RolCliente) o;
        return id.equals(that.id) && rol.equals(that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rol);
    }
}
