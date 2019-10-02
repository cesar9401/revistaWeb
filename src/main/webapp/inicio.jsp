<%-- 
    Document   : iniciar
    Created on : Sep 14, 2019, 10:33:24 PM
    Author     : cesar31
--%>

<%@page import="objeto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Obtener al usuario del servlet
    Usuario tmp = (Usuario) session.getAttribute("usuario");
    session.setAttribute("user", tmp.getIdUsuario());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estiloInicio.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Inicio - Revistas Web</title>
    </head>
    <body>

        <div class="foto">
            <img src="ControladorUsuario?idUsuario=<%=tmp.getIdUsuario()%>" width="350" height="360"/>     
        </div>

        <div class="acciones">
            <h4><a href="ControladorRevista?accion=buscarEditores">Buscar Editores</a></h4>
            <h4><a href="ControladorRevista?accion=buscarRevistas" tittle="Revistas">Buscar Revistas</a></h4>
            <h4><a href="ControladorUsuario?action=CerrarSesion">Cerrar Sesion</a></h4>

        </div>

        <div class="user">
            <h1><%=tmp.getIdUsuario()%></h1>
            <p>Contacto: <%=tmp.getEmail()%></p>
            <p>Nacimiento: <%=tmp.getFechaNac()%></p>
            <p>Nacionalidad: <%=tmp.getUbicacion()%></p>
            <p>Sexo: <%=tmp.getSexo()%></p>
        </div>

        <div class="info">
            <h3>About Me:</h3>
            <p><%=tmp.getDescripcion()%></p>
        </div>

        <div class="info2">
            <h3>Hobbies:</h3>
            <p><%=tmp.getHobbies()%></p>
        </div>


        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
