<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Desbloqueo de Cuenta</title>
    </head>

    <body>
        <h1>Desbloqueo de Cuenta:</h1>
        <%
            CuentaInternaEntity cuenta = (CuentaInternaEntity) request.getAttribute("cuenta");
            if(cuenta == null){
        %>

        <h2>No hay ninguna cuenta asociada a este usuario</h2>

    <%
        } else{
    %>
        <h2>Sí hay una cuenta asociada a este usuario</h2>

    <%
        }
    %>

    </body>
</html>