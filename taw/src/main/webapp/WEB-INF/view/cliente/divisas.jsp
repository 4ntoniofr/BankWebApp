<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo25.dto.CuentaInterna" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.CambioDivisa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

 Fichero hecho por:
 Valentín García Rosas

--%>

<html>
    <head>
        <title>Cambio Divisas</title>
    </head>

    <body>
<h1>Cambio de Divisas</h1>

<%
    String error = (String) request.getAttribute("error");
    if(error!=null){
%>
    <h2><%=error%></h2>
<%
    }else{
%>

<%
    CuentaInterna cuenta = (CuentaInterna) request.getAttribute("cuenta");
    if(cuenta!=null){
%>

    <h2>Saldo actual: <%=cuenta.getCantidad()%></h2>

    <form:form method="POST" action="divisas" modelAttribute="cuenta">
        <form:hidden path="id"></form:hidden>
        <form:hidden path="pais"></form:hidden>
        <form:hidden path="cantidad"></form:hidden>
        <form:hidden path="cuentaBancariaByCuentaBancaria.iban"></form:hidden>
        <form:hidden path="clienteByPropietario.id"></form:hidden>
        <form:hidden path="estadoCuentaByEstadoCuenta.id"></form:hidden>
        <form:select path="monedaByMoneda" items="${monedas}" itemValue="id" itemLabel="moneda"/><br />
        <form:button>Realizar Cambio</form:button>
    </form:form>

    <h2>Antiguos cambios de divisa</h2>

    <table border="1">
        <tr>
            <th>Fecha Instrucción</th>
            <th>Fecha Ejecución</th>
            <th>Moneda Anterior</th>
            <th>Moneda Posterior</th>
        </tr>

    <%
        List<CambioDivisa> cambioDivisas = (List<CambioDivisa>) request.getAttribute("cambioDivisas");
        for(CambioDivisa cambio : cambioDivisas){
    %>

        <tr>
            <td><%=cambio.getTransaccionByTransaccion().getFechaInstruccion()%></td>
            <td><%=cambio.getTransaccionByTransaccion().getFechaEjecucion()%></td>
            <td><%=cambio.getMonedaByMonedaCompra().getMoneda()%></td>
            <td><%=cambio.getMonedaByMonedaVenta().getMoneda()%></td>
        </tr>

    <%
        }
    %>

    </table>
<%
    } else{

%>

    <h2>No existe la cuenta</h2>

<%
        }
    }
%>
    </body>
</html>