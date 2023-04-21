package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Moneda;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MONEDA", schema = "grupo25", catalog = "")
public class MonedaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "MONEDA", nullable = false, length = 45)
    private String moneda;
    @Basic
    @Column(name = "CAMBIO_EURO", nullable = false, precision = 0)
    private Double cambioEuro;
    @OneToMany(mappedBy = "monedaByMonedaCompra")
    private List<CambioDivisaEntity> cambioDivisasById;
    @OneToMany(mappedBy = "monedaByMonedaVenta")
    private List<CambioDivisaEntity> cambioDivisasById_0;
    @OneToMany(mappedBy = "monedaByMoneda")
    private List<CuentaInternaEntity> cuentaInternasById;

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

    public Double getCambioEuro() {
        return cambioEuro;
    }

    public void setCambioEuro(Double cambioEuro) {
        this.cambioEuro = cambioEuro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonedaEntity that = (MonedaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(moneda, that.moneda) && Objects.equals(cambioEuro, that.cambioEuro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moneda, cambioEuro);
    }

    public List<CambioDivisaEntity> getCambioDivisasById() {
        return cambioDivisasById;
    }

    public void setCambioDivisasById(List<CambioDivisaEntity> cambioDivisasById) {
        this.cambioDivisasById = cambioDivisasById;
    }

    public List<CambioDivisaEntity> getCambioDivisasById_0() {
        return cambioDivisasById_0;
    }

    public void setCambioDivisasById_0(List<CambioDivisaEntity> cambioDivisasById_0) {
        this.cambioDivisasById_0 = cambioDivisasById_0;
    }

    public List<CuentaInternaEntity> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(List<CuentaInternaEntity> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }

    public Moneda toDTO(){
        Moneda moneda = new Moneda();
        moneda.setId(this.id);
        moneda.setMoneda(this.moneda);
        moneda.setCambioEuro(this.cambioEuro);
        return moneda;
    }

}
