package es.taw.grupo25.dto;

import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpresaEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Empresa implements Serializable {
    private Integer id;
    private String nombre;
    private Date fechaCierre;
    private Cliente clienteByClienteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Cliente getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(Cliente clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id) && Objects.equals(nombre, empresa.nombre) && Objects.equals(fechaCierre, empresa.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaCierre);
    }
}
