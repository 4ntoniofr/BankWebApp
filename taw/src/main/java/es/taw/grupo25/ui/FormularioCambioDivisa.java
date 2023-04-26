package es.taw.grupo25.ui;

import es.taw.grupo25.dto.CuentaInterna;

public class FormularioCambioDivisa {
    private Integer monedaId;
    private CuentaInterna cuentaInterna;

    public FormularioCambioDivisa(CuentaInterna cuentaInterna){
        this.cuentaInterna = cuentaInterna;
    }

    public Integer getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(Integer monedaId) {
        this.monedaId = monedaId;
    }

    public CuentaInterna getCuentaInterna() {
        return cuentaInterna;
    }

    public void setCuentaInterna(CuentaInterna cuentaInterna) {
        this.cuentaInterna = cuentaInterna;
    }
}
