<%
    /**
     * @author Jose Fco Artacho
     */
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    String error = (String) request.getAttribute("error");
%>

<h1>Login de empresas</h1>

<%
    if(error != null){
%>

<p style="color: red"><%= error %></p>

<%
    }
%>

<form method="post" action="/empresa/login">
    Usuario: <input type="text" name="username"><br>
    Contraseña: <input type="password" name="pwd"><br>
    <button type="submit">Login</button><br>
    <a href="/empresa/">Volver al menú de empresas</a>
</form>
</html>