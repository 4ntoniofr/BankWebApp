<%@ page import="es.taw.grupo25.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.EstadoClienteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%
    List<ClienteEntity> personas = (List<ClienteEntity>) request.getAttribute("personas");
    List<ClienteEntity> empresas = (List<ClienteEntity>) request.getAttribute("empresas");
    List<EstadoClienteEntity> estados = (List<EstadoClienteEntity>) request.getAttribute("estados");
%>

<html>
<head>
    <title>Lista clientes</title>
</head>
<body>
<h1>Lista de clientes del sistema</h1>

<h2>Buscar por:</h2>

<form:form action="/gestor/filtrarClientes" method="post" modelAttribute="filtro">
    Contiene: <form:input path="texto"/>
    Estado:
    <form:select path="estadoCliente"  size="6" >
        <form:option value="" label="No filter" />
        <form:options items="${estados}" itemLabel="estado" itemValue="estado" />
    </form:select>
    <form:button>Filtrar</form:button>
</form:form>

<h2>Clientes Individuales</h2>
<table border="1">
    <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Fecha Nacimiento</th>
        <th>Rol</th>
        <th>Estado</th>
        <th>Direccion</th>
    </tr>

    <%
        for (ClienteEntity persona : personas) {
    %>

    <tr>
        <td><%= persona.getPersonaByPersonaId().getDni() %>
        </td>
        <td><%= persona.getPersonaByPersonaId().getNombre() %>
        </td>
        <td><%= persona.getPersonaByPersonaId().getPrimerApellido() + " " +
                persona.getPersonaByPersonaId().getSegundoApellido()%>
        </td>
        <td><%= persona.getPersonaByPersonaId().getFechaNacimiento().toString() %>
        </td>
        <td><%= persona.getRolClienteByRolClienteId().getRol() %>
        </td>
        <td><%= persona.getEstadoClienteByEstadoCliente().getEstado() %>
        </td>
        <td><%= persona.getDireccionByDireccion().getCalle() + " " + persona.getDireccionByDireccion().getNumero() + ", " +
                persona.getDireccionByDireccion().getCiudad() + " (" + persona.getDireccionByDireccion().getPais() + ")" %>
        </td>
    </tr>


    <%
        }
    %>

</table>
<h2>Empresas</h2>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Estado</th>
        <th>Direccion</th>
    </tr>

    <%
        for (ClienteEntity empresa : empresas) {
    %>

    <tr>
        <td><%= empresa.getEmpresasById().getNombre() %>
        </td>
        <td><%= empresa.getEstadoClienteByEstadoCliente().getEstado() %>
        </td>
        <td><%= empresa.getDireccionByDireccion().getCalle() + " " + empresa.getDireccionByDireccion().getNumero() + ", " +
                empresa.getDireccionByDireccion().getCiudad() + " (" + empresa.getDireccionByDireccion().getPais() + ")" %>
        </td>
    </tr>


    <%
        }
    %>

</table>
</body>
</html>