package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Gestor", schema = "grupo25", catalog = "")
public class GestorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToOne(mappedBy = "gestorById")
    private RolPersonaEntity rolPersonaByRolPersonaId;

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

    public RolPersonaEntity getRolPersonaByRolPersonaId() {
        return rolPersonaByRolPersonaId;
    }

    public void setRolPersonaByRolPersonaId(RolPersonaEntity rolPersonaByRolPersonaId) {
        this.rolPersonaByRolPersonaId = rolPersonaByRolPersonaId;
    }
}
