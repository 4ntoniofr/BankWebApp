<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    /**
     * @author Jose Fco Artacho
     */
%>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Cliente> socios = (List<Cliente>) request.getAttribute("socios");
    Empresa empresa = (Empresa) request.getAttribute("empresa");

    List<RolCliente> roles = (List<RolCliente>) request.getAttribute("roles");
    List<EstadoCliente> estados = (List<EstadoCliente>) request.getAttribute("estados");
%>

<html>
<h1>Socios/Autorizados de la empresa <%= empresa.getNombre() %></h1>

<form:form modelAttribute="filtro" method="post" action="/empresa/sociosEmpresa/filtro">
    Estado: <form:select path="estado">
                <form:option value="" label="---"/>
                <form:options items="${estados}" itemLabel="estado" itemValue="estado" />
            </form:select>
    Rol: <form:select path="rol">
            <form:option value="" label="---" />
            <form:options items="${roles}" itemLabel="rol" itemValue="rol" />
         </form:select>
    <form:button>Filtrar</form:button>
</form:form>

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
        <td><%= socio.getAutorizador() ? "SÍ" : "NO" %></td>
        <td><%= socio.getRolClienteByRolClienteId().getRol() %></td>
        <td><%= socio.getEstadoClienteByEstadoCliente().getEstado() %></td>
        <td><a href="/empresa/operaciones?idCliente=<%= socio.getId() %>">Visualizar operaciones</a></td>
        <td><a href="/empresa/bloquearSocio?idCliente=<%= socio.getId() %>">Bloquear socio</a></td>
    </tr>


    <%
        }
    %>
</table border="1"><br>
<a href="/empresa/">Volver al menú de empresas</a>
</html>