<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:form action="/empresa/addAsociado" method="post" modelAttribute="registroAsociado">
    <div style="border: 1px solid black">
        <h3>Informacion del asociado</h3>
        <br>
        <div style="border: 1px solid black">
            <h5>Datos personales</h5>
            <form:hidden path="personaAsociado.id" />
            DNI: <form:input path="personaAsociado.dni" maxlength="9" /><br>
            Nombre: <form:input path="personaAsociado.nombre" maxlength="45" /><br>
            Primer Apellido: <form:input path="personaAsociado.primerApellido" maxlength="45" /><br>
            Segundo Apellido: <form:input path="personaAsociado.segundoApellido" maxlength="45" /><br>
            Fecha Nacimiento: <form:input path="personaAsociado.fechaNacimiento" type="date" /><br>
        </div>
        <br>
        <div style="border: 1px solid black">
            <h5>Informacion de usuario</h5>
            <form:hidden path="usuarioAsociado.id" />
            Nombre de usuario: <form:input path="usuarioAsociado.usuario" maxlength="45" /><br>
            Contraseña: <form:input path="usuarioAsociado.password" maxlength="45" /><br>
        </div>
        <br>
        <div style="border: 1px solid black">
            <h5>Direccion del asociado</h5>
            <form:hidden path="clienteAsociado.direccionByDireccion.id" />
            Calle: <form:input path="clienteAsociado.direccionByDireccion.calle" maxlength="100" /><br>
            Numero: <form:input path="clienteAsociado.direccionByDireccion.numero" type="number" /><br>
            Ciudad: <form:input path="clienteAsociado.direccionByDireccion.ciudad" maxlength="45" /><br>
            Codigo postal: <form:input path="clienteAsociado.direccionByDireccion.codigoPostal" maxlength="5" /><br>
            Pais: <form:input path="clienteAsociado.direccionByDireccion.pais" maxlength="45" /><br>
        </div>
        <br>
    </div>
    <form:button>Enviar</form:button>
</form:form>