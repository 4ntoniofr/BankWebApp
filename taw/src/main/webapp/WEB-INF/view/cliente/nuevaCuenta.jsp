<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Añadir Cuenta</title>
    </head>

    <body>
        <form:form method="post" action="nuevaCuenta" modelAttribute="formulario">
            <form:hidden path="cuentaInterna.id"></form:hidden>
            Moneda: <form:select path="cuentaInterna.moneda" items="${divisas}"/><br />
            Pais: <form:select path="cuentaInterna.pais" items="${paises}"/><br />
            Cantidad: <form:input path="cuentaInterna.cantidad" type="number" min="0" max="999999999999"></form:input></br>
            <form:hidden path="cuentaBancaria.id"></form:hidden>
            <form:button>Guardar</form:button>
        </form:form>
    </body>

</html>