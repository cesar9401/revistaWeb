<%-- 
    Document   : buscarEditores
    Created on : Oct 1, 2019, 8:10:13 PM
    Author     : cesar31
--%>

<%@page import="objeto.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Usuario> editores = (List<Usuario>) session.getAttribute("editores");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editores - <%=session.getAttribute("user")%></title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <div class="action">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link" href="inicio.jsp"><%=session.getAttribute("user")%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="ControladorRevista?accion=buscarEditores">Editores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ControladorRevista?accion=buscarRevistas">Revistas</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Acciones</a>
                    <div class="dropdown-menu">
                        <!--Insertar enlaces aqui-->
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="ControladorUsuario?action=CerrarSesion">Cerrar Sesion</a>
                    </div>
                </li>
            </ul>
        </div>

        <br/>
        <h1 align="center">Editores</h1>
        <hr/>

        <%
            for (int i = 0; i < editores.size(); i++) {
        %>

        <div class="jumbotron">
            <img src="ControladorUsuario?idUsuario=<%=editores.get(i).getIdUsuario()%>" class="align-self-start mr-3" alt="..." height="200px" align="left"/>

            <h1 class="display-4"><%=editores.get(i).getIdUsuario()%></h1>
            <h3><%=editores.get(i).getNombres() + " " + editores.get(i).getApellidos()%></h3>
            <h4>Contacto: <%=editores.get(i).getEmail()%></h4>
            <h6>About Me: <%=editores.get(i).getDescripcion()%></h6>
            <h6>Hobbies: <%=editores.get(i).getHobbies()%></h6>
            <br clear="none"/>
            <a class="btn btn-primary btn-lg" href="ControladorUsuario?action=<%=editores.get(i).getIdUsuario()%>" role="button">Perfil</a>
        </div>

        <%
            }
        %>


        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
