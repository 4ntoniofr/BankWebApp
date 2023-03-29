<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.RolClienteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${error != null}" >
    <p style="color:red;">
            ${error}
    </p>
</c:if>

<html>
    <head>
        <title>Autenticación</title>
    </head>
    <body>
    <h1>Autenticación de cliente:</h1>

        <form action="" method="post">
            <table>
                <tr>
                    <td>Usuario:</td> <td><input type="text" name="usuario"></td>
                </tr>
                <tr>
                    <td>Clave:</td> <td><input type="password" name="clave"> </td>
                </tr>
                <tr>
                    <td colspan="2"> <button>Enviar</button></td>
                </tr>
            </table>
        </form>

    <a href="cliente/register">Registrarse</a>
    </body>
</html>