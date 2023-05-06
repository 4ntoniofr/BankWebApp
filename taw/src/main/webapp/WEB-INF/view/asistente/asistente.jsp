<%@ page import="es.taw.grupo25.dto.Empleado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @author Jorge Camacho García
     */
%>
<html>
<head>
    <title>Página del asistente</title>
</head>

<%
    Empleado asistente = (Empleado) session.getAttribute("asistente");
%>

<body>
    <h1> Sección para el asistente </h1>

    <%
        if(asistente != null){
    %>
        <h2> Bienvenido al servicio <%=asistente.getPersonaByPersonaId().getNombre()%></h2>
    <%
        }
    %>

    <ul>

        <%
            if(asistente == null){
        %>
                <li>
                    <a href="/asistente/iniciar-sesion"> Iniciar sesión </a>
                </li>
        <%
            } else {
        %>
                <li>
                    <a href="/asistente/cerrar-sesion"> Cerrar sesión </a>
                </li>
        <%
            }
        %>

        <li>
            <a href="/asistente/chats"> Chats </a>
        </li>

        <li>
            <a href="/"> Seccion principal </a>
        </li>
    </ul>

</body>
</html>