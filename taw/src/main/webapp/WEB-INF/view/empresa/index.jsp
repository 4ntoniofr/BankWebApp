<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cliente cliente = (Cliente) session.getAttribute("cliente");
    String error = (String) request.getAttribute("error");
%>

<html>
<h1>Pagina principal de empresas</h1>

<%
    if(cliente != null){
%>

<h2>Bienvenido, <%= cliente.getUsuarioByUsuarioId().getUsuario() %></h2>

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
        if(cliente == null){
    %>

    <li><a href="/empresa/login">Login</a></li>
    <li><a href="/empresa/registrar">Registrar empresa</a></li>

    <%
        }else{
    %>

    <li><a href="/empresa/logout">Logout</a></li>

    <%
            if(cliente.getEmpresasById() == null){
                //OPCIONES ASOCIADO
    %>

    <li><a href="/empresa/updateAsociado">Modificar datos personales</a></li>
    <li><a href="/empresa/sociosEmpresa">Mostrar socios/autorizados de la empresa</a></li>

    <%
        if(cliente.getEstadoClienteByEstadoCliente().getEstado().equals("BLOQUEADO")){
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
</ul>
</html>