package es.taw.grupo25.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Autorizacion implements Serializable {
    private Integer id;
    private Date fecha;
    private Empleado empleadoByEmpleadoId;
    private CuentaInterna cuentaInternaByCuentaInternaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(Empleado empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public CuentaInterna getCuentaInternaByCuentaInternaId() {
        return cuentaInternaByCuentaInternaId;
    }

    public void setCuentaInternaByCuentaInternaId(CuentaInterna cuentaInternaByCuentaInternaId) {
        this.cuentaInternaByCuentaInternaId = cuentaInternaByCuentaInternaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorizacion that = (Autorizacion) o;
        return Objects.equals(id, that.id) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha);
    }

}
