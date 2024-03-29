<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.Empleado" %>
<%@ page import="es.taw.grupo25.dto.Chat" %>
<%
    /**
     * @author Jorge Camacho García
     */
%>
<html>
<head>
    <title>Chats disponibles</title>
</head>
<body>

<%
    // Obtenemos las variables que necesitamos
    List<Chat> chats = (List<Chat>) request.getAttribute("chats");

    Empleado empleadoAsistente = (Empleado) request.getAttribute("empleado");

    String nombreDelEmpleado = empleadoAsistente.getPersonaByPersonaId().getNombre();
%>

<h1> Bienvenido <%=nombreDelEmpleado%> </h1>

<h3> <a href="/"> Volver a la seccion principal </a> </h3>

<br/>

<h2> Filtros de busqueda </h2>
<form:form modelAttribute="filtro" method="post" action="filtrar">
    <table>
        <tr>
            <td>
                Nombre del cliente:
            </td>
            <td>
                <form:input path="nombre"/> <br/>
            </td>
        </tr>
        <tr>
            <td>
                Mostrar solo chats  abiertos:
            </td>
            <td>
                <form:checkbox path="soloAbiertos" /> <br/>
            </td>
        </tr>
        <tr>
            <td>
                Ultima interaccion posterior a:
            </td>
            <td>
                <form:input type="datetime-local" path="ultimoMensajeAntesDe" /> <br/>
            </td>
        </tr>
        <tr>
            <td>
                Ordenado por:
            </td>
            <td>
                <form:select path="orderBy">
                    <form:option value="0" label="--------------------------"/>
                    <form:option value="1" label="Ultimo mensaje mas reciente"/>
                    <form:option value="2" label="Orden alfabetico de nombre"/>
                    <form:option value="3" label="Orden de apertura creciente (primero antiguos)"/>
                    <form:option value="4" label="Orden de apertura decreciente (primero nuevos)"/>
                </form:select>
            </td>
        </tr>
    </table>
    <form:button> Aplicar filtro </form:button>
</form:form>

<br/>

<h2>Estos son los chats disponibles con los clientes</h2>

<%
    for (Chat chat : chats) {

%>

<a href="/asistente/chat?id=<%=chat.getId()%>"> <%= chat.getClienteByClienteId().getPersonaByPersonaId().getNombre() %></a>
<br/>

<%
    }
%>


</body>
</html>
