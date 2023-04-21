<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Transaccion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Mis Operaciones</title>
    </head>

    <body>


    <h1>Mis Operaciones</h1>

    <form:form action="operaciones" method="post" modelAttribute="filtro">
        <form:hidden path="idCuenta"></form:hidden>
        Buscar por</br>
        Fecha Instruccion: <form:input type="date" path="fechaInstruccion"></form:input>
        Fecha Ejecucion: <form:input type="date" path="fechaEjecucion"></form:input>
        IBAN Destino <form:input path="iban"></form:input></br>
        <form:button>Filtrar</form:button>
    </form:form>

<%
    List<Transaccion> transacciones = (List<Transaccion>) request.getAttribute("transacciones");
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
        for(Transaccion tran: transacciones){
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
    <h2>No hay ninguna operación</h2>
<%
    }
%>


    </body>
</html>