<%/**
* @author Antonio Fernandez Rodriguez
*/%>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> personas = (List<Cliente>) request.getAttribute("personas");
    List<Cliente> empresas = (List<Cliente>) request.getAttribute("empresas");
%>

<html>
<head>
    <title>Clientes pendientes</title>
</head>
<body>

<h1>Listado de clientes pendientes por autorizar</h1>
<a href="/gestor/">Volver a la pagina principal</a>
<h2>Clientes Individuales</h2>
<%
    if (personas.isEmpty()) {
%>
<b>No hay clientes individuales pendientes por autorizar</b>
<%
} else {
%>
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
                (persona.getPersonaByPersonaId().getSegundoApellido() == null ? "" : persona.getPersonaByPersonaId().getSegundoApellido())%>
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
        <td><a href="/gestor/autorizar/<%= persona.getId() %>"> Autorizar</a></td>
        <td><a href="/gestor/rechazar/<%= persona.getId() %>"> Rechazar</a></td>
    </tr>


    <%
            }
        }
    %>

</table>
<h2>Empresas</h2>
<%
    if (empresas.isEmpty()) {
%>
<b>No hay empresas pendientes por autorizar</b>
<%
} else {
%>
<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Estado</th>
        <th>Direccion</th>
        <th></th>
        <th></th>
    </tr>

    <%
        for (Cliente empresa : empresas) {
    %>

    <tr>
        <td><%= empresa.getEmpresasById().getNombre() %>
        </td>
        <td><%= empresa.getEstadoClienteByEstadoCliente().getEstado() %>
        </td>
        <td><%= empresa.getDireccionByDireccion().getCalle() + " " + empresa.getDireccionByDireccion().getNumero() + ", " +
                empresa.getDireccionByDireccion().getCiudad() + " (" + empresa.getDireccionByDireccion().getPais() + ")" %>
        </td>
        <td><a href="/gestor/autorizar/<%= empresa.getId() %>"> Autorizar</a></td>
        <td><a href="/gestor/rechazar/<%= empresa.getId() %>"> Rechazar</a></td>
    </tr>


    <%
            }
        }
    %>

</table>

</body>
</html>