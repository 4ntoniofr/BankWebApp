<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.RolClienteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Modificar Perfil</title>
    </head>

    <body>
        <h1>Formulario modificar perfil</h1>
        <form:form action="/cliente/guardar" method="post" modelAttribute="persona">
            NIF: <form:input path="dni" size="20" maxlength="9" /></br>
            Nombre <form:input path="nombre" size="30" maxlength="45"/></br>
            Primer Apellido: <form:input path="primerApellido" size="30" maxlength="45"/></br>
            Segundo Apellido <form:input path="segundoApellido" size="30" maxlength="45"/></br>
            Fecha de Nacimiento: <form:input path="fechaNacimiento" size="30" maxlength="45"/></br>
            <form:button>Guardar</form:button>
        </form:form>
    </body>

</html>