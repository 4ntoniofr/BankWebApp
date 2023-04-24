package es.taw.grupo25.ui;

import java.util.Date;

public class FiltroChats {

    String nombre;

    boolean abierto;

    String ultimoMensajeAntesDe;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public String getUltimoMensajeAntesDe() {
        return ultimoMensajeAntesDe;
    }

    public void setUltimoMensajeAntesDe(String ultimoMensajeAntesDe) {
        this.ultimoMensajeAntesDe = ultimoMensajeAntesDe;
    }
}
