<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

 Fichero hecho por:
 Valentín García Rosas

--%>

<html>
    <head>
        <title>Autenticación</title>
    </head>
    <body>
    <h1>Autenticación de usuario:</h1>

    <%
        String error = (String) request.getAttribute("error");
        if(error!=null){
    %>
        <p style="color:red;"><%=error%></p>
    <%
        }
    %>

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

    <a href="cliente/register">Registrarse como cliente</a><br>
    <a href="empresa/registrar">Registrarse como empresa</a>
    </body>
</html>