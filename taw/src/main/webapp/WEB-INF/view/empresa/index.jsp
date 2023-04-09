<%@ page import="es.taw.grupo25.entity.EmpresaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.ClienteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<EmpresaEntity> lista = (List<EmpresaEntity>) request.getAttribute("empresas");
    ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
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