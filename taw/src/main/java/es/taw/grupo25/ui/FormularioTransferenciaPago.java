package es.taw.grupo25.ui;

import es.taw.grupo25.entity.CuentaBancariaEntity;
import es.taw.grupo25.entity.PagoEntity;
import es.taw.grupo25.entity.TransaccionEntity;

public class FormularioTransferenciaPago {
    private TransaccionEntity transaccion;
    private PagoEntity pago;
    private String cuentaDestino;

    public FormularioTransferenciaPago(){
        this.transaccion =  new TransaccionEntity();
        this.pago = new PagoEntity();
        cuentaDestino = null;

        transaccion.setPagosById(pago);
        pago.setTransaccionByTransaccion(transaccion);
    }

    public TransaccionEntity getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(TransaccionEntity transaccion) {
        this.transaccion = transaccion;
    }

    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
