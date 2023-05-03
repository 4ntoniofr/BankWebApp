<%
    /**
     * @author Jose Fco Artacho
     */
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<h1>Registro de empresas</h1>

<form:form action="/empresa/registrar" method="post" modelAttribute="registroEmpresa">
    <div style="border: 1px solid black">
        <h3>Informacion de la empresa</h3>
        <form:hidden path="empresa.id" />
        Nombre de la empresa: <form:input path="empresa.nombre" maxlength="100" /><br>
        <br>
        <form:hidden path="usuarioEmpresa.id" />
        Nombre de usuario: <form:input path="usuarioEmpresa.usuario" maxlength="45" /><br>
        Contraseña: <form:input path="usuarioEmpresa.password" maxlength="45" /><br>
        <br>
        <div style="border: 1px solid black">
            <h5>Direccion de la empresa</h5>
            <form:hidden path="clienteEmpresa.id" />
            <form:hidden path="clienteEmpresa.direccionByDireccion.id" />
            Calle: <form:input path="clienteEmpresa.direccionByDireccion.calle" maxlength="100" /><br>
            Numero: <form:input path="clienteEmpresa.direccionByDireccion.numero" type="number" /><br>
            Ciudad: <form:input path="clienteEmpresa.direccionByDireccion.ciudad" maxlength="45" /><br>
            Codigo postal: <form:input path="clienteEmpresa.direccionByDireccion.codigoPostal" maxlength="5" /><br>
            Pais: <form:input path="clienteEmpresa.direccionByDireccion.pais" maxlength="45" /><br>
        </div>
        <br>
    </div>
    <br>
    <div style="border: 1px solid black">
        <h3>Informacion del asociado</h3>
        <br>
        <div style="border: 1px solid black">
            <h5>Datos personales</h5>
            <form:hidden path="asociadoEmpresa.personaAsociado.id" />
            DNI: <form:input path="asociadoEmpresa.personaAsociado.dni" maxlength="9" /><br>
            Nombre: <form:input path="asociadoEmpresa.personaAsociado.nombre" maxlength="45" /><br>
            Primer Apellido: <form:input path="asociadoEmpresa.personaAsociado.primerApellido" maxlength="45" /><br>
            Segundo Apellido: <form:input path="asociadoEmpresa.personaAsociado.segundoApellido" maxlength="45" /><br>
            Fecha Nacimiento: <form:input path="asociadoEmpresa.personaAsociado.fechaNacimiento" type="date" /><br>
        </div>
        <br>
        <div style="border: 1px solid black">
            <h5>Informacion de usuario</h5>
            <form:hidden path="asociadoEmpresa.usuarioAsociado.id" />
            Nombre de usuario: <form:input path="asociadoEmpresa.usuarioAsociado.usuario" maxlength="45" /><br>
            Contraseña: <form:input path="asociadoEmpresa.usuarioAsociado.password" maxlength="45" /><br>
        </div>
        <br>
        <div style="border: 1px solid black">
            <h5>Direccion del asociado</h5>
            <form:hidden path="asociadoEmpresa.clienteAsociado.direccionByDireccion.id" />
            Calle: <form:input path="asociadoEmpresa.clienteAsociado.direccionByDireccion.calle" maxlength="100" /><br>
            Numero: <form:input path="asociadoEmpresa.clienteAsociado.direccionByDireccion.numero" type="number" /><br>
            Ciudad: <form:input path="asociadoEmpresa.clienteAsociado.direccionByDireccion.ciudad" maxlength="45" /><br>
            Codigo postal: <form:input path="asociadoEmpresa.clienteAsociado.direccionByDireccion.codigoPostal" maxlength="5" /><br>
            Pais: <form:input path="asociadoEmpresa.clienteAsociado.direccionByDireccion.pais" maxlength="45" /><br>
        </div>
        <br>
    </div>

    <form:button>Guardar</form:button>
</form:form><br>
<a href="/empresa/">Cancelar y volver al menú de empresas</a>
</html>