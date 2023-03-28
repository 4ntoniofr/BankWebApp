<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.RolClienteEntity" %>
<%@ page import="es.taw.grupo25.entity.CuentaBancariaEntity" %>
<%@ page import="es.taw.grupo25.entity.TransaccionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Mis Operaciones</title>
    </head>

    <body>


    <h1>Mis Operaciones</h1>

<%
    List<TransaccionEntity> transacciones = (List<TransaccionEntity>) request.getAttribute("transacciones");
    if(transacciones != null){
%>

    <table border="1">
        <tr>
            <th>Id</th>
            <th>Fecha Instrucción</th>
            <th>Fecha Ejecución</th>
            <th>Cuenta Origen</th>
            <th>Cuenta Destino</th>
        </tr>

<%
        for(TransaccionEntity tran: transacciones){
%>

        <tr>
            <td><%=tran.getId()%></td>
            <td><%=tran.getFechaInstruccion()%></td>
            <td><%=tran.getFechaEjecucion()%></td>
            <td><%=tran.getCuentaBancariaByCuentaOrigen()%></td>
            <td><%=tran.getCuentaBancariaByCuentaDestino()%></td>
        </tr>

<%
        }
%>

    </table>
<%
    } else{
%>
    <h2>No hay ninguna cuenta vinculada a este usuario</h2>
<%
    }
%>

    </body>
</html>