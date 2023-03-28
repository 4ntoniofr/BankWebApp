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
    CuentaInternaEntity cuenta = (CuentaInternaEntity) request.getAttribute("clientes");
    if(cuenta!=null){
%>

    <h2>Saldo actual: <%=cuenta.getCantidad()%></h2>
    <h2>Moneda actual: <%=cuenta.getMoneda()%></h2>

    <form:form method="POST" action="cliente/guardarDivisas">
        <form:select path="currency">
            <form:option value="euro">Euro</form:option>
            <form:option value="dollar">Dollar</form:option>
            <form:option value="peso">Peso</form:option>
        </form:select>
        <input type="submit" value="Submit">
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