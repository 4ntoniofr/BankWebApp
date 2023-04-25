package es.taw.grupo25.dto;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Chat> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<Chat> chatsById) {
        this.chatsById = chatsById;
    }

    public List<Cliente> getClientesById() {
        return clientesById;
    }

    public void setClientesById(List<Cliente> clientesById) {
        this.clientesById = clientesById;
    }

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

    public int getRolEmpleadoId() {
        return rolEmpleadoId;
    }

    public void setRolEmpleadoId(int rolEmpleadoId) {
        this.rolEmpleadoId = rolEmpleadoId;
    }
}
