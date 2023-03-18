package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CuentaInterna", schema = "grupo25", catalog = "")
public class CuentaInternaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "moneda", nullable = false, length = 45)
    private String moneda;
    @Basic
    @Column(name = "pais", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "cantidad", nullable = false, precision = 0)
    private Double cantidad;
    @OneToOne
    @JoinColumn(name = "CuentaBancaria_id", referencedColumnName = "id", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaBancariaId;
    @ManyToOne
    @JoinColumn(name = "propietario", referencedColumnName = "id", nullable = false)
    private ClienteEntity clienteByPropietario;
    @ManyToOne
    @JoinColumn(name = "estadoCuenta", referencedColumnName = "id", nullable = false)
    private EstadoCuentaEntity estadoCuentaByEstadoCuenta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaInternaEntity that = (CuentaInternaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moneda, that.moneda) && Objects.equals(pais, that.pais) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, pais, cantidad);
    }

    public CuentaBancariaEntity getCuentaBancariaByCuentaBancariaId() {
        return cuentaBancariaByCuentaBancariaId;
    }

    public void setCuentaBancariaByCuentaBancariaId(CuentaBancariaEntity cuentaBancariaByCuentaBancariaId) {
        this.cuentaBancariaByCuentaBancariaId = cuentaBancariaByCuentaBancariaId;
    }

    public ClienteEntity getClienteByPropietario() {
        return clienteByPropietario;
    }

    public void setClienteByPropietario(ClienteEntity clienteByPropietario) {
        this.clienteByPropietario = clienteByPropietario;
    }

    public EstadoCuentaEntity getEstadoCuentaByEstadoCuenta() {
        return estadoCuentaByEstadoCuenta;
    }

    public void setEstadoCuentaByEstadoCuenta(EstadoCuentaEntity estadoCuentaByEstadoCuenta) {
        this.estadoCuentaByEstadoCuenta = estadoCuentaByEstadoCuenta;
    }
}
