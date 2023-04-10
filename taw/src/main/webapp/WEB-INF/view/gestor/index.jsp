<%@ page import="es.taw.grupo25.entity.UsuarioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UsuarioEntity usuario = (UsuarioEntity) session.getAttribute("usuario");
%>
<html>
<head>
    <title>Home gestor</title>
</head>
<body>
<h1>Pagina principal de gestor</h1>

<ul>
    <%if (usuario == null) {%>
    <li><a href="/gestor/login">Login</a></li>
    <%} else {%>
    <li><a href="/gestor/pendientes">Ver clientes/empresas pendientes de autorizar</a></li>
    <li><a href="/gestor/clientes">Ver clientes/empresas del sistema</a></li>
    <li><a href="/gestor/inactivos">Ver clientes/empresas inactivos del sistema</a></li>
    <li><a href="/gestor/sospechosos">Ver clientes/empresas que han realizado transferencias a cuentas sospechosas</a>
    </li>
    <li><a href="/gestor/logout">Logout</a>
    </li>
    <%}%>
</ul>
</body>
</html>