<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.*" %>
<%@ page import="es.taw.grupo25.dto.Cliente" %>
<html>
<head>
    <title>Chat privado</title>
</head>
<body>

<%

    Chat chat = (Chat) request.getAttribute("chat");
    Persona asistentePersona = null;
    Persona clientePersona = null;

    List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("mensajes");

    String rol = (String) request.getAttribute("rol");
    int idPersonaEmisor = 0;
    if(rol.equals("c")){
        ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
        clientePersona = cliente.getPersonaByPersonaId();

        EmpleadoEntity asistente = (EmpleadoEntity) request.getAttribute("asistente");
        asistentePersona = asistente.getPersonaByPersonaId();
        idPersonaEmisor = clientePersona.getId();
    }else if(rol.equals("a")){
        EmpleadoEntity asistente = (EmpleadoEntity) session.getAttribute("asistente");
        asistentePersona = asistente.getPersonaByPersonaId();
        idPersonaEmisor = asistentePersona.getId();

        ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
        clientePersona = cliente.getPersonaByPersonaId();
    }

    int idChat = chat.getId();
%>

<div id="receptor">
    <h1> Chat con <%=clientePersona.getNombre()%></h1>
</div>

<div id="chat" style="width: 80%;  margin: auto; padding: 10px;">


    <div id="chatbox">
        <%
            for (MensajeEntity mensaje : mensajes) {
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

    <%
        if(chat.getFechaCierre() == null) {

    %>
    <div class="siguiente-mensaje" >
        <form:form action="/${rol eq 'a' ? 'asistente' : 'cliente'}/enviar-mensaje" method="post" modelAttribute="siguienteMensaje">
            <form:hidden path="personaByEmisor" value="<%=idPersonaEmisor%>"/>
            <form:hidden path="chatByChat" value="<%=idChat%>"/>
            <form:input path="texto" maxlength="500" cssStyle="width: 100%" />
            <button style="float: right;"> Enviar</button>
        </form:form>
    </div>

    <%
            // En caso de que el asistente quiera cerrar la conversación
            if(rol.equals("a")) {
    %>
            <form action="/asistente/cerrar-chat?id=<%=chat.getId()%>" method="post">
                <button> Cerrar la consulta </button>
            </form>

            <a href="/asistente/chats">
                Volver a los chats
            </a>
    <%
            }else {
    %>
            <a href="/cliente">
                Volver a seccion cliente
            </a>
    <%
            }
        } else {
    %>
        <h1> Este chat ya ha sido cerrado</h1>
    <%
        }
    %>

</div>



</body>
</html>
