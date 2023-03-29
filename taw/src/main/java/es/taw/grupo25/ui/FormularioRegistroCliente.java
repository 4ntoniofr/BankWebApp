package es.taw.grupo25.ui;

import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.DireccionEntity;
import es.taw.grupo25.entity.PersonaEntity;
import es.taw.grupo25.entity.UsuarioEntity;

import java.util.Date;

public class FormularioRegistroCliente {
    PersonaEntity persona;
    UsuarioEntity usuario;
    ClienteEntity cliente;

    public FormularioRegistroCliente(){
        persona = new PersonaEntity();
        usuario = new UsuarioEntity();
        cliente = new ClienteEntity();


        cliente.setPersonaByPersonaId(persona);
        cliente.setUsuarioByUsuarioId(usuario);

        cliente.setFechaInicio(new Date());
        usuario.setFechaRegistro(new Date());

    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
