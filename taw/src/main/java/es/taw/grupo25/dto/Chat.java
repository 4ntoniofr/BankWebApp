package es.taw.grupo25.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author Jorge Camacho García
 */
public class Chat {

    private Integer id;

    private Empleado empleadoByEmpleadoId;

    private Cliente clienteByClienteId;

    private Timestamp fechaCierre;
    private List<Mensaje> mensajesById;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat that = (Chat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empleado getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(Empleado empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public Cliente getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(Cliente clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public List<Mensaje> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<Mensaje> mensajesById) {
        this.mensajesById = mensajesById;
    }
}
