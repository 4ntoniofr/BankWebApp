<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> personas = (List<Cliente>) request.getAttribute("personas");
    List<Cliente> empresas = (List<Cliente>) request.getAttribute("empresas");
%>

<html>
<head>
    <title>Clientes sospechosos</title>
</head>
<body>
<h1>Lista de clientes sospechosos</h1>

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
        <th></th>
    </tr>

    <%
        for (Cliente persona : personas) {
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
        <td><a href="/gestor/desactivar/<%= persona.getId() %>/sospechosos">Desactivar cuentas</a></td>
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
        <th></th>
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
        <td><a href="/gestor/desactivar?id=<%= empresa.getId() %>&urlto=sospechosos">Desactivar cuentas</a></td>
    </tr>


    <%
        }
    %>

</table>

</body>
</html>