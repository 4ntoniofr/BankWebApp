<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Modificar Perfil</title>
    </head>

    <body>
        <h1>Formulario modificar perfil</h1>
        <form:form action="/cliente/guardar" method="post" modelAttribute="persona">
            <form:hidden path="id"></form:hidden>
            <form:hidden path="dni"></form:hidden>
            Nombre: <form:input path="nombre" size="20" minlength="2" maxlength="10" required="requied"></form:input></br>
            Primer Apellido: <form:input path="primerApellido" size="20" minlength="2" maxlength="45" required="required"></form:input></br>
            Segundo Apellido: <form:input path="segundoApellido" size="20" minlength="2" maxlength="45" required="required"></form:input></br>
            Fecha de Nacimiento: <form:input type="date" path="fechaNacimiento" required="required"></form:input></br>
            <form:button>Guardar</form:button>
        </form:form>
    </body>

</html>