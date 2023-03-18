package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Direccion", schema = "grupo25", catalog = "")
public class DireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "calle", nullable = false, length = 45)
    private String calle;
    @Basic
    @Column(name = "numero", nullable = false)
    private Integer numero;
    @Basic
    @Column(name = "ciudad", nullable = false, length = 45)
    private String ciudad;
    @Basic
    @Column(name = "codigoPostal", nullable = false, length = 45)
    private String codigoPostal;
    @Basic
    @Column(name = "pais", nullable = false, length = 45)
    private String pais;
    @OneToMany(mappedBy = "direccionByDireccionId")
    private List<ClienteEntity> clientesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DireccionEntity that = (DireccionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(calle, that.calle) && Objects.equals(numero, that.numero) && Objects.equals(ciudad, that.ciudad) && Objects.equals(codigoPostal, that.codigoPostal) && Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, ciudad, codigoPostal, pais);
    }

    public List<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }
}
