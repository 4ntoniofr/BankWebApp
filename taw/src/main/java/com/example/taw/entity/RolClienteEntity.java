package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ROL_CLIENTE", schema = "grupo25", catalog = "")
public class RolClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "ROL", nullable = false, length = 45)
    private String rol;
    @OneToMany(mappedBy = "rolClienteByRolClienteId")
    private List<ClienteEntity> clientesById;

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
        RolClienteEntity that = (RolClienteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(rol, that.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rol);
    }

    public List<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }
}
