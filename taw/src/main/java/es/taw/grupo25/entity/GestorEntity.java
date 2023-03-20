package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "GESTOR", schema = "grupo25", catalog = "")
public class GestorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "ROL_PERSONA", referencedColumnName = "ID", nullable = false)
    private RolPersonaEntity rolPersonaByRolPersona;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestorEntity that = (GestorEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public RolPersonaEntity getRolPersonaByRolPersona() {
        return rolPersonaByRolPersona;
    }

    public void setRolPersonaByRolPersona(RolPersonaEntity rolPersonaByRolPersona) {
        this.rolPersonaByRolPersona = rolPersonaByRolPersona;
    }
}
