<%-- 
    Document   : index
    Created on : Sep 13, 2019, 9:23:28 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Revistas Web</title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <p><%=session.getAttribute("user")%></p>
    <center>
        <a href="index.jsp" title="Inicio"><img src="img/index.png" width="100" alt="Inicio" title="Inicio"/></a>

        <div>
            <p><a href="registroUsuario.jsp" title="Registrarme">Registrarme</a></p>
            <p><a href="iniciarSesion.jsp" title ="IniciarSesion">Iniciar Sesion</a></p>
        </div>
    </center>



    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
