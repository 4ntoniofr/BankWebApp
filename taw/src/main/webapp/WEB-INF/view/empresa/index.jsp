<%
    /**
     * @author Jose Fco Artacho
     */
%>
<%@ page import="es.taw.grupo25.dto.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String error = (String) request.getAttribute("error");
%>

<html>
<h1>Pagina principal de empresas</h1>

<%
    if(usuario != null){
%>

<h2>Bienvenido, <%= usuario.getUsuario() %></h2>

<%
    }
%>

<%
    if(error != null){
%>

<p style="color: red"><%= error %></p>

<%
    }
%>

<ul>
    <%
        if(usuario != null){
    %>

    <li><a href="/cliente/cuentas">Realizar transferencias</a></li>

    <%
            if(usuario.getClientesById().getEmpresasById() == null){
                //OPCIONES ASOCIADO
    %>

    <li><a href="/empresa/updateAsociado">Modificar datos personales</a></li>
    <li><a href="/empresa/transferencias">Mostrar transferencias con las cuentas empresariales</a></li>

    <%
                if(usuario.getClientesById().getRolClienteByRolClienteId().getRol().equals("SOCIO")){
    %>

    <li><a href="/empresa/sociosEmpresa">Mostrar socios/autorizados de la empresa</a></li>

    <%
                }
        if(usuario.getClientesById().getEstadoClienteByEstadoCliente().getEstado().equals("BLOQUEADO")){
    %>

    <li><a href="/empresa/solicitudDesbloqueo">Solicitar desbloqueo</a></li>

    <%
        }
    %>

    <%
            }else{
                //OPCIONES EMPRESA
    %>

    <li><a href="/empresa/addAsociado">Añadir asociado</a></li>

    <%
            }
    %>
    <%
        }
    %>
    <br>
    <a href="/">Volver a la página principal</a>
</ul>
</html>