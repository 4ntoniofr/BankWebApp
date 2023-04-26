package es.taw.grupo25.ui;

public class FiltroChats {

    String nombre;

    boolean soloAbiertos;

    String ultimoMensajeAntesDe;

    String orderBy;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSoloAbiertos() {
        return soloAbiertos;
    }

    public void setSoloAbiertos(boolean soloAbiertos) {
        this.soloAbiertos = soloAbiertos;
    }

    public String getUltimoMensajeAntesDe() {
        return ultimoMensajeAntesDe;
    }

    public void setUltimoMensajeAntesDe(String ultimoMensajeAntesDe) {
        this.ultimoMensajeAntesDe = ultimoMensajeAntesDe;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
