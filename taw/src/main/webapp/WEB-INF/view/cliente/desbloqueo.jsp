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
            if(cuenta != null){
        %>

        <h2>Iban de la cuenta: <%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></h2>

        <h3>Estado de la cuenta: <%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></h3>

        <%
            if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado()=="BLOQUEADA"){
        %>
        <h4><a href="#">Solicitar Desbloqueo de la cuenta</a></h4>

        <%
            }
        %>
    <%
        } else{
    %>
        <h2>No hay ninguna cuenta asociada a este usuario</h2>

    <%
        }
    %>

    </body>
</html>