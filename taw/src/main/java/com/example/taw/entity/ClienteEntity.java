package com.example.taw.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Cliente", schema = "grupo25", catalog = "")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "usuario", nullable = false, length = 45)
    private String usuario;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;
    @OneToMany(mappedBy = "clienteByCliente")
    private List<ChatEntity> chatsById;
    @ManyToOne
    @JoinColumn(name = "EstadoCliente_idEstadoCliente", referencedColumnName = "idEstadoCliente", nullable = false)
    private EstadoClienteEntity estadoClienteByEstadoClienteIdEstadoCliente;
    @ManyToOne
    @JoinColumn(name = "Direccion_id", referencedColumnName = "id", nullable = false)
    private DireccionEntity direccionByDireccionId;
    @OneToOne
    @JoinColumn(name = "Empresa_id", referencedColumnName = "id")
    private EmpresaEntity empresaByEmpresaId;
    @OneToOne
    @JoinColumn(name = "RolPersona_id", referencedColumnName = "id")
    private RolPersonaEntity rolPersonaByRolPersonaId;
    @OneToMany(mappedBy = "clienteByPropietario")
    private List<CuentaInternaEntity> cuentaInternasById;

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(usuario, that.usuario) && Objects.equals(password, that.password) && Objects.equals(fechaInicio, that.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, password, fechaInicio);
    }

    public List<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public EstadoClienteEntity getEstadoClienteByEstadoClienteIdEstadoCliente() {
        return estadoClienteByEstadoClienteIdEstadoCliente;
    }

    public void setEstadoClienteByEstadoClienteIdEstadoCliente(EstadoClienteEntity estadoClienteByEstadoClienteIdEstadoCliente) {
        this.estadoClienteByEstadoClienteIdEstadoCliente = estadoClienteByEstadoClienteIdEstadoCliente;
    }

    public DireccionEntity getDireccionByDireccionId() {
        return direccionByDireccionId;
    }

    public void setDireccionByDireccionId(DireccionEntity direccionByDireccionId) {
        this.direccionByDireccionId = direccionByDireccionId;
    }

    public EmpresaEntity getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(EmpresaEntity empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }

    public RolPersonaEntity getRolPersonaByRolPersonaId() {
        return rolPersonaByRolPersonaId;
    }

    public void setRolPersonaByRolPersonaId(RolPersonaEntity rolPersonaByRolPersonaId) {
        this.rolPersonaByRolPersonaId = rolPersonaByRolPersonaId;
    }

    public List<CuentaInternaEntity> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(List<CuentaInternaEntity> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }
}
