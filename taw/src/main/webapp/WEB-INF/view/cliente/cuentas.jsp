<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.CuentaInterna" %>
<%@ page import="es.taw.grupo25.dto.Moneda" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Mis Cuentas</title>
    </head>

    <body>
        <h2>Mis cuentas</h2>

    <%
        List<CuentaInterna> cuentas = (List<CuentaInterna>) request.getAttribute("cuentas");
        List<CuentaInterna> cuentas_empresa = (List<CuentaInterna>) request.getAttribute("cuentas_empresa");
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
                <td><a href="operaciones?idCuenta=<%=cuenta.getId()%>">operaciones</a></td>
                <%
                    if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                %>
                <td><a href="divisas?idCuenta=<%=cuenta.getId()%>">cambio divisa</a></td>
                <td><a href="transferencia?idCuenta=<%=cuenta.getCuentaBancariaByCuentaBancaria().getId()%>">realizar transferencia</a></td>
                <td>solicitar desbloqueo</td>
                <%}else{%>
                <td>cambio divisa</td>
                <td>realizar transferencia</td>
                <td><a href="desbloqueo?idCuenta=<%=cuenta.getId()%>">solicitar desbloqueo</a></td>
                <%}%>
            </tr>
    <%
            }
        }else{
    %>
        <h1>Ninguna cuenta asociada a este usuario</h1>
    <%
        }
        if(cuentas_empresa!=null){
    %>

            <h2>Cuentas de empresa</h2>
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

            for(CuentaInterna cuenta : cuentas_empresa){
                Moneda moneda = new Moneda();
                moneda.setId(cuenta.getMonedaByMoneda());
    %>
                <tr>
                    <td><%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></td>
                    <td><%=cuenta.getCantidad()%></td>
                    <td><%=monedas.get(monedas.indexOf(moneda)).getMoneda()%></td>
                    <td><%=cuenta.getPais()%></td>
                    <td><%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></td>
                    <td><a href="operaciones?idCuenta=<%=cuenta.getId()%>">operaciones</a></td>
                    <%
                        if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("ACTIVA")){
                    %>
                    <td><a href="divisas?idCuenta=<%=cuenta.getId()%>">cambio divisa</a></td>
                    <td><a href="transferencia?idCuenta=<%=cuenta.getCuentaBancariaByCuentaBancaria().getId()%>">realizar transferencia</a></td>
                    <td>solicitar desbloqueo</td>
                    <%}else{%>
                    <td>cambio divisa</td>
                    <td>realizar transferencia</td>
                    <td><a href="desbloqueo?idCuenta=<%=cuenta.getId()%>">solicitar desbloqueo</a></td>
                    <%}%>
                </tr>


    <%
         }}
    %>
    <a href="nuevaCuenta"><h2>Añadir cuenta (Para Pruebas)</h2></a>
    <a href="/cliente">Volver al menú principal del cliente.</a>
    </body>
</html>