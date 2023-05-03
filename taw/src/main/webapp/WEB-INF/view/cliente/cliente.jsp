<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

 Fichero hecho por:
 Valentín García Rosas

--%>

<html>
    <head>
        <title>Cliente</title>
    </head>

    <body>
        <h1>Clientes</h1>

        <h2>Página principal de clientes</h2>

        <%
            String error = (String) request.getAttribute("error");
            if(error!=null){
        %>
                <%=error%>
        <%
            }else{
        %>

        <a href="/cliente/perfil">Modificar Perfil</a> </br>
        <a href="/cliente/cuentas">Mis Cuentas</a> </br>
        <a href="/cliente/chats"> Chats de asistencia </a>

    <%
        }
    %>
        </br><a href="/cliente/logout">LogOut y salir</a>
    </body>
</html>