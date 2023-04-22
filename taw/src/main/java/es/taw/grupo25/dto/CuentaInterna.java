package es.taw.grupo25.dto;
import java.util.Objects;

public class CuentaInterna {
    private Integer id;
    private String pais;
    private Double cantidad;
    private Boolean bloqueada;
    private CuentaBancaria cuentaBancariaByCuentaBancaria;
    private Cliente clienteByPropietario;
    private EstadoCuenta estadoCuentaByEstadoCuenta;
    private Integer monedaByMoneda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(Boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public CuentaBancaria getCuentaBancariaByCuentaBancaria() {
        return cuentaBancariaByCuentaBancaria;
    }

    public void setCuentaBancariaByCuentaBancaria(CuentaBancaria cuentaBancariaByCuentaBancaria) {
        this.cuentaBancariaByCuentaBancaria = cuentaBancariaByCuentaBancaria;
    }

    public Cliente getClienteByPropietario() {
        return clienteByPropietario;
    }

    public void setClienteByPropietario(Cliente clienteByPropietario) {
        this.clienteByPropietario = clienteByPropietario;
    }

    public EstadoCuenta getEstadoCuentaByEstadoCuenta() {
        return estadoCuentaByEstadoCuenta;
    }

    public void setEstadoCuentaByEstadoCuenta(EstadoCuenta estadoCuentaByEstadoCuenta) {
        this.estadoCuentaByEstadoCuenta = estadoCuentaByEstadoCuenta;
    }

    public Integer getMonedaByMoneda() {
        return monedaByMoneda;
    }

    public void setMonedaByMoneda(Integer monedaByMoneda) {
        this.monedaByMoneda = monedaByMoneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaInterna that = (CuentaInterna) o;
        return id.equals(that.id) && pais.equals(that.pais) && cantidad.equals(that.cantidad) && bloqueada.equals(that.bloqueada) && cuentaBancariaByCuentaBancaria.equals(that.cuentaBancariaByCuentaBancaria) && clienteByPropietario.equals(that.clienteByPropietario) && estadoCuentaByEstadoCuenta.equals(that.estadoCuentaByEstadoCuenta) && monedaByMoneda.equals(that.monedaByMoneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pais, cantidad, bloqueada, cuentaBancariaByCuentaBancaria, clienteByPropietario, estadoCuentaByEstadoCuenta, monedaByMoneda);
    }
}