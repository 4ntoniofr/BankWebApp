<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.dto.*" %>
<html>
<head>
    <title>Chat privado</title>

    <script>
        window.onload = function() {
            // DE ESTA FORMA EL SCROLL ESTA SIEMPRE ABAJO
            var chat = document.getElementById("chat");
            chat.scrollTop = chat.scrollHeight;
        }
    </script>
</head>
<body>

<%

    Chat chat = (Chat) request.getAttribute("chat");
    Persona asistentePersona = null;
    Persona clientePersona = null;

    List<Mensaje> mensajes = (List<Mensaje>) request.getAttribute("mensajes");

    String rol = (String) request.getAttribute("rol");

    Cliente cliente = (Cliente) request.getAttribute("cliente");
    clientePersona = cliente.getPersonaByPersonaId();

    int idPersonaEmisor = 0;

    // Establecer el rol de la conversación
    if(rol.equals("c")){
        Empleado asistente = (Empleado) request.getAttribute("asistente");
        asistentePersona = asistente.getPersonaByPersonaId();

        idPersonaEmisor = clientePersona.getId();
    }else if(rol.equals("a")){
        Empleado asistente = (Empleado) session.getAttribute("asistente");
        asistentePersona = asistente.getPersonaByPersonaId();

        idPersonaEmisor = asistentePersona.getId();
    }

    int idChat = chat.getId();
%>

<div id="receptor">
    <h1> Chat con <%=rol.equals("a")?clientePersona.getNombre():asistentePersona.getNombre()%></h1>
</div>

<div id="chat" style="width: 80%;  margin: auto; padding: 10px; height: 75%; overflow: auto">
    <div id="chatbox">
        <%
            for (Mensaje mensaje : mensajes) {
                // EL MENSAJE ES DEL ASISTENTE
                if(mensaje.getPersonaByEmisor().equals(asistentePersona)){
                    // ERES ASISTENTE
                    if(rol.equals("a")){
        %>
                        <p style="text-align: right; background-color: darkseagreen; padding: 5px; border: 1px solid black">
                            <%=asistentePersona.getNombre()%> [<%=mensaje.getFecha()%>]: <br/> <%=mensaje.getTexto()%>
                        </p>
        <%
                    // ERES CLIENTE
                    } else {
        %>
                        <p style="text-align: left; background-color: lightgrey; padding: 5px; border: 1px solid black">
                            <%=asistentePersona.getNombre()%> [<%=mensaje.getFecha()%>]: <br/> <%=mensaje.getTexto()%>
                        </p>
        <%
                     }
                // EL MENSAJE ES DEL CLIENTE
                }else{
                    // ERES ASISTENTE
                    if(rol.equals("a")){
        %>
                        <p style="text-align: left; background-color: lightgrey; padding: 5px; border: 1px solid black">
                            <%=clientePersona.getNombre()%> [<%=mensaje.getFecha()%>]: <br/> <%=mensaje.getTexto()%>
                        </p>
        <%
                    // ERES CLIENTE
                    } else {
        %>
                        <p style="text-align: right; background-color: darkseagreen; padding: 5px; border: 1px solid black">
                            <%=clientePersona.getNombre()%> [<%=mensaje.getFecha()%>]: <br/> <%=mensaje.getTexto()%>
                        </p>
        <%
                        }
                }
            }
        %>

    </div>
</div>


    <%
        if(chat.getFechaCierre() == null) {

    %>
    <div class="siguiente-mensaje" style="width: 80%;  margin: auto; padding: 20px;" >
        <form:form action="/${rol eq 'a' ? 'asistente' : 'cliente'}/enviar-mensaje" method="post" modelAttribute="siguienteMensaje">
            <form:hidden path="idPersonaEmisorNuevoMensaje" value="<%=idPersonaEmisor%>"/>
            <form:hidden path="idChatNuevoMensaje" value="<%=idChat%>"/>
            <form:input path="texto" maxlength="500" cssStyle="width: 100%" />
            <button style="float: right;"> Enviar</button>
        </form:form>
    </div>

    <%
            // En caso de que el asistente quiera cerrar la conversación
            if(rol.equals("c")) {
    %>
            <form action="/cliente/cerrar-chat?id=<%=chat.getId()%>" method="post">
                <button> Cerrar la consulta </button>
            </form>

    <%
        }
    %>


    <%
        } else {
    %>
        <h1> Este chat ya ha sido cerrado</h1>
    <%
        }
    %>


    <a href="/<%=rol.equals("c")?"cliente":"asistente"%>/chats">
        Volver a los chats
    </a>





</body>
</html>
