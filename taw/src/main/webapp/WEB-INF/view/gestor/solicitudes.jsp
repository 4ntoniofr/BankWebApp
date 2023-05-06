<%/**
* @author Antonio Fernandez Rodriguez
*/%>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.CuentaInterna" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<CuentaInterna> cuentas = (List<CuentaInterna>) request.getAttribute("cuentas");
    List<String> monedas = (List<String>) request.getAttribute("monedas");
%>

<html>
<head>
    <title>Cuentas solicitantes</title>
</head>
<body>
<h1>Cuentas solicitantes de desbloqueo</h1>
<a href="/gestor/">Volver a la pagina principal</a>
<%
    if (cuentas.isEmpty()) {
%>
<br><b>No hay cuentas que hayan solicitado el desbloqueo</b>
<%
} else {
%>
<a href="/gestor/">Volver a la pagina principal</a>
<table border="1">
    <tr>
        <th>IBAN</th>
        <th>Moneda</th>
        <th>Pais</th>
        <th>Cantidad</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (int i = 0; i < cuentas.size(); i++) {
            CuentaInterna cuenta = cuentas.get(i);
    %>
    <tr>
        <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%>
        </td>
        <td><%=monedas.get(i)%>
        </td>
        <td><%=cuenta.getPais()%>
        </td>
        <td><%=cuenta.getCantidad()%>
        </td>
        <td><a href="/gestor/operaciones/<%=cuenta.getId()%>">Operaciones</a></td>
        <td><a href="/gestor/desbloquear/<%=cuenta.getId()%>">Desbloquear</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>