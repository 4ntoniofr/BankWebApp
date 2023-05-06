<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.CuentaInterna" %>
<%@ page import="es.taw.grupo25.dto.Moneda" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    /**
     * @author Jose Bravo Marqués
     */
%>

<html>
<head>
    <title>Cajero: Menu Principal</title>
</head>

<body>
<a href="/datos"><h2>Cambiar datos personales</h2></a>
<a href="/">Volver al menú principal.</a>
<h2>Mis cuentas</h2>

<%
    List<CuentaInterna> cuentas = (List<CuentaInterna>) request.getAttribute("cuentas");
    List<Moneda> monedas = (List<Moneda>) request.getAttribute("monedas");
    if(!cuentas.isEmpty()){
%>

<table border="1">
    <tr>
        <th>IBAN</th>
        <th>Cantidad</th>
        <th>Moneda</th>
        <th>Pais</th>
        <th>Estado</th>
        <th>Operaciones</th>
        <th>Cambiar Divisas</th>
        <th>Realizar Transacción</th>
        <th>Desbloqueo</th>
    </tr>

        <%

            for(CuentaInterna cuenta : cuentas){
                Moneda moneda = new Moneda();
                moneda.setId(cuenta.getMonedaByMoneda());
    %>
    <tr>
        <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></td>
        <td><%=cuenta.getCantidad()%></td>
        <td><%=monedas.get(monedas.indexOf(moneda)).getMoneda()%></td>
        <td><%=cuenta.getPais()%></td>
        <td><%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></td>
        <td><a href="operacionesLista?idCuenta=<%=cuenta.getId()%>">operaciones</a></td>
        <%
            if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
        %>
        <td><a href="cambio?idCuenta=<%=cuenta.getId()%>">cambio divisa</a></td>
        <td><a href="transferencias?idCuenta=<%=cuenta.getCuentaBancariaByCuentaBancaria().getId()%>">realizar transferencia</a></td>
        <td>solicitar desbloqueo</td>
        <%}else{%>
        <td>cambio divisa</td>
        <td>realizar transferencia</td>
        <td><a href="desbloquear?idCuenta=<%=cuenta.getId()%>">solicitar desbloqueo</a></td>
        <%}%>
    </tr>
        <%
            }
        }else{
    %>
    <h1>Ninguna cuenta asociada a este usuario</h1>
        <%
        }
    %>
</body>
</html>
