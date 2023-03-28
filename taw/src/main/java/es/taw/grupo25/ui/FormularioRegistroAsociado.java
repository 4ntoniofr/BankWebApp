package es.taw.grupo25.ui;

import es.taw.grupo25.entity.*;

import java.util.Date;

public class FormularioRegistroAsociado {
    public FormularioRegistroAsociado(){
        clienteAsociado = new ClienteEntity();
        usuarioAsociado = new UsuarioEntity();
        personaAsociado = new PersonaEntity();

        clienteAsociado.setUsuarioByUsuarioId(usuarioAsociado);
        clienteAsociado.setPersonaByPersonaId(personaAsociado);

        clienteAsociado.setFechaInicio(new Date());
        usuarioAsociado.setFechaRegistro(new Date());
    }
    ClienteEntity clienteAsociado;
    UsuarioEntity usuarioAsociado;
    PersonaEntity personaAsociado;

    public ClienteEntity getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(ClienteEntity clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public UsuarioEntity getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(UsuarioEntity usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public PersonaEntity getPersonaAsociado() {
        return personaAsociado;
    }

    public void setPersonaAsociado(PersonaEntity personaAsociado) {
        this.personaAsociado = personaAsociado;
    }
}
