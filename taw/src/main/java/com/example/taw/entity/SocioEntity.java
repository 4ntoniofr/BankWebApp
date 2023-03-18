package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "SOCIO", schema = "grupo25", catalog = "")
public class SocioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "ROL_PERSONA", referencedColumnName = "ID", nullable = false)
    private RolPersonaEntity rolPersonaByRolPersona;
    @ManyToOne
    @JoinColumn(name = "EMPRESA", referencedColumnName = "ID", nullable = false)
    private EmpresaEntity empresaByEmpresa;

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

    public RolPersonaEntity getRolPersonaByRolPersona() {
        return rolPersonaByRolPersona;
    }

    public void setRolPersonaByRolPersona(RolPersonaEntity rolPersonaByRolPersona) {
        this.rolPersonaByRolPersona = rolPersonaByRolPersona;
    }

    public EmpresaEntity getEmpresaByEmpresa() {
        return empresaByEmpresa;
    }

    public void setEmpresaByEmpresa(EmpresaEntity empresaByEmpresa) {
        this.empresaByEmpresa = empresaByEmpresa;
    }
}
