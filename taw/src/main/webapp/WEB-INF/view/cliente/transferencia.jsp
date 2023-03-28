<%@ page import="es.taw.grupo25.entity.CuentaBancariaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Transferencia</title>
</head>

<body>
    <h1>Transferencias bancarias</h1>
    <%
        CuentaBancariaEntity cuenta_destino = (CuentaBancariaEntity) request.getAttribute("cuenta_destino");
        if(cuenta_destino==null){
    %>

        <form method="post" action="/transaccionCuenta">
            Cantidad:<input type="number" id="cantidad"></br>
            Iban cuenta destino: <input type="text" id="iban_destino"></br>
            <input type="Submit" value="Submit">
        </form>

    <%
        }
    %>


</body>

</html>