<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Transaccion" %>
<%@ page import="es.taw.grupo25.dto.Pago" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author Jose Bravo Marqués
     */
%>

<html>
<head>
    <title>Cajero: Lista de Operaciones</title>
</head>

<body>

<%
    String iban = (String) request.getAttribute("iban");
%>

<h1>Mis Operaciones</h1>

<form:form action="operaciones" method="post" modelAttribute="filtro">
    Buscar por:</br>
    Fecha Instruccion: <form:input type="date" path="fechaInstruccion"></form:input>
    Fecha Ejecucion: <form:input type="date" path="fechaEjecucion"></form:input>
    IBAN Destino <form:input path="iban"></form:input></br>
    Ordenar por:</br>
    Columna: <form:select path="orden">
    <form:option value="instruccion" label="Fecha de instruccion"/>
    <form:option value="ejecucion" label="Fecha de ejecucion"/>
</form:select>
    En orden:
    <form:select path="ascdesc">
        <form:option value="ascendente" label="Ascendente"/>
        <form:option value="descendente" label="Descendente"/>
    </form:select>
    </br>
    <form:button>Filtrar y Ordenar</form:button>
</form:form>

<%
    List<Pago> pagos = (List<Pago>) request.getAttribute("pagos");
    if(!pagos.isEmpty()){
%>

<table border="1">
    <tr>
        <th>Tipo</th>
        <th>Fecha Instrucción</th>
        <th>Fecha Ejecución</th>
        <th>IBAN Origen</th>
        <th>IBAN Destino</th>
        <th>Cantidad</th>
    </tr>

    <%
        for(Pago pago : pagos){
    %>

    <tr>
        <td><%=pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaOrigen().getIban().equals(iban)?"PAGO":"INGRESO"%></td>
        <td><%=pago.getTransaccionByTransaccion().getFechaInstruccion()%></td>
        <td><%=pago.getTransaccionByTransaccion().getFechaEjecucion()%></td>
        <td><%=pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaOrigen().getIban()%></td>
        <td><%=pago.getTransaccionByTransaccion().getCuentaBancariaByCuentaDestino().getIban()%></td>
        <td><%=pago.getCantidad()%></td>

    </tr>

    <%
        }
    %>

</table>
<%
} else{
%>
<h2>No hay ninguna operación</h2>
<%
    }
%>

<a href="/cliente/cuentas">Volver a mis cuentas</a>


</body>
</html>