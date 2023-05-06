<%/**
* @author Antonio Fernandez Rodriguez
*/%>
<%@ page import="es.taw.grupo25.entity.UsuarioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Login</title>
</head>

<body>
<h1>Login de gestor</h1>

<%if (error != null) {%>
<%=error%>
<%}%>

<form method="post" action="/gestor/login">
    Usuario: <input type="text" name="username"><br>
    Contraseña: <input type="password" name="password"><br>
    <button type="submit">Login</button>
</form>
</body>
</html>