<%@ page import="es.taw.grupo25.entity.ClienteEntity" %>
<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    List<CuentaInternaEntity> cuentas = cliente.getCuentaInternasById();
%>

<html>
<head>
    <title>Detalles cliente</title>
</head>
<body>
<h1>Datos de <%=cliente.getEmpresasById().getNombre()%>
</h1>

<div style="border: 2px solid black">
    <h2>Datos de la empresa</h2>
    <ul>
        <li>Nombre: <%=cliente.getEmpresasById().getNombre()%>
        </li>
        <li>
            Direccion: <%= cliente.getDireccionByDireccion().getCalle() + " " + cliente.getDireccionByDireccion().getNumero() + ", " + cliente.getDireccionByDireccion().getCiudad() + " (" + cliente.getDireccionByDireccion().getPais() + ")" %>
        </li>
    </ul>
</div>

<div style="border: 2px solid black">
    <h2>Datos bancarios</h2>
    <ul>
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
                for (CuentaInternaEntity cuenta : cuentas) {
            %>
            <tr>
                <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></td>
                <td><%=cuenta.getMoneda()%></td>
                <td><%=cuenta.getPais()%></td>
                <td><%=cuenta.getCantidad()%></td>
                <td><%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></td>
                <td><a href="/gestor/operaciones?id=<%=cuenta.getId()%>">Operaciones</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </ul>
</div>
</body>
</html>