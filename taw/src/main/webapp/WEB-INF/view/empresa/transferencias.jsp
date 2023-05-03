<%
    /**
     * @author Jose Fco Artacho
     */
%>
<%@ page import="es.taw.grupo25.dto.Transaccion" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Transaccion> transacciones = (List<Transaccion>) request.getAttribute("transacciones");
%>

<html>
<head>
    <title>Transferencias</title>
</head>
<body>
<h1>Transferencias realizadas</h1>

<table border="1">
    <tr>
        <th>CUENTA ORIGEN</th>
        <th>CUENTA DESTINO</th>
        <th>IMPORTE</th>
        <th>MONEDA</th>
        <th>FECHA INSTRUCCIÓN</th>
        <th>FECHA EJECUCIÓN</th>
        <th>SOCIO EJECUTOR</th>
    </tr>

    <%
        for(Transaccion t : transacciones) {
            Persona p = t.getClienteByCliente().getPersonaByPersonaId();
    %>

        <tr>
            <td><%= t.getCuentaBancariaByCuentaOrigen().getIban() %></td>
            <td><%= t.getCuentaBancariaByCuentaDestino().getIban() %></td>
            <td><%= t.getPagosById().getCantidad() %></td>
            <td><%= t.getPagosById().getMoneda() %></td>
            <td><%= t.getFechaInstruccion() %></td>
            <td><%= t.getFechaEjecucion() %></td>
            <td><%= p.getNombre() + " " + p.getPrimerApellido() %></td>
        </tr>

    <%
        }
    %>
</table><br>
<a href="/empresa/">Volver al menú de empresas</a>
</body>
</html>