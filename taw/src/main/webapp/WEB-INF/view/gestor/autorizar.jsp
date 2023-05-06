<%/**
* @author Antonio Fernandez Rodriguez
*/%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Autorizacion</title>
</head>

<body>
<form:form action="/gestor/autorizar" method="post" modelAttribute="cuenta">
    <form:hidden path="id"/>
    <form:hidden path="cuentaBancariaByCuentaBancaria.id"/>
    <form:hidden path="clienteByPropietario.id"/>

    IBAN: <form:input path="cuentaBancariaByCuentaBancaria.iban"/><br>
    Moneda:
    <form:select path="monedaByMoneda">
        <form:options items="${monedas}" itemValue="id" itemLabel="moneda"></form:options>
    </form:select><br>
    Pais: <form:input path="pais"/><br>

    <form:button>Guardar</form:button>
</form:form>
</body>

</html>