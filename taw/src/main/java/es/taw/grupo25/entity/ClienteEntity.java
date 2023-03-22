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
    @Column(name = "FECHA_INICIO", nullable = false)
    private Date fechaInicio;
    @OneToMany(mappedBy = "clienteByClienteId")
    private List<ChatEntity> chatsById;
    @ManyToOne
    @JoinColumn(name = "ESTADO_CLIENTE", referencedColumnName = "ID", nullable = false)
    private EstadoClienteEntity estadoClienteByEstadoCliente;
    @ManyToOne
    @JoinColumn(name = "DIRECCION", referencedColumnName = "ID", nullable = false)
    private DireccionEntity direccionByDireccion;
    @OneToOne
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", nullable = false)
    private UsuarioEntity usuarioByUsuarioId;
    @OneToOne
    @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID")
    private PersonaEntity personaByPersonaId;
    @ManyToOne
    @JoinColumn(name = "ROL_CLIENTE_ID", referencedColumnName = "ID", nullable = false)
    private RolClienteEntity rolClienteByRolClienteId;
    @OneToMany(mappedBy = "clienteByPropietario")
    private List<CuentaInternaEntity> cuentaInternasById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private List<EmpleadoEntity> empleadosById;
    @OneToMany(mappedBy = "clienteByClienteId")
    private List<EmpresaEntity> empresasById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, that.id) && Objects.equals(fechaInicio, that.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaInicio);
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

    public UsuarioEntity getUsuarioByUsuarioId() {
        return usuarioByUsuarioId;
    }

    public void setUsuarioByUsuarioId(UsuarioEntity usuarioByUsuarioId) {
        this.usuarioByUsuarioId = usuarioByUsuarioId;
    }

    public PersonaEntity getPersonaByPersonaId() {
        return personaByPersonaId;
    }

    public void setPersonaByPersonaId(PersonaEntity personaByPersonaId) {
        this.personaByPersonaId = personaByPersonaId;
    }

    public RolClienteEntity getRolClienteByRolClienteId() {
        return rolClienteByRolClienteId;
    }

    public void setRolClienteByRolClienteId(RolClienteEntity rolClienteByRolClienteId) {
        this.rolClienteByRolClienteId = rolClienteByRolClienteId;
    }

    public List<CuentaInternaEntity> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(List<CuentaInternaEntity> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }

    public List<EmpleadoEntity> getEmpleadosById() {
        return empleadosById;
    }

    public void setEmpleadosById(List<EmpleadoEntity> empleadosById) {
        this.empleadosById = empleadosById;
    }

    public List<EmpresaEntity> getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(List<EmpresaEntity> empresasById) {
        this.empresasById = empresasById;
    }
}
