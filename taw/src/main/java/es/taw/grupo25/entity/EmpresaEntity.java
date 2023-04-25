package es.taw.grupo25.entity;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Empresa;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EMPRESA", schema = "grupo25", catalog = "")
public class EmpresaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "FECHA_CIERRE", nullable = true)
    private Date fechaCierre;
    @OneToMany(mappedBy = "empresaByEmpresaSocio")
    private List<ClienteEntity> clientesById_Socios;
    @OneToOne
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID", nullable = false)
    private ClienteEntity clienteByClienteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaEntity empresa = (EmpresaEntity) o;
        return Objects.equals(id, empresa.id) && Objects.equals(nombre, empresa.nombre) && Objects.equals(fechaCierre, empresa.fechaCierre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaCierre);
    }

    public List<ClienteEntity> getClientesById_Socios() {
        return clientesById_Socios;
    }

    public void setClientesById_Socios(List<ClienteEntity> clientesById_Socios) {
        this.clientesById_Socios = clientesById_Socios;
    }

    public ClienteEntity getClienteByClienteId() {
        return clienteByClienteId;
    }

    public void setClienteByClienteId(ClienteEntity clienteByClienteId) {
        this.clienteByClienteId = clienteByClienteId;
    }

    public Empresa toDTO(Cliente cliente){
        Empresa empresa = new Empresa();
        empresa.setClienteByClienteId(this.clienteByClienteId.toDTO());
        empresa.setFechaCierre(this.fechaCierre);
        empresa.setNombre(this.nombre);
        return empresa;
    }
}
