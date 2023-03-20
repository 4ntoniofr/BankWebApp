package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ENTIDAD_BANCARIA", schema = "grupo25", catalog = "")
public class EntidadBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @OneToMany(mappedBy = "entidadBancariaByEntidadBancaria")
    private List<CuentaExternaEntity> cuentaExternasById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadBancariaEntity that = (EntidadBancariaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public List<CuentaExternaEntity> getCuentaExternasById() {
        return cuentaExternasById;
    }

    public void setCuentaExternasById(List<CuentaExternaEntity> cuentaExternasById) {
        this.cuentaExternasById = cuentaExternasById;
    }
}
