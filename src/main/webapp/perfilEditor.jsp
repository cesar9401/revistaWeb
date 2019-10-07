
<%@page import="java.util.List"%>
<%@page import="objeto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario editor = (Usuario) session.getAttribute("editor");
    List<Revista> revistasEditor = (List<Revista>) session.getAttribute("revistasEditor");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=session.getAttribute("user")%></title>
        <link href="css/estiloInicioEditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <div class="action">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link" href="ControladorRevista?accion=inicio"><%=session.getAttribute("user")%></a>
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

        <div class="foto">
            <img src="ControladorUsuario?idUsuario=<%=editor.getIdUsuario()%>" width="350" height="360"/>     
        </div>

        <div class="user">
            <h1><%=editor.getIdUsuario()%></h1>
            <p>Nombre: <%=editor.getNombres() + " " + editor.getApellidos()%></p>
            <p>Contacto: <%=editor.getEmail()%></p>
            <p>Nacimiento: <%=editor.getFechaNac()%></p>
            <p>Nacionalidad: <%=editor.getUbicacion()%></p>
            <p>Sexo: <%=editor.getSexo()%></p>
        </div>

        <div class="info">
            <h3>About Me:</h3>
            <p><%=editor.getDescripcion()%></p>
        </div>

        <div class="info2">
            <h3>Hobbies:</h3>
            <p><%=editor.getHobbies()%></p>
        </div>


        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
