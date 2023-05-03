/**
 * @author Jose Fco Artacho
 */
package es.taw.grupo25.ui;

public class FiltroSociosEmpresa {
    private String rol;
    private String estado;

    public FiltroSociosEmpresa(){
        rol = "";
        estado = "";
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
