<%@ page import="es.taw.grupo25.dto.Usuario" %>
<%@ page import="es.taw.grupo25.dto.Empleado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

 Fichero hecho por:
 Todos

--%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    Empleado asistente = (Empleado) session.getAttribute("asistente");
%>
<html>
    <head>
        <title>Página Principal</title>
    </head>

    <body>
        <h1>Bienvenido, <%= usuario.getUsuario() %></h1>
        <%
            if(usuario.soyCliente()){
        %>
        <a href="cliente"><h2>Ir a clientes</h2></a>
        <%
            }
            if(usuario.isEmpresa() || usuario.isAsociado()){
        %>
        <a href="empresa/"><h2>Ir a empresas</h2></a>
        <%
            }
            if (usuario.soyGestor()){
        %>
        <a href="gestor/"><h2>Ir a gestor</h2></a>

        <%
            }
            if(asistente != null){
        %>
            <a href="asistente/"><h2>Ir a asistente</h2></a>
        <%
            }
        %>
        <br>
        <a href="/logout">Logout</a>
    </body>
</html>