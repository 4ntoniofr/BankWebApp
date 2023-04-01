<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Autorizacion</title>
</head>

<body>
<form:form action="/gestor/autorizar" method="post" modelAttribute="cuenta">
    <form:hidden path="id"/>
    <form:hidden path="cuentaBancariaByCuentaBancaria"/>
    <form:hidden path="clienteByPropietario"/>

    IBAN: <form:input path="cuentaBancariaByCuentaBancaria.iban"/><br>
    Moneda: <form:input path="moneda"/><br>
    Pais: <form:input path="pais"/><br>

    <form:button>Guardar</form:button>
</form:form>
</body>

</html>