<%@ page import="es.taw.grupo25.dto.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

 Fichero hecho por:
 Todos

--%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<html>
    <head>
        <title>Página Principal</title>
    </head>

    <body>
        <h1>Bienvenido, <%= usuario.getUsuario() %></h1>
        <a href="cliente"><h2>Ir a clientes</h2></a>
        <a href="empresa/"><h2>Ir a empresas</h2></a>
        <a href="gestor/"><h2>Ir a gestor</h2></a>
        <a href="asistente/"><h2>Ir a asistente</h2></a>
        <br>
        <a href="/logout">Logout</a>
    </body>
</html>