package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ASISTENTE", schema = "grupo25", catalog = "")
public class AsistenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "ROL_PERSONA", referencedColumnName = "ID", nullable = false)
    private RolPersonaEntity rolPersonaByRolPersona;
    @OneToMany(mappedBy = "asistenteByAsistente")
    private List<ChatEntity> chatsById;

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
        AsistenteEntity that = (AsistenteEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public RolPersonaEntity getRolPersonaByRolPersona() {
        return rolPersonaByRolPersona;
    }

    public void setRolPersonaByRolPersona(RolPersonaEntity rolPersonaByRolPersona) {
        this.rolPersonaByRolPersona = rolPersonaByRolPersona;
    }

    public List<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }
}
