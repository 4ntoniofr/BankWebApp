package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "RolPersona", schema = "grupo25", catalog = "")
public class RolPersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToOne(mappedBy = "rolPersonaByRolPersonaId")
    private AsistenteEntity asistenteById;
    @OneToOne(mappedBy = "rolPersonaByRolPersonaId")
    private ClienteEntity clienteById;
    @OneToOne
    @JoinColumn(name = "persona", referencedColumnName = "id", nullable = false)
    private PersonaEntity personaByPersona;
    @OneToOne(mappedBy = "rolPersonaByRolPersonaId")
    private SocioEntity socioById;
    @OneToOne
    private GestorEntity gestorById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolPersonaEntity that = (RolPersonaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AsistenteEntity getAsistenteById() {
        return asistenteById;
    }

    public void setAsistenteById(AsistenteEntity asistenteById) {
        this.asistenteById = asistenteById;
    }

    public ClienteEntity getClienteById() {
        return clienteById;
    }

    public void setClienteById(ClienteEntity clienteById) {
        this.clienteById = clienteById;
    }

    public PersonaEntity getPersonaByPersona() {
        return personaByPersona;
    }

    public void setPersonaByPersona(PersonaEntity personaByPersona) {
        this.personaByPersona = personaByPersona;
    }

    public SocioEntity getSocioById() {
        return socioById;
    }

    public void setSocioById(SocioEntity socioById) {
        this.socioById = socioById;
    }

    public GestorEntity getGestorById() {
        return gestorById;
    }

    public void setGestorById(GestorEntity gestorById) {
        this.gestorById = gestorById;
    }
}
