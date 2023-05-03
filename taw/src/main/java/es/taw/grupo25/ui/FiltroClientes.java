/**
 * @author Antonio Fernandez Rodriguez
 */
package es.taw.grupo25.ui;

import es.taw.grupo25.entity.EstadoClienteEntity;

public class FiltroClientes {
    private String texto;

    private String estadoCliente;

    public FiltroClientes(){
        this.texto = "";
        this.estadoCliente = "";
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }
}
