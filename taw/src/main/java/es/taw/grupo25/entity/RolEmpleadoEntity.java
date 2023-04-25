package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Empleado;
import es.taw.grupo25.dto.RolEmpleado;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ROL_EMPLEADO", schema = "grupo25", catalog = "")
public class RolEmpleadoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ROL", nullable = false, length = 45)
    private String rol;
    @OneToMany(mappedBy = "rolEmpleadoByRolEmpleadoId")
    private List<EmpleadoEntity> empleadosById;

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
        RolEmpleadoEntity that = (RolEmpleadoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rol);
    }

    public List<EmpleadoEntity> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(List<EmpleadoEntity> empleadosById) {
        this.empleadosById = empleadosById;
    }

    public RolEmpleado toDTO(){
        RolEmpleado rolEmpleado = new RolEmpleado();
        rolEmpleado.setRol(this.rol);
        return rolEmpleado;
    }

}
