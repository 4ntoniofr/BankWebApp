<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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

        <a href="/cliente/perfil">Modificar Perfil</a></br>
        <a href="/cliente/cuentas">Mis Cuentas</a>

    <%
        }
    %>
        </br><a href="/cliente/logout">LogOut</a>
    </body>
</html>