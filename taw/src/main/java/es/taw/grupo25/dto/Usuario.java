package es.taw.grupo25.dto;

import es.taw.grupo25.entity.ClienteEntity;

import java.util.Objects;

public class Usuario {
    private Integer id;
    private String usuario;
    private String password;
    private Cliente clientesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cliente getClientesById() {
        return clientesById;
    }

    public void setClientesById(Cliente clientesById) {
        this.clientesById = clientesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(id, usuario1.id) && Objects.equals(usuario, usuario1.usuario) && Objects.equals(password, usuario1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, password);
    }
}
