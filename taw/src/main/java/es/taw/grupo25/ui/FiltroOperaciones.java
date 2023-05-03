package es.taw.grupo25.ui;


public class FiltroOperaciones {

    private int idCuenta;
    private String fechaInstruccion;
    private String fechaEjecucion;
    private String ibanOrigen;
    private String iban;
    private String pagoingreso;
    private String orden;
    private String ascdesc;

    public FiltroOperaciones(int idCuenta) {
        this.idCuenta = idCuenta;
        this.orden = "";
        this.ascdesc = "";
        pagoingreso = "none";
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

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getAscdesc() {
        return ascdesc;
    }

    public void setAscdesc(String ascdesc) {
        this.ascdesc = ascdesc;
    }

    public String getIbanOrigen() {
        return ibanOrigen;
    }

    public void setIbanOrigen(String ibanOrigen) {
        this.ibanOrigen = ibanOrigen;
    }

    public String getPagoingreso() {
        return pagoingreso;
    }

    public void setPagoingreso(String pagoingreso) {
        this.pagoingreso = pagoingreso;
    }
}
