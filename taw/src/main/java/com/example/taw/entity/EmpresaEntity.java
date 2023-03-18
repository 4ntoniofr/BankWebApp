package com.example.taw.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Empresa", schema = "grupo25", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "cliente", nullable = false)
    private Integer cliente;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "fechaCierre", nullable = true)
    private Date fechaCierre;
    @OneToOne(mappedBy = "empresaByEmpresaId")
    private ClienteEntity clientesById;
    @OneToMany(mappedBy = "empresaByEmpresaId")
    private List<SocioEntity> sociosById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity that = (EmpresaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cliente, that.cliente) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaCierre, that.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, nombre, fechaCierre);
    }

    public ClienteEntity getClientesById() {
        return clientesById;
    }

    public void setClientesById(ClienteEntity clientesById) {
        this.clientesById = clientesById;
    }

    public List<SocioEntity> getSociosById() {
        return sociosById;
    }

    public void setSociosById(List<SocioEntity> sociosById) {
        this.sociosById = sociosById;
    }
}
