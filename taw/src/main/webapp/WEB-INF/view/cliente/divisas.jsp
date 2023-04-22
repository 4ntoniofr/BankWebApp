<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo25.dto.CuentaInterna" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <form:hidden path="bloqueada"></form:hidden>
        <form:hidden path="cuentaBancariaByCuentaBancaria.iban"></form:hidden>
        <form:hidden path="clienteByPropietario.id"></form:hidden>
        <form:hidden path="estadoCuentaByEstadoCuenta.id"></form:hidden>
        <form:select path="monedaByMoneda" items="${monedas}" itemValue="id" itemLabel="moneda"/><br />
        <form:button>Realizar Cambio</form:button>
    </form:form>

<%
    } else{

%>

    <h2>No hay ninguna cuenta vinculada a este usuario</h2>

<%
        }
    }
%>
    </body>
</html>