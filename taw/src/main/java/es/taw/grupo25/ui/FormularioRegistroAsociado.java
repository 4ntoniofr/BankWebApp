/**
 * @author Jose Fco Artacho
 */
package es.taw.grupo25.ui;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Persona;
import es.taw.grupo25.dto.Usuario;
import es.taw.grupo25.entity.*;

import java.util.Date;

public class FormularioRegistroAsociado {
    Cliente clienteAsociado;
    Usuario usuarioAsociado;
    Persona personaAsociado;
    String rol;

    public FormularioRegistroAsociado(){
        clienteAsociado = new Cliente();
        usuarioAsociado = new Usuario();
        personaAsociado = new Persona();
        rol = "";

        clienteAsociado.setUsuarioByUsuarioId(usuarioAsociado);
        clienteAsociado.setPersonaByPersonaId(personaAsociado);

        clienteAsociado.setFechaInicio(new Date());
        usuarioAsociado.setFechaRegistro(new Date());
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public Persona getPersonaAsociado() {
        return personaAsociado;
    }

    public void setPersonaAsociado(Persona personaAsociado) {
        this.personaAsociado = personaAsociado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
