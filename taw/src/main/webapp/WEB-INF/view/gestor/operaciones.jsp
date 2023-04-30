<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.CuentaBancaria" %>
<%@ page import="es.taw.grupo25.dto.Transaccion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%
    CuentaBancaria cuentaBancaria = (CuentaBancaria) request.getAttribute("cuenta");
    List<Transaccion> transacciones = (List<Transaccion>) request.getAttribute("lista");
%>

<html>
<head>
    <title>Operaciones</title>
</head>
<body>
<h1>Operaciones bancarias de <%=cuentaBancaria.getIban()%>
</h1>
<a href="/gestor/clientes">Volver a la lista de clientes del sistema</a>

<form:form action="/gestor/operakciones" method="post" modelAttribute="filtro">
    <h2>Filtro</h2>
    <form:hidden path="idCuenta"/>
    <div>
        <label style="margin-right: 30px"> IBAN: <form:input path="iban"/> </label>
        <label style="margin-right: 30px"> Fecha Instruccion: <form:input type="date"
                                                                          path="fechaInstruccion"></form:input></label>
        <label style="margin-right: 30px"> Fecha Ejecucion: <form:input type="date"
                                                                        path="fechaEjecucion"></form:input></label>
        <label style="margin-right: 30px">Ordenar por:
            <form:select path="orden">
                <form:option value="instruccion" label="Fecha de instruccion"/>
                <form:option value="ejecucion" label="Fecha de ejecucion"/>
            </form:select>
        </label>
    </div>
    <hr>
    <form:button>Filtrar y ordenar</form:button>
</form:form>

<%
    if (transacciones.isEmpty()) {
%>
<b>No se ha realizado ninguna transaccion bancaria</b>
<%
} else {
%>

<table border="1">
    <tr>
        <th>IBAN origen</th>
        <th>IBAN destino</th>
        <th>Cantidad</th>
        <th>Fecha instruccion</th>
        <th>Fecha ejecucion</th>
        <th>Cambio divisa</th>
    </tr>

    <%
        for (Transaccion transaccion : transacciones) {
    %>
    <tr>
        <td><%=transaccion.getCuentaBancariaByCuentaOrigen().getIban()%>
        </td>
        <td><%=transaccion.getCuentaBancariaByCuentaDestino().getIban()%>
        </td>
        <td><%=transaccion.getPagosById() == null ? "Sin pago" : transaccion.getPagosById().getCantidad()%>
        </td>
        <td><%=transaccion.getFechaInstruccion().toString()%>
        </td>
        <td><%=transaccion.getFechaEjecucion().toString()%>
        </td>
        <td><%=transaccion.getCambioDivisa() == null ? "Sin cambio de divisa" : transaccion.getCambioDivisa().getMonedaByMonedaVenta().getMoneda() + " => " + transaccion.getCambioDivisa().getMonedaByMonedaCompra().getMoneda()%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>