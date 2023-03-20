package es.taw.grupo25.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENTE", schema = "grupo25", catalog = "")
public class ClienteEntity {
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
    @Column(name = "FECHA_INICIO", nullable = false)
    private Date fechaInicio;
    @OneToMany(mappedBy = "clienteByCliente")
    private List<ChatEntity> chatsById;
    @ManyToOne
    @JoinColumn(name = "ESTADO_CLIENTE", referencedColumnName = "ID", nullable = false)
    private EstadoClienteEntity estadoClienteByEstadoCliente;
    @ManyToOne
    @JoinColumn(name = "DIRECCION", referencedColumnName = "ID", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToOne
    @JoinColumn(name = "EMPRESA", referencedColumnName = "ID")
    private EmpresaEntity empresaByEmpresa;
    @OneToOne
    @JoinColumn(name = "ROL_PERSONA", referencedColumnName = "ID")
    private RolPersonaEntity rolPersonaByRolPersona;
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

    public EstadoClienteEntity getEstadoClienteByEstadoCliente() {
        return estadoClienteByEstadoCliente;
    }

    public void setEstadoClienteByEstadoCliente(EstadoClienteEntity estadoClienteByEstadoCliente) {
        this.estadoClienteByEstadoCliente = estadoClienteByEstadoCliente;
    }

    public DireccionEntity getDireccionByDireccion() {
        return direccionByDireccion;
    }

    public void setDireccionByDireccion(DireccionEntity direccionByDireccion) {
        this.direccionByDireccion = direccionByDireccion;
    }

    public EmpresaEntity getEmpresaByEmpresa() {
        return empresaByEmpresa;
    }

    public void setEmpresaByEmpresa(EmpresaEntity empresaByEmpresa) {
        this.empresaByEmpresa = empresaByEmpresa;
    }

    public RolPersonaEntity getRolPersonaByRolPersona() {
        return rolPersonaByRolPersona;
    }

    public void setRolPersonaByRolPersona(RolPersonaEntity rolPersonaByRolPersona) {
        this.rolPersonaByRolPersona = rolPersonaByRolPersona;
    }

    public List<CuentaInternaEntity> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(List<CuentaInternaEntity> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }
}
