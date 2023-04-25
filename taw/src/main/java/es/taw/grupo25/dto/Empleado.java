package es.taw.grupo25.dto;

<<<<<<< HEAD
import java.util.List;
import java.util.Objects;

public class Empleado {

    // No he incluido el atributo de las autorizaciones, si alguien lo necesita que lo añada

    private Integer id;

    private List<Chat> chatsById;

    private List<Cliente> clientesById;

    private Usuario usuarioByUsuarioId;

    private Persona personaByPersonaId;

    private int rolEmpleadoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado that = (Empleado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
=======
import es.taw.grupo25.entity.AutorizacionEntity;
import es.taw.grupo25.entity.ClienteEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empleado implements Serializable {
    private Integer id;
    private List<Autorizacion> autorizacionsById;
    private List<Cliente> clientesById;
    private Usuario usuarioByUsuarioId;
    private Persona personaByPersonaId;
    private RolEmpleado rolEmpleadoByRolEmpleadoId;
>>>>>>> main

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
    public List<Chat> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<Chat> chatsById) {
        this.chatsById = chatsById;
=======
    public List<Autorizacion> getAutorizacionsById() {
        return autorizacionsById;
    }

    public void setAutorizacionsById(List<Autorizacion> autorizacionsById) {
        this.autorizacionsById = autorizacionsById;
    }

    public void setAutorizacionsEntitieById(List<AutorizacionEntity> autorizacionsById) {
        List<Autorizacion> autorizacions = new ArrayList<>();
        for (AutorizacionEntity autorizacion:autorizacionsById) {
            autorizacions.add(autorizacion.toDTO());
        }

        this.autorizacionsById = autorizacions;
>>>>>>> main
    }

    public List<Cliente> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<Cliente> clientesById) {
        this.clientesById = clientesById;
    }

<<<<<<< HEAD
=======
    public void setClientesEntitiesById(List<ClienteEntity> clienteEntities){
        List<Cliente> clientes = new ArrayList<>();
        for (ClienteEntity cliente: clienteEntities) {
            clientes.add(cliente.toDTO());
        }
        this.clientesById = clientes;
    }

>>>>>>> main
    public Usuario getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(Usuario usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public Persona getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(Persona personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }

<<<<<<< HEAD
    public int getRolEmpleadoId() {
        return rolEmpleadoId;
    }

    public void setRolEmpleadoId(int rolEmpleadoId) {
        this.rolEmpleadoId = rolEmpleadoId;
=======
    public RolEmpleado getRolEmpleadoByRolEmpleadoId() {
        return rolEmpleadoByRolEmpleadoId;
    }

    public void setRolEmpleadoByRolEmpleadoId(RolEmpleado rolEmpleado) {
        this.rolEmpleadoByRolEmpleadoId = rolEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado that = (Empleado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
>>>>>>> main
    }
}
