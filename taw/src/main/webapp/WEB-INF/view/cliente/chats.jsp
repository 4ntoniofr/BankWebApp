<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.*" %>
<html>
<head>
    <title>Chats disponibles</title>
</head>

<%
    List<Chat> chatsAbiertos = (List<Chat>) request.getAttribute("chatsAbiertos");

    List<Chat> chatsCerrados = (List<Chat>) request.getAttribute("chatsCerrados");
%>

<body>

<h1>Chats con los asistentes</h1>

<h2> Abrir un nuevo chat </h2>

<form action="/cliente/nuevoChat" method="post">
    <button> Generar nuevo chat </button>
</form>

<h3> Ordenar todos los chats: </h3>
<form:form action="/cliente/chats" method="post" modelAttribute="filtro">
    <form:select path="orden">
        <form:option value="0" label="Orden de apertura creciente (primero antiguos)"/>
        <form:option value="1" label="Orden de apertura decreciente (primero nuevos)"/>
    </form:select>

    <form:button> Aplicar orden </form:button>
</form:form>

<h2> Chats abiertos </h2>
<p> Recuerda que si has solucionado tu problema puedes cerrar el chat. </p>
<br/>

<%
    for(Chat chat: chatsAbiertos){
%>
    <a href="/cliente/chat?id=<%=chat.getId()%>">  <%=chat.getEmpleadoByEmpleadoId().getPersonaByPersonaId().getNombre()%></a> <br/>
<%
    }
%>

<h2> Chats cerrados</h2>
<p> Una vez que cierres el chat no podras volver a abrirlo, mantendremos las conversaciones
por seguridad o por si necesitas revisar algo. </p>
<br/>
<%
    for(Chat chat: chatsCerrados){
%>
    <a href="/cliente/chat?id=<%=chat.getId()%>">  <%=chat.getEmpleadoByEmpleadoId().getPersonaByPersonaId().getNombre()%></a> <br/>
<%
    }
%>

<a style="position: fixed; bottom: 30px;" href="/cliente"> Volver atras </a>

</body>