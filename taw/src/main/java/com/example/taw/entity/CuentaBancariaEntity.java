package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CuentaBancaria", schema = "grupo25", catalog = "")
public class CuentaBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "iban", nullable = false, length = 45)
    private String iban;
    @OneToOne(mappedBy = "cuentaBancariaByCuentaBancariaId")
    private CuentaExternaEntity cuentaExternasById;
    @OneToOne(mappedBy = "cuentaBancariaByCuentaBancariaId")
    private CuentaInternaEntity cuentaInternasById;
    @OneToMany(mappedBy = "cuentaBancariaByCuentaOrigen")
    private List<TransaccionEntity> transaccionsById_Saliente;
    @OneToMany(mappedBy = "cuentaBancariaByCuentaDestino")
    private List<TransaccionEntity> transaccionsById_Entrante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancariaEntity that = (CuentaBancariaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban);
    }

    public CuentaExternaEntity getCuentaExternasById() {
        return cuentaExternasById;
    }

    public void setCuentaExternasById(CuentaExternaEntity cuentaExternasById) {
        this.cuentaExternasById = cuentaExternasById;
    }

    public CuentaInternaEntity getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(CuentaInternaEntity cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }

    public List<TransaccionEntity> getTransaccionsById_Saliente() {
        return transaccionsById_Saliente;
    }

    public void setTransaccionsById_Saliente(List<TransaccionEntity> transaccionsById_Saliente) {
        this.transaccionsById_Saliente = transaccionsById_Saliente;
    }

    public List<TransaccionEntity> getTransaccionsById_Entrante() {
        return transaccionsById_Entrante;
    }

    public void setTransaccionsById_Entrante(List<TransaccionEntity> transaccionsById_Entrante) {
        this.transaccionsById_Entrante = transaccionsById_Entrante;
    }
}
