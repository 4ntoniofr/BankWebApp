<%@ page import="es.taw.grupo25.entity.CuentaBancariaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Transferencia</title>
</head>

<body>
    <h1>Transferencias bancarias</h1>
    <h2>Realizar transferencia desde Cuenta id: ${param.idCuenta}</h2>

    <form:form method="post" action="transferencia?idCuenta=${param.idCuenta}" modelAttribute="transferenciapago">
        <form:hidden path="transaccion.id"></form:hidden>
        <form:hidden path="pago.id"></form:hidden>
        Iban Destino: <form:input path="cuentaDestino" size="50" maxlength="45"></form:input></br>
        Moneda Transferencia: Moneda: <form:select path="pago.moneda" items="${divisas}"/><br />
        Cantidad A Enviar: <form:input path="pago.cantidad" type="number" min="0" max="999999999999"></form:input></br>
        <form:button>Realizar Transferencia</form:button>
    </form:form>

</body>

</html>