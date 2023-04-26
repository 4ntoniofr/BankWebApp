<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<%@ page import="es.taw.grupo25.dto.Empresa" %>
<%@ page import="es.taw.grupo25.dto.Persona" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> socios = (List<Cliente>) request.getAttribute("socios");
    Empresa empresa = (Empresa) request.getAttribute("empresa");
%>

<html>
<h1>Socios/Autorizados de la empresa <%= empresa.getNombre() %></h1>

<table border="1">
    <tr>
        <th>DNI</th>
        <th>NOMBRE</th>
        <th>PRIMER APELLIDO</th>
        <th>SEGUNDO APELLIDO</th>
        <th>FECHA NACIMIENTO</th>
        <th>FECHA REGISTRO</th>
        <th>AUTORIZADO</th>
        <th>ROL</th>
        <th>ESTADO</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (Cliente socio: socios) {
            Persona persona = socio.getPersonaByPersonaId();
    %>
    <tr>
        <td><%= persona.getDni() %></td>
        <td><%= persona.getNombre() %></td>
        <td><%= persona.getPrimerApellido() %></td>
        <td><%= persona.getSegundoApellido() %></td>
        <td><%= persona.getFechaNacimiento() %></td>
        <td><%= socio.getFechaInicio() %></td>
        <td><%= socio.getEmpleadoByAutorizador() != null %></td>
        <td><%= socio.getRolClienteByRolClienteId().getRol() %></td>
        <td><%= socio.getEstadoClienteByEstadoCliente().getEstado() %></td>
        <td><a href="/empresa/operaciones?idCliente=<%= socio.getId() %>">Visualizar operaciones</a></td>
        <td><a href="/empresa/bloquearSocio?idCliente=<%= socio.getId() %>">Bloquear socio</a></td>
    </tr>


    <%
        }
    %>
</table border="1">
</html>