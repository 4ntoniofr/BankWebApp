<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<%@ page import="es.taw.grupo25.dto.CuentaInterna" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<CuentaInterna> cuentas = cliente.getCuentaInternasById();
%>

<html>
<head>
    <title>Detalles cliente</title>
</head>
<body>
<h1>Datos de <%=cliente.getPersonaByPersonaId().getNombre()%>
</h1>

<div style="border: 2px solid black">
    <h2>Datos personales</h2>
    <ul>
        <li>Nombre: <%=cliente.getPersonaByPersonaId().getNombre()%>
        </li>
        <li>
            Apellidos: <%=cliente.getPersonaByPersonaId().getPrimerApellido() + " " + cliente.getPersonaByPersonaId().getSegundoApellido()%>
        </li>
        <li>Fecha de nacimiento: <%= cliente.getPersonaByPersonaId().getFechaNacimiento().toString() %>
        </li>
        <li>
            Direccion: <%= cliente.getDireccionByDireccion().getCalle() + " " + cliente.getDireccionByDireccion().getNumero() + ", " + cliente.getDireccionByDireccion().getCiudad() + " (" + cliente.getDireccionByDireccion().getPais() + ")" %>
        </li>
    </ul>
</div>

<div style="border: 2px solid black">
    <h2>Datos bancarios</h2>
    <ul>
        <li>Rol: <%=cliente.getRolClienteByRolClienteId().getRol()%>
        </li>
        <li>
            Estado: <%=cliente.getEstadoClienteByEstadoCliente().getEstado()%>
        </li>
        <table border="1">
            <tr>
                <th>IBAN</th>
                <th>Moneda</th>
                <th>Pais</th>
                <th>Cantidad</th>
                <th>Estado</th>
                <th></th>
            </tr>

            <%
                for (CuentaInterna cuenta : cuentas) {
            %>
            <tr>
                <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></td>
                <td><%=cuenta.getMonedaByMoneda().getMoneda()%></td>
                <td><%=cuenta.getPais()%></td>
                <td><%=cuenta.getCantidad()%></td>
                <td><%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></td>
                <td><a href="/gestor/operaciones/<%=cuenta.getId()%>">Operaciones</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </ul>
</div>
</body>
</html>