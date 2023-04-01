package es.taw.grupo25.ui;
import java.util.Date;

public class FiltroOperaciones {

    private int idCuenta;
    private String fechaInstruccion;
    private String fechaEjecucion;
    private String iban;

    public FiltroOperaciones(int idCuenta){
        this.idCuenta = idCuenta;
    }

    public String getFechaInstruccion() {
        return fechaInstruccion;
    }

    public void setFechaInstruccion(String fechaInstruccion) {
        this.fechaInstruccion = fechaInstruccion;
    }

    public String getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(String fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
}
