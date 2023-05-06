<%
    /**
     * @author Jorge Camacho García
     */
%>

<h1>Autenticacion de asistente:</h1>


<%
    String error = (String) request.getAttribute("error");
    if(error!=null){
%>
<p style="color:red;"><%=error%></p>
<%
    }
%>

<form action="/asistente/iniciar-sesion" method="post">
    <table>
        <tr>
            <td>Usuario:</td> <td><input type="text" name="usuario"></td>
        </tr>
        <tr>
            <td>Clave:</td> <td><input type="password" name="clave"> </td>
        </tr>
        <tr>
            <td colspan="2"> <button>Enviar</button></td>
        </tr>
    </table>
</form>