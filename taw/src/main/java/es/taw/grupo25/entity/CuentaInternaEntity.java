package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CUENTA_INTERNA", schema = "grupo25", catalog = "")
public class CuentaInternaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MONEDA", nullable = false, length = 45)
    private String moneda;
    @Basic
    @Column(name = "PAIS", nullable = false, length = 45)
    private String pais;
    @Basic
    @Column(name = "CANTIDAD", nullable = false, precision = 0)
    private Double cantidad;
    @Basic
    @Column(name = "BLOQUEADA", nullable = false)
    private Byte bloqueada;
    @OneToMany(mappedBy = "cuentaInternaByCuentaInternaId")
    private List<AutorizacionEntity> autorizacionsById;
    @OneToOne
    @JoinColumn(name = "CUENTA_BANCARIA", referencedColumnName = "ID", nullable = false)
    private CuentaBancariaEntity cuentaBancariaByCuentaBancaria;
    @ManyToOne
    @JoinColumn(name = "PROPIETARIO", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByPropietario;
    @ManyToOne
    @JoinColumn(name = "ESTADO_CUENTA", referencedColumnName = "ID", nullable = false)
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

    public Byte getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(Byte bloqueada) {
        this.bloqueada = bloqueada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaInternaEntity that = (CuentaInternaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moneda, that.moneda) && Objects.equals(pais, that.pais) && Objects.equals(cantidad, that.cantidad) && Objects.equals(bloqueada, that.bloqueada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, pais, cantidad, bloqueada);
    }

    public List<AutorizacionEntity> getAutorizacionsById() {
        return autorizacionsById;
    }

    public void setAutorizacionsById(List<AutorizacionEntity> autorizacionsById) {
        this.autorizacionsById = autorizacionsById;
    }

    public CuentaBancariaEntity getCuentaBancariaByCuentaBancaria() {
        return cuentaBancariaByCuentaBancaria;
    }

    public void setCuentaBancariaByCuentaBancaria(CuentaBancariaEntity cuentaBancariaByCuentaBancaria) {
        this.cuentaBancariaByCuentaBancaria = cuentaBancariaByCuentaBancaria;
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
