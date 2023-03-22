package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CUENTA_EXTERNA", schema = "grupo25", catalog = "")
public class CuentaExternaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "SOSPECHOSA", nullable = false)
    private Byte sospechosa;
    @OneToOne
    @JoinColumn(name = "CUENTA_BANCARIA", referencedColumnName = "ID", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaBancaria;
    @ManyToOne
    @JoinColumn(name = "ENTIDAD_BANCARIA", referencedColumnName = "ID", nullable = false)
    private EntidadBancariaEntity entidadBancariaByEntidadBancaria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getSospechosa() {
        return sospechosa;
    }

    public void setSospechosa(Byte sospechosa) {
        this.sospechosa = sospechosa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaExternaEntity that = (CuentaExternaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sospechosa, that.sospechosa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sospechosa);
    }

    public CuentaBancariaEntity getCuentaBancariaByCuentaBancaria() {
        return cuentaBancariaByCuentaBancaria;
    }

    public void setCuentaBancariaByCuentaBancaria(CuentaBancariaEntity cuentaBancariaByCuentaBancaria) {
        this.cuentaBancariaByCuentaBancaria = cuentaBancariaByCuentaBancaria;
    }

    public EntidadBancariaEntity getEntidadBancariaByEntidadBancaria() {
        return entidadBancariaByEntidadBancaria;
    }

    public void setEntidadBancariaByEntidadBancaria(EntidadBancariaEntity entidadBancariaByEntidadBancaria) {
        this.entidadBancariaByEntidadBancaria = entidadBancariaByEntidadBancaria;
    }
}
