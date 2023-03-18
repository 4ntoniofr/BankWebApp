package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Socio", schema = "grupo25", catalog = "")
public class SocioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "RolPersona_id", referencedColumnName = "id", nullable = false)
    private RolPersonaEntity rolPersonaByRolPersonaId;
    @ManyToOne
    @JoinColumn(name = "Empresa_id", referencedColumnName = "id", nullable = false)
    private EmpresaEntity empresaByEmpresaId;

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
        SocioEntity that = (SocioEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public RolPersonaEntity getRolPersonaByRolPersonaId() {
        return rolPersonaByRolPersonaId;
    }

    public void setRolPersonaByRolPersonaId(RolPersonaEntity rolPersonaByRolPersonaId) {
        this.rolPersonaByRolPersonaId = rolPersonaByRolPersonaId;
    }

    public EmpresaEntity getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(EmpresaEntity empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }
}
