package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Persona;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PERSONA", schema = "grupo25", catalog = "")
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "PRIMER_APELLIDO", nullable = false, length = 45)
    private String primerApellido;
    @Basic
    @Column(name = "SEGUNDO_APELLIDO", nullable = true, length = 45)
    private String segundoApellido;
    @Basic
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private Date fechaNacimiento;
    @OneToOne(mappedBy = "personaByPersonaId")
    private ClienteEntity clientesById;
    @OneToOne(mappedBy = "personaByPersonaId")
    private EmpleadoEntity empleadosById;
    @OneToMany(mappedBy = "personaByEmisor")
    private List<MensajeEntity> mensajesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaEntity that = (PersonaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(primerApellido, that.primerApellido) && Objects.equals(segundoApellido, that.segundoApellido) && Objects.equals(fechaNacimiento, that.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, primerApellido, segundoApellido, fechaNacimiento);
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

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }


    public Persona toDTO() {
        Persona persona = new Persona();
        persona.setId(this.id);
        persona.setDni(this.dni);
        persona.setNombre(this.nombre);
        persona.setPrimerApellido(this.primerApellido);
        persona.setSegundoApellido(this.segundoApellido);
        persona.setFechaNacimiento(this.fechaNacimiento);
        persona.setClientesById(this.clientesById == null ? null : this.clientesById.toDTO());
        persona.setEmpleadosById(this.empleadosById);
        return persona;
    }

    public Persona toDTO(Cliente cliente) {
        Persona persona = new Persona();
        persona.setId(this.id);
        persona.setDni(this.dni);
        persona.setNombre(this.nombre);
        persona.setPrimerApellido(this.primerApellido);
        persona.setSegundoApellido(this.segundoApellido);
        persona.setFechaNacimiento(this.fechaNacimiento);
        persona.setClientesById(cliente);
        persona.setEmpleadosById(this.empleadosById);
        return persona;
    }


}
