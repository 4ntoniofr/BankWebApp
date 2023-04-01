<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.RolClienteEntity" %>
<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Cambio Divisas</title>
    </head>

    <body>
<h1>Cambio de Divisas</h1>

<%
    CuentaInternaEntity cuenta = (CuentaInternaEntity) request.getAttribute("cuenta");
    if(cuenta!=null){
%>

    <h2>Saldo actual: <%=cuenta.getCantidad()%></h2>
    <h2>Moneda actual: <%=cuenta.getMoneda()%></h2>

    <form:form method="POST" action="divisas" modelAttribute="cuenta">
        <form:hidden path="id"></form:hidden>
        <form:hidden path="pais"></form:hidden>
        <form:hidden path="cantidad"></form:hidden>
        <form:hidden path="bloqueada"></form:hidden>
        <form:hidden path="cuentaBancariaByCuentaBancaria"></form:hidden>
        <form:hidden path="clienteByPropietario"></form:hidden>
        <form:hidden path="estadoCuentaByEstadoCuenta"></form:hidden>
        <form:select path="moneda" items="${divisas}"/><br />
        <form:button>Realizar Cambio</form:button>
    </form:form>

<%
    } else{

%>

    <h2>No hay ninguna cuenta vinculada a este usuario</h2>

<%
    }
%>
    </body>
</html>