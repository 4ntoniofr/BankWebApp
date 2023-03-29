<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registro de Cliente</title>
</head>
<body>
<h1>Registro de Cliente:</h1>

<form:form action="/cliente/register" method="post" modelAttribute="registroCliente">
    <div style="border: 1px solid black">
        <h2>Información de la Persona:</h2>
            <form:hidden path="persona.id"></form:hidden>
            Dni: <form:input path="persona.dni" size="20" maxlength="20"></form:input></br>
            Nombre: <form:input path="persona.nombre" size="20" maxlength="30"></form:input></br>
            Primer Apellido: <form:input path="persona.primerApellido" size="20" maxlength="30"></form:input></br>
            Segundo Apellido: <form:input path="persona.segundoApellido" size="20" maxlength="30"></form:input></br>
            Fecha de Nacimiento: <form:input type="date" path="persona.fechaNacimiento" size="20" maxlength="30"></form:input></br>
    </div>
        <div style="border: 1px solid black">
            <h2>Dirección de la Persona:</h2>
            <form:hidden path="cliente.id"></form:hidden>
            <form:hidden path="cliente.direccionByDireccion.id"></form:hidden>
            Calle: <form:input path="cliente.direccionByDireccion.calle" size="30" maxlength="30"></form:input></br>
            Numero: <form:input path="cliente.direccionByDireccion.numero"></form:input></br>
            Ciudad: <form:input path="cliente.direccionByDireccion.ciudad"></form:input></br>
            Codigo Postal: <form:input path="cliente.direccionByDireccion.codigoPostal"></form:input></br>
            País: <form:input path="cliente.direccionByDireccion.pais"></form:input></br>
        </div>

        <div style="border: 1px solid black">
            <h2>Informacion de usuario</h2>
            <form:hidden path="usuario.id" />
            Nombre de usuario: <form:input path="usuario.usuario" maxlength="45" /><br>
            Contraseña: <form:input path="usuario.password" maxlength="45" /><br>
        </div>
    <form:button>Registrar</form:button>
</form:form>
</body>
</html>