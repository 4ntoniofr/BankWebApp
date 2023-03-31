<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Mis Cuentas</title>
    </head>

    <body>
        <h1>Mis cuentas</h1>
    <%
        List<CuentaInternaEntity> cuentas = (List<CuentaInternaEntity>) request.getAttribute("cuentas");
        if(!cuentas.isEmpty()){

            %>

        <table border="1">
            <tr>
                <th>IBAN</th>
                <th>Cantidad</th>
                <th>Moneda</th>
                <th>Pais</th>
                <th>Estado</th>
                <th>Operaciones</th>
                <th>Cambiar Divisas</th>
                <th>Realizar Transacción</th>
                <th>Desbloqueo</th>
            </tr>

        <%

            for(CuentaInternaEntity cuenta : cuentas){
    %>
            <tr>
                <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></td>
                <td><%=cuenta.getCantidad()%></td>
                <td><%=cuenta.getMoneda()%></td>
                <td><%=cuenta.getPais()%></td>
                <td><%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></td>
                <td><a href="operaciones?idCuenta=<%=cuenta.getId()%>">operaciones</a></td>
                <td><a href="divisas?idCuenta=<%=cuenta.getId()%>">cambio divisa</a></td>
                <td><a href="transferencia?idCuenta=<%=cuenta.getCuentaBancariaByCuentaBancaria().getId()%>">realizar transferencia</a></td>
                <td><a href="desbloqueo?idCuenta=<%=cuenta.getId()%>">solicitar desbloqueo</a></td>
            </tr>
    <%
            }
        }else{
    %>
        <h1>Ninguna cuenta asociada a este usuario</h1>
    <%
        }
    %>
    <a href="nuevaCuenta"><h2>Añadir cuenta</h2></a>
    </body>
</html>