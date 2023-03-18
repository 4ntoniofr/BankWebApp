package com.example.taw.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Asistente", schema = "grupo25", catalog = "")
public class AsistenteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "RolPersona_id", referencedColumnName = "id", nullable = false)
    private RolPersonaEntity rolPersonaByRolPersonaId;
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

    public RolPersonaEntity getRolPersonaByRolPersonaId() {
        return rolPersonaByRolPersonaId;
    }

    public void setRolPersonaByRolPersonaId(RolPersonaEntity rolPersonaByRolPersonaId) {
        this.rolPersonaByRolPersonaId = rolPersonaByRolPersonaId;
    }

    public List<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(List<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }
}
