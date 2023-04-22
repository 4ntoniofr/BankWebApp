package es.taw.grupo25.dto;

import java.util.Objects;

public class Direccion {
    private Integer id;
    private String calle;
    private Integer numero;
    private String ciudad;
    private String codigoPostal;
    private String pais;

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
        Direccion direccion = (Direccion) o;
        return id.equals(direccion.id) && calle.equals(direccion.calle) && numero.equals(direccion.numero) && ciudad.equals(direccion.ciudad) && codigoPostal.equals(direccion.codigoPostal) && pais.equals(direccion.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, ciudad, codigoPostal, pais);
    }
}
