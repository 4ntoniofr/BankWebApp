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

    <h2>Operaciones de Origen:</h2>
<%
    List<TransaccionEntity> transacciones = (List<TransaccionEntity>) request.getAttribute("transacciones");
    if(!transacciones.isEmpty()){
%>

    <table border="1">
        <tr>
            <th>Id</th>
            <th>Fecha Instrucción</th>
            <th>Fecha Ejecución</th>
            <th>IBAN Origen</th>
            <th>IBAN Destino</th>
        </tr>

<%
        for(TransaccionEntity tran: transacciones){
%>

        <tr>
            <td><%=tran.getId()%></td>
            <td><%=tran.getFechaInstruccion()%></td>
            <td><%=tran.getFechaEjecucion()%></td>
            <td><%=tran.getCuentaBancariaByCuentaOrigen().getIban()%></td>
            <td><%=tran.getCuentaBancariaByCuentaDestino().getIban()%></td>
        </tr>

<%
        }
%>

    </table>
<%
    } else{
%>
    <h2>No se ha realizado ninguna operación con origen en esta cuenta</h2>
<%
    }
%>


    <h2>Operaciones de Destino:</h2>
    <%
        List<TransaccionEntity> transacciones_destino = (List<TransaccionEntity>) request.getAttribute("transacciones_destino");
        if(!transacciones_destino.isEmpty()){
    %>

    <table border="1">
        <tr>
            <th>Id</th>
            <th>Fecha Instrucción</th>
            <th>Fecha Ejecución</th>
            <th>IBAN Origen</th>
            <th>IBAN Destino</th>
        </tr>

        <%
            for(TransaccionEntity tran: transacciones_destino){
        %>

        <tr>
            <td><%=tran.getId()%></td>
            <td><%=tran.getFechaInstruccion()%></td>
            <td><%=tran.getFechaEjecucion()%></td>
            <td><%=tran.getCuentaBancariaByCuentaOrigen().getIban()%></td>
            <td><%=tran.getCuentaBancariaByCuentaDestino().getIban()%></td>
        </tr>

        <%
            }
        %>

    </table>
    <%
    } else{
    %>
    <h2>No se ha realizado ninguna operación con destino a esta cuenta</h2>
    <%
        }
    %>


    </body>
</html>