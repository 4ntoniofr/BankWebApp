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

    <%
        String error = (String) request.getAttribute("error");
        if(error!=null){
    %>
    <h2><%=error%></h2>
    <%
    }else{
    %>

    <%
        String errorTransferencia = (String) request.getAttribute("errorTransferencia");
        if(errorTransferencia!=null){
    %>
    <p style="color:red;"><%=errorTransferencia%></p>
    <%
        }
    %>

    <form:form method="post" action="transferencia?idCuenta=${param.idCuenta}" modelAttribute="pago">
        <form:hidden path="transaccionByTransaccion.id"></form:hidden>
        <form:hidden path="id"></form:hidden>
        <form:hidden path="transaccionByTransaccion.cuentaBancariaByCuentaDestino.id"></form:hidden>
        Iban Destino: <form:input path="transaccionByTransaccion.cuentaBancariaByCuentaDestino.iban" size="50" maxlength="45"></form:input></br>
        Moneda Transferencia: Moneda: <form:select path="moneda" items="${divisas}"/><br />
        Cantidad A Enviar: <form:input path="cantidad" type="number" min="0" max="999999999999"></form:input></br>
        <form:button>Realizar Transferencia</form:button>
    </form:form>

<%
    }
%>
</body>

</html>