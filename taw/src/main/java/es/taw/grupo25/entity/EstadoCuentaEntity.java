package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ESTADO_CUENTA", schema = "grupo25", catalog = "")
public class EstadoCuentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ESTADO", nullable = false, length = 45)
    private String estado;
    @OneToMany(mappedBy = "estadoCuentaByEstadoCuenta")
    private List<CuentaInternaEntity> cuentaInternaById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoCuentaEntity that = (EstadoCuentaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado);
    }

    public List<CuentaInternaEntity> getCuentaInternaById() {
        return cuentaInternaById;
    }

    public void setCuentaInternaById(List<CuentaInternaEntity> cuentaInternaById) {
        this.cuentaInternaById = cuentaInternaById;
    }
}
