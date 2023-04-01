package es.taw.grupo25.ui;

import es.taw.grupo25.entity.CuentaBancariaEntity;
import es.taw.grupo25.entity.CuentaInternaEntity;

public class FormularioRegistroCuenta {
    private CuentaBancariaEntity cuentaBancaria;
    private CuentaInternaEntity cuentaInterna;

    public FormularioRegistroCuenta(){
        cuentaBancaria = new CuentaBancariaEntity();

        cuentaInterna = new CuentaInternaEntity();
        Byte byteValue = 0;
        cuentaInterna.setBloqueada(byteValue);


        cuentaInterna.setCuentaBancariaByCuentaBancaria(cuentaBancaria);

    }

    public CuentaBancariaEntity getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaEntity cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public CuentaInternaEntity getCuentaInterna() {
        return cuentaInterna;
    }

    public void setCuentaInterna(CuentaInternaEntity cuentaInterna) {
        this.cuentaInterna = cuentaInterna;
    }
}
