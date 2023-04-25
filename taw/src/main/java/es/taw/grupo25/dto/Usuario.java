package es.taw.grupo25.dto;
import es.taw.grupo25.entity.EmpleadoEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Usuario implements Serializable {
    private Integer id;
    private String usuario;
    private String password;
    private Cliente clientesById;
    private Date fechaRegistro;
    private Empleado empleadosById;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cliente getClientesById() {
        return clientesById;
    }

    public void setClientesById(Cliente clientesById) {
        this.clientesById = clientesById;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Empleado getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(Empleado empleadosById) {
        this.empleadosById = empleadosById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(id, usuario1.id) && Objects.equals(usuario, usuario1.usuario) && Objects.equals(password, usuario1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, password);
    }
}
