package es.taw.grupo25.ui;

import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.EmpresaEntity;
import es.taw.grupo25.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormularioRegistroEmpresa {
    public FormularioRegistroEmpresa(){
        empresa = new EmpresaEntity();
        clienteEmpresa = new ClienteEntity();
        usuarioEmpresa = new UsuarioEntity();
        asociadoEmpresa = new FormularioRegistroAsociado();

        empresa.setClienteByClienteId(clienteEmpresa);
        clienteEmpresa.setUsuarioByUsuarioId(usuarioEmpresa);

        asociadoEmpresa.getClienteAsociado().setEmpresaByEmpresaSocio(empresa);

        clienteEmpresa.setFechaInicio(new Date());
        usuarioEmpresa.setFechaRegistro(new Date());
    }
    EmpresaEntity empresa;
    ClienteEntity clienteEmpresa;
    UsuarioEntity usuarioEmpresa;
    FormularioRegistroAsociado asociadoEmpresa;

    public EmpresaEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }

    public ClienteEntity getClienteEmpresa() {
        return clienteEmpresa;
    }

    public void setClienteEmpresa(ClienteEntity clienteEmpresa) {
        this.clienteEmpresa = clienteEmpresa;
    }

    public UsuarioEntity getUsuarioEmpresa() {
        return usuarioEmpresa;
    }

    public void setUsuarioEmpresa(UsuarioEntity usuarioEmpresa) {
        this.usuarioEmpresa = usuarioEmpresa;
    }

    public FormularioRegistroAsociado getAsociadoEmpresa() {
        return asociadoEmpresa;
    }

    public void setAsociadoEmpresa(FormularioRegistroAsociado asociadoEmpresa) {
        this.asociadoEmpresa = asociadoEmpresa;
    }
}
