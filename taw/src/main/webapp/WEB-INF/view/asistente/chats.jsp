<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.ChatEntity" %>
<%@ page import="es.taw.grupo25.entity.MensajeEntity" %>
<html>
<head>
    <title>Chats</title>
</head>
<body>

<h1>Bienvenido a tus chats</h1>

<%
    List<ChatEntity> chats = (List<ChatEntity>) request.getAttribute("chats");
    List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("mensajes");
    String empleadoAsistenteId = (String) request.getAttribute("empleadoAsistenteId");

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
