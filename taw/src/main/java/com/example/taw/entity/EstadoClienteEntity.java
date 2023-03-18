package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EstadoCliente", schema = "grupo25", catalog = "")
public class EstadoClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEstadoCliente", nullable = false)
    private Integer idEstadoCliente;
    @Basic
    @Column(name = "estado", nullable = false, length = 45)
    private String estado;
    @OneToMany(mappedBy = "estadoClienteByEstadoClienteIdEstadoCliente")
    private List<ClienteEntity> clientesByIdEstadoCliente;

    public Integer getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
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
        EstadoClienteEntity that = (EstadoClienteEntity) o;
        return Objects.equals(idEstadoCliente, that.idEstadoCliente) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstadoCliente, estado);
    }

    public List<ClienteEntity> getClientesByIdEstadoCliente() {
        return clientesByIdEstadoCliente;
    }

    public void setClientesByIdEstadoCliente(List<ClienteEntity> clientesByIdEstadoCliente) {
        this.clientesByIdEstadoCliente = clientesByIdEstadoCliente;
    }
}
