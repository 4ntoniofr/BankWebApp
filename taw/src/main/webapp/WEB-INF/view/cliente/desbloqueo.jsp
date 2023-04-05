<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.grupo25.entity.CuentaInternaEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Desbloqueo de Cuenta</title>
    </head>

    <body>
        <h1>Desbloqueo de Cuenta:</h1>

        <%
            CuentaInternaEntity cuenta = (CuentaInternaEntity) request.getAttribute("cuenta");
            if(cuenta != null){
        %>

        <h2>Iban de la cuenta: <%=cuenta.getCuentaBancariaByCuentaBancaria().getIban()%></h2>

        <h3>Estado de la cuenta: <%=cuenta.getEstadoCuentaByEstadoCuenta().getEstado()%></h3>

        <%
            if(cuenta.getEstadoCuentaByEstadoCuenta().getEstado().equals("BLOQUEADA")){
        %>
        <form:form method="post" action="desbloqueo" modelAttribute="cuenta">
            <form:hidden path="id"></form:hidden>
            <form:button>Solicitar Desbloqueo de la Cuenta</form:button>
        </form:form>


        <%
            }
        %>
    <%
        } else{
    %>
        <h2>No hay ninguna cuenta asociada a este usuario</h2>

    <%
            }
    %>

    </body>
</html>