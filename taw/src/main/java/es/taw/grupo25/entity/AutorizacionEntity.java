package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "AUTORIZACION", schema = "grupo25", catalog = "")
public class AutorizacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "EMPLEADO_ID", referencedColumnName = "ID", nullable = false)
    private EmpleadoEntity empleadoByEmpleadoId;
    @ManyToOne
    @JoinColumn(name = "CUENTA_INTERNA_ID", referencedColumnName = "ID", nullable = false)
    private CuentaInternaEntity cuentaInternaByCuentaInternaId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorizacionEntity that = (AutorizacionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha);
    }

    public EmpleadoEntity getEmpleadoByEmpleadoId() {
        return empleadoByEmpleadoId;
    }

    public void setEmpleadoByEmpleadoId(EmpleadoEntity empleadoByEmpleadoId) {
        this.empleadoByEmpleadoId = empleadoByEmpleadoId;
    }

    public CuentaInternaEntity getCuentaInternaByCuentaInternaId() {
        return cuentaInternaByCuentaInternaId;
    }

    public void setCuentaInternaByCuentaInternaId(CuentaInternaEntity cuentaInternaByCuentaInternaId) {
        this.cuentaInternaByCuentaInternaId = cuentaInternaByCuentaInternaId;
    }
}
