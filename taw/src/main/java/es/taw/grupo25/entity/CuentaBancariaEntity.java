package es.taw.grupo25.entity;

import es.taw.grupo25.dto.CuentaBancaria;
import es.taw.grupo25.dto.CuentaInterna;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CUENTA_BANCARIA", schema = "grupo25", catalog = "")
public class CuentaBancariaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "IBAN", nullable = false, length = 45)
    private String iban;
    @OneToOne(mappedBy = "cuentaBancariaByCuentaBancaria")
    private CuentaExternaEntity cuentaExternasById;
    @OneToOne(mappedBy = "cuentaBancariaByCuentaBancaria")
    private CuentaInternaEntity cuentaInternasById;
    @OneToMany(mappedBy = "cuentaBancariaByCuentaOrigen")
    private List<TransaccionEntity> transaccionsById_Entrantes;
    @OneToMany(mappedBy = "cuentaBancariaByCuentaDestino")
    private List<TransaccionEntity> transaccionsById_Salientes;

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

    public List<TransaccionEntity> getTransaccionsById_Entrantes() {
        return transaccionsById_Entrantes;
    }

    public void setTransaccionsById_Entrantes(List<TransaccionEntity> transaccionsById_Entrantes) {
        this.transaccionsById_Entrantes = transaccionsById_Entrantes;
    }

    public List<TransaccionEntity> getTransaccionsById_Salientes() {
        return transaccionsById_Salientes;
    }

    public void setTransaccionsById_Salientes(List<TransaccionEntity> transaccionsById_Salientes) {
        this.transaccionsById_Salientes = transaccionsById_Salientes;
    }

    public CuentaBancaria toDTO(){
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setId(this.id);
        cuenta.setIban(this.iban);
        cuenta.setCuentaExternasById(this.cuentaExternasById);
        cuenta.setCuentaInternasById(this.cuentaInternasById);
        return cuenta;
    }
}
