<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.ChatEntity" %>
<%@ page import="es.taw.grupo25.entity.MensajeEntity" %>
<%@ page import="es.taw.grupo25.entity.EmpleadoEntity" %>
<html>
<head>
    <title>Chats disponibles</title>
</head>
<body>

<%
    // Obtenemos las variables que necesitamos
    List<ChatEntity> chats = (List<ChatEntity>) request.getAttribute("chats");

    EmpleadoEntity empleadoAsistente = (EmpleadoEntity) request.getAttribute("empleado");

    String nombreDelEmpleado = empleadoAsistente.getPersonaByPersonaId().getNombre();
%>

<h1> Bienvenido <%=nombreDelEmpleado%> </h1>
<h2>Estos son los chats disponibles con los clientes</h2>

<%
    for (ChatEntity chat : chats) {

%>

<a href="/asistente/chat?id=<%=chat.getId()%>"> <%= chat.getClienteByClienteId().getPersonaByPersonaId().getNombre() %></a>
<br/>

<%
    }
%>

</body>
</html>
