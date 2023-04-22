<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<CuentaInternaEntity> cuentas = (List<CuentaInternaEntity>) request.getAttribute("cuentas");
%>

<html>
<head>
    <title>Cuentas solicitantes</title>
</head>
<body>
<h1>Cuentas solicitantes de desbloqueo</h1>
<table border="1">
    <tr>
        <th>IBAN</th>
        <th>Moneda</th>
        <th>Pais</th>
        <th>Cantidad</th>
        <th></th>
        <th></th>
    </tr>
    <% for (CuentaInternaEntity cuenta : cuentas) { %>
    <tr>
        <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%>
        </td>
        <td><%=cuenta.getMonedaByMoneda().getMoneda()%>
        </td>
        <td><%=cuenta.getPais()%>
        </td>
        <td><%=cuenta.getCantidad()%>
        </td>
        <td><a href="/gestor/operaciones?id=<%=cuenta.getId()%>">Operaciones</a></td>
        <td><a href="/gestor/desbloquear?id=<%=cuenta.getId()%>">Desbloquear</a></td>
    </tr>
    <% } %>
</table>
</body>