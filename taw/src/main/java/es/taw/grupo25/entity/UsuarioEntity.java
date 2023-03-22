package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "USUARIO", schema = "grupo25", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "USUARIO", nullable = false, length = 45)
    private String usuario;
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "FECHA_REGISTRO", nullable = false)
    private Date fechaRegistro;
    @OneToOne(mappedBy = "usuarioByUsuarioId")
    private ClienteEntity clientesById;
    @OneToOne(mappedBy = "usuarioByUsuarioId")
    private EmpleadoEntity empleadosById;

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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(password, that.password) && Objects.equals(fechaRegistro, that.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, password, fechaRegistro);
    }

    public ClienteEntity getClientesById() {
        return clientesById;
    }

    public void setClientesById(ClienteEntity clientesById) {
        this.clientesById = clientesById;
    }

    public EmpleadoEntity getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(EmpleadoEntity empleadosById) {
        this.empleadosById = empleadosById;
    }
}
