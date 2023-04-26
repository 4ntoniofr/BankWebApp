package es.taw.grupo25.ui;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.Empresa;
import es.taw.grupo25.dto.Usuario;

import java.util.Date;

public class FormularioRegistroEmpresa {
    Empresa empresa;
    Cliente clienteEmpresa;
    Usuario usuarioEmpresa;
    FormularioRegistroAsociado asociadoEmpresa;

    public FormularioRegistroEmpresa(){
        empresa = new Empresa();
        clienteEmpresa = new Cliente();
        usuarioEmpresa = new Usuario();
        asociadoEmpresa = new FormularioRegistroAsociado();

        empresa.setClienteByClienteId(clienteEmpresa);
        clienteEmpresa.setUsuarioByUsuarioId(usuarioEmpresa);

        asociadoEmpresa.getClienteAsociado().setEmpresaByEmpresaSocio(empresa);

        clienteEmpresa.setFechaInicio(new Date());
        usuarioEmpresa.setFechaRegistro(new Date());
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getClienteEmpresa() {
        return clienteEmpresa;
    }

    public void setClienteEmpresa(Cliente clienteEmpresa) {
        this.clienteEmpresa = clienteEmpresa;
    }

    public Usuario getUsuarioEmpresa() {
        return usuarioEmpresa;
    }

    public void setUsuarioEmpresa(Usuario usuarioEmpresa) {
        this.usuarioEmpresa = usuarioEmpresa;
    }

    public FormularioRegistroAsociado getAsociadoEmpresa() {
        return asociadoEmpresa;
    }

    public void setAsociadoEmpresa(FormularioRegistroAsociado asociadoEmpresa) {
        this.asociadoEmpresa = asociadoEmpresa;
    }
}
