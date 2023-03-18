package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CuentaExterna", schema = "grupo25", catalog = "")
public class CuentaExternaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "sospechosa", nullable = false)
    private Byte sospechosa;
    @OneToOne
    @JoinColumn(name = "CuentaBancaria_id", referencedColumnName = "id", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaBancariaId;
    @ManyToOne
    @JoinColumn(name = "EntidadBancaria_id", referencedColumnName = "id", nullable = false)
    private EntidadBancariaEntity entidadBancariaByEntidadBancariaId;

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

    public CuentaBancariaEntity getCuentaBancariaByCuentaBancariaId() {
        return cuentaBancariaByCuentaBancariaId;
    }

    public void setCuentaBancariaByCuentaBancariaId(CuentaBancariaEntity cuentaBancariaByCuentaBancariaId) {
        this.cuentaBancariaByCuentaBancariaId = cuentaBancariaByCuentaBancariaId;
    }

    public EntidadBancariaEntity getEntidadBancariaByEntidadBancariaId() {
        return entidadBancariaByEntidadBancariaId;
    }

    public void setEntidadBancariaByEntidadBancariaId(EntidadBancariaEntity entidadBancariaByEntidadBancariaId) {
        this.entidadBancariaByEntidadBancariaId = entidadBancariaByEntidadBancariaId;
    }
}
