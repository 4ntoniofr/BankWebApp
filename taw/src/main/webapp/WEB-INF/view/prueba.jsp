<%@ page import="java.util.List" %>
<%@ page import="es.taw.grupo25.entity.RolClienteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Hola</h1>

<h2>Tipos de roles como cliente</h2>
<%
    List<RolClienteEntity> lista = (List<RolClienteEntity>) request.getAttribute("lista");

    for(RolClienteEntity e : lista){
%>
    <p><%= e.getRol() %></p>
<%
    }
%>