<%@ page import="es.taw.grupo25.dto.Transaccion" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<%@ page import="es.taw.grupo25.dto.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Transaccion> transacciones = (List<Transaccion>) request.getAttribute("transacciones");
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    Persona p = cliente.getPersonaByPersonaId();
%>

<html>
<head>
    <title>Transferencias</title>
</head>
<body>
<h1>Transferencias realizadas por <%= p.getNombre() %> <%= p.getPrimerApellido() %></h1>

<table border="1">
    <tr>
        <th>CUENTA ORIGEN</th>
        <th>CUENTA DESTINO</th>
        <th>IMPORTE</th>
        <th>MONEDA</th>
        <th>FECHA INSTRUCCIÓN</th>
        <th>FECHA EJECUCIÓN</th>
    </tr>

    <%
        for(Transaccion t : transacciones) {
    %>

        <tr>
            <td><%= t.getCuentaBancariaByCuentaOrigen().getIban() %></td>
            <td><%= t.getCuentaBancariaByCuentaDestino().getIban() %></td>
            <td><%= t.getPagosById().getCantidad() %></td>
            <td><%= t.getPagosById().getMoneda() %></td>
            <td><%= t.getFechaInstruccion() %></td>
            <td><%= t.getFechaEjecucion() %></td>
        </tr>

    <%
        }
    %>
</table>
</body>
</html>