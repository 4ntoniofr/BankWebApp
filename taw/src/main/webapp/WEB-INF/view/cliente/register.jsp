<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registro de Cliente</title>
</head>
<body>
<h1>Registro de Cliente:</h1>

<form:form action="/cliente/register" method="post" modelAttribute="cliente">
    <div style="border: 1px solid black">
        <h2>Información de la Persona:</h2>
        <form:hidden path="personaByPersonaId.id"></form:hidden>
        Dni: <form:input path="personaByPersonaId.dni" size="10" minlength="9" maxlength="9"></form:input></br>
        Nombre: <form:input path="personaByPersonaId.nombre" size="20" minlength="2" maxlength="45"></form:input></br>
        Primer Apellido: <form:input path="personaByPersonaId.primerApellido" size="20" minlength="2" maxlength="45"></form:input></br>
        Segundo Apellido: <form:input path="personaByPersonaId.segundoApellido" size="20" minlength="2" maxlength="45"></form:input></br>
        Fecha de Nacimiento: <form:input type="date" path="personaByPersonaId.fechaNacimiento" required="required"></form:input></br>
    </div>
    <div style="border: 1px solid black">
        <h2>Dirección de la Persona:</h2>
        <form:hidden path="id"></form:hidden>
        <form:hidden path="direccionByDireccion.id"></form:hidden>
        Calle: <form:input path="direccionByDireccion.calle" size="40" minlength="1" maxlength="100"></form:input></br>
        Numero: <form:input type="number" min="0" max="9999" path="direccionByDireccion.numero" required="required"></form:input></br>
        Ciudad: <form:input path="direccionByDireccion.ciudad" size="30" minlength="1" maxlength="45"></form:input></br>
        Codigo Postal: <form:input path="direccionByDireccion.codigoPostal" size="30" minlength="5" maxlength="5"></form:input></br>
        País: <form:input path="direccionByDireccion.pais" size="30" minlength="1" maxlength="45"></form:input></br>
    </div>

    <div style="border: 1px solid black">
        <h2>Informacion de usuario</h2>
        <form:hidden path="usuarioByUsuarioId.id" />
        Nombre de usuario: <form:input path="usuarioByUsuarioId.usuario" minlength="4" maxlength="45" /><br>
        Contraseña: <form:input type="password" path="usuarioByUsuarioId.password" minlength="8" maxlength="45" /><br>
    </div>
    <form:button>Registrar</form:button>
</form:form>

</body>
</html>