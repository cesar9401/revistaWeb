<%-- 
    Document   : iniciarSesion
    Created on : Sep 19, 2019, 12:09:53 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    <body>
        <p><%=session.getAttribute("user")%></p>


    <center>
        <div>
            <form action="ControladorUsuario" method="post">
                <h1>Iniciar Sesion</h1>
                <label for="usuario">Usuario</label><br/>
                <input id="usuario" name="usuario" maxlength="30"/><br/><br/>
                <label for="pass">Password</label><br/>
                <input type="password" id="pass" name="pass" maxlength="12"/><br/><br/>
                <button type="submit" name="accion" value="Iniciar">Iniciar Sesion</button> 
            </form>
        </div>

    </center>


</body>
</html>
