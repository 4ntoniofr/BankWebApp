package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Cliente;
import jakarta.persistence.*;

import java.util.Date;
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
    @ManyToOne
    @JoinColumn(name = "AUTORIZADOR", referencedColumnName = "ID")
    private EmpleadoEntity empleadoByAutorizador;
    @ManyToOne
    @JoinColumn(name = "EMPRESA_SOCIO", referencedColumnName = "ID")
    private EmpresaEntity empresaByEmpresaSocio;
    @OneToMany(mappedBy = "clienteByPropietario")
    private List<CuentaInternaEntity> cuentaInternasById;
    @OneToOne(mappedBy = "clienteByClienteId")
    private EmpresaEntity empresasById;
    @OneToMany(mappedBy = "clienteByCliente")
    private List<TransaccionEntity> transaccionsById;

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

    public EmpleadoEntity getEmpleadoByAutorizador() {
        return empleadoByAutorizador;
    }

    public void setEmpleadoByAutorizador(EmpleadoEntity empleadoByAutorizador) {
        this.empleadoByAutorizador = empleadoByAutorizador;
    }

    public EmpresaEntity getEmpresaByEmpresaSocio() {
        return empresaByEmpresaSocio;
    }

    public void setEmpresaByEmpresaSocio(EmpresaEntity empresaByEmpresaSocio) {
        this.empresaByEmpresaSocio = empresaByEmpresaSocio;
    }

    public List<CuentaInternaEntity> getCuentaInternasById() {
        return cuentaInternasById;
    }

    public void setCuentaInternasById(List<CuentaInternaEntity> cuentaInternasById) {
        this.cuentaInternasById = cuentaInternasById;
    }

    public EmpresaEntity getEmpresasById() {
        return empresasById;
    }

    public void setEmpresasById(EmpresaEntity empresasById) {
        this.empresasById = empresasById;
    }

    public List<TransaccionEntity> getTransaccionsById() {
        return transaccionsById;
    }

    public void setTransaccionsById(List<TransaccionEntity> transaccionsById) {
        this.transaccionsById = transaccionsById;
    }

    public Cliente toDTO(){
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setFechaInicio(this.fechaInicio);
        cliente.setChatsById(this.chatsById);
        cliente.setEstadoClienteByEstadoCliente(this.estadoClienteByEstadoCliente);
        cliente.setDireccionByDireccion(this.direccionByDireccion);
        cliente.setUsuarioByUsuarioId(this.usuarioByUsuarioId);
        cliente.setPersonaByPersonaId(this.personaByPersonaId);
        cliente.setRolClienteByRolClienteId(this.rolClienteByRolClienteId);
        cliente.setTransaccionsById(this.transaccionsById);
        return cliente;
    }
}
