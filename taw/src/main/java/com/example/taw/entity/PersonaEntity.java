package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Persona", schema = "grupo25", catalog = "")
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "DNI", nullable = false, length = 45)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "primerApellido", nullable = false, length = 45)
    private String primerApellido;
    @Basic
    @Column(name = "segundoApellido", nullable = true, length = 45)
    private String segundoApellido;
    @Basic
    @Column(name = "fechaNacimiento", nullable = false, length = 45)
    private String fechaNacimiento;
    @OneToMany(mappedBy = "personaByEmisor")
    private List<MensajeEntity> mensajesById;
    @OneToOne(mappedBy = "personaByPersona")
    private RolPersonaEntity rolPersonasById;

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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
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

    public List<MensajeEntity> getMensajesById() {
        return mensajesById;
    }

    public void setMensajesById(List<MensajeEntity> mensajesById) {
        this.mensajesById = mensajesById;
    }

    public RolPersonaEntity getRolPersonasById() {
        return rolPersonasById;
    }

    public void setRolPersonasById(RolPersonaEntity rolPersonasById) {
        this.rolPersonasById = rolPersonasById;
    }
}
