<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<h1>Login de empresas</h1>

<form method="post" action="/empresa/login">
    Usuario: <input type="text" name="username"><br>
    Contraseña: <input type="password" name="pwd"><br>
    <button type="submit">Login</button>
</form>
</html>