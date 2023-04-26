package es.taw.grupo25.dto;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Empresa {
    private Integer id;
    private String nombre;
    private Date fechaCierre;
    private List<Cliente> clientesById_Socios;
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

    public List<Cliente> getClientesById_Socios() {
        return clientesById_Socios;
    }

    public void setClientesById_Socios(List<Cliente> clientesById_Socios) {
        this.clientesById_Socios = clientesById_Socios;
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
        return Objects.equals(id, empresa.id) && Objects.equals(nombre, empresa.nombre) && Objects.equals(fechaCierre, empresa.fechaCierre) && Objects.equals(clientesById_Socios, empresa.clientesById_Socios) && Objects.equals(clienteByClienteId, empresa.clienteByClienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaCierre, clientesById_Socios, clienteByClienteId);
    }
}
