<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.ChatEntity" %>
<%@ page import="es.taw.grupo25.entity.MensajeEntity" %>
<%@ page import="es.taw.grupo25.entity.EmpleadoEntity" %>
<html>
<head>
    <title>Chats</title>
</head>
<body>

<%
    // Obtenemos las variables que necesitamos
    List<ChatEntity> chats = (List<ChatEntity>) request.getAttribute("chats");
    List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("mensajes");

    // TODO: desde el controlador deberá controlarse que la persona que está accediendo esta registrada
    EmpleadoEntity empleadoAsistente = (EmpleadoEntity)  request.getAttribute("empleado");

    // TODO: cuando sea seguro que empleadoAsistente no es nulo podremos obtener el nombre
    String nombreDelEmpleado = empleadoAsistente.getPersonaByPersonaId().getNombre();



%>

<h1>Bienvenido <%=nombreDelEmpleado%></h1>
<h2>Estos son los chats disponibles con los clientes</h2>

<%
    for (int i = 0; i <= 1; i++) {
        // for(chatDisponible : chats) {

%>
<div id="chat">
    <div id="receptor">
        <p> Este es el chat con el cliente: ${chatDisponible.clienteByClienteId}</p>
    </div>

    <div id="chatbox">
        <%
            for (int j = 0; j <= 2; j++) {
                if(true /* || chatDisponible.empleadoByEmpleadoId == empleadoAsistenteId */){

        %>
                <p> Un mensaje</p>
        <%
                }
            }
        %>

    </div>

    <form:form action="/asistente/enviar-mensaje" method="post" modelAttribute="siguienteMensaje">
        <form:hidden path="id"/>
        <form:hidden path="fecha"/>
        <form:hidden path="personaByEmisor"/>
        <form:hidden path="chatByChat"/>
        <form:input path="texto"/>
        <button> Enviar</button>
    </form:form>
    <br/>
    <hr/>
    <br/>
</div>
<%
    }
%>

</body>
</html>
