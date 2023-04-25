package es.taw.grupo25.dto;

import es.taw.grupo25.entity.CuentaExternaEntity;
import es.taw.grupo25.entity.CuentaInternaEntity;
import es.taw.grupo25.entity.TransaccionEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CuentaBancaria implements Serializable {
    private Integer id;
    private String iban;
    private CuentaExternaEntity cuentaExternasById;
    private CuentaInternaEntity cuentaInternasById;
    private List<Transaccion> transaccionsById_Entrantes;
    private List<Transaccion> transaccionsById_Salientes;

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

    public List<Transaccion> getTransaccionsById_Entrantes() {
        return transaccionsById_Entrantes;
    }

    public void setTransaccionsById_Entrantes(List<Transaccion> transaccionsById_Entrantes) {
        this.transaccionsById_Entrantes = transaccionsById_Entrantes;
    }

    public void setTransaccionsEntitiesById_Entrantes(List<TransaccionEntity> transaccionsById_Entrantes) {
        List<Transaccion> transaccions = new ArrayList<>();
        for (TransaccionEntity transaccion : transaccionsById_Entrantes) {
            transaccions.add(transaccion.toDTO());
        }
        this.transaccionsById_Entrantes = transaccions;
    }

    public List<Transaccion> getTransaccionsById_Salientes() {
        return transaccionsById_Salientes;
    }

    public void setTransaccionsById_Salientes(List<Transaccion> transaccionsById_Salientes) {
        this.transaccionsById_Salientes = transaccionsById_Salientes;
    }

    public void setTransaccionsEntitiesById_Salientes(List<TransaccionEntity> transaccionsById_Salientes) {
        List<Transaccion> transaccions = new ArrayList<>();
        for (TransaccionEntity transaccion : transaccionsById_Salientes) {
            transaccions.add(transaccion.toDTO());
        }
        this.transaccionsById_Salientes = transaccions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return id.equals(that.id) && iban.equals(that.iban) && Objects.equals(cuentaExternasById, that.cuentaExternasById) && Objects.equals(cuentaInternasById, that.cuentaInternasById) && Objects.equals(transaccionsById_Entrantes, that.transaccionsById_Entrantes) && Objects.equals(transaccionsById_Salientes, that.transaccionsById_Salientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, cuentaExternasById, cuentaInternasById, transaccionsById_Entrantes, transaccionsById_Salientes);
    }
}
