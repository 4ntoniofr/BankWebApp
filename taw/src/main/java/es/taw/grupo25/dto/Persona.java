package es.taw.grupo25.dto;

import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpleadoEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Persona implements Serializable {
    private Integer id;
    private String dni;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private ClienteEntity clientesById;
    private EmpleadoEntity empleadosById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id.equals(persona.id) && dni.equals(persona.dni) && nombre.equals(persona.nombre) && primerApellido.equals(persona.primerApellido) && Objects.equals(segundoApellido, persona.segundoApellido) && fechaNacimiento.equals(persona.fechaNacimiento) && Objects.equals(clientesById, persona.clientesById) && Objects.equals(empleadosById, persona.empleadosById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, primerApellido, segundoApellido, fechaNacimiento, clientesById, empleadosById);
    }
}
