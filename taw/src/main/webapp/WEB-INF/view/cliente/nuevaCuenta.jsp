<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Moneda" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

 Fichero hecho por:
 Valentín García Rosas

--%>

<html>
    <head>
        <title>Añadir Cuenta</title>
    </head>

    <%
        List<Moneda> monedas = (List<Moneda>) request.getAttribute("monedas");
    %>

    <body>
        <form:form method="post" action="nuevaCuenta" modelAttribute="cuenta">
            <form:hidden path="id"></form:hidden>
            Moneda: <form:select path="monedaByMoneda" items="<%=monedas%>" itemValue="id" itemLabel="moneda"></form:select><br />
            Pais: <form:select path="pais" items="${paises}"/><br />
            Cantidad: <form:input path="cantidad" type="number" min="0" max="999999999999"></form:input></br>
            <form:hidden path="cuentaBancariaByCuentaBancaria.id"></form:hidden>
            <form:button>Guardar</form:button>
        </form:form>
    </body>

</html>