<%-- 
    Document   : iniciar
    Created on : Sep 14, 2019, 10:33:24 PM
    Author     : cesar31
--%>

<%@page import="java.util.List"%>
<%@page import="objeto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Obtener al usuario del servlet
    Usuario tmp = (Usuario) session.getAttribute("usuario");
    session.setAttribute("user", tmp.getIdUsuario());

    List<Revista> misRevistas = (List<Revista>) session.getAttribute("misRevistas");
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

        <div class="action">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="inicio.jsp"><%=session.getAttribute("user")%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ControladorRevista?accion=buscarEditores">Editores</a>
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
            <img src="ControladorUsuario?idUsuario=<%=tmp.getIdUsuario()%>" width="350" height="380"/>     
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

    <center>
        <div class="misRevistas" style="width: 100%">
            <h3 align="center">Mis Revistas</h3>

            <%
                for (int i = 0; i < misRevistas.size(); i++) {
            %>

            <center>
                <div class="card" style="width: 60%;">
                    <div class="card-body">
                        <h5 class="card-title"><%=misRevistas.get(i).getTituloRevista()%></h5>
                        <h6 class="card-subtitle mb-2 text-muted"><%=misRevistas.get(i).getIdUsuario()%></h6>
                        <img src="ControladorUsuario?idUsuario=<%=misRevistas.get(i).getIdUsuario()%>" class="align-self-start mr-3" alt="..." height="60px"/>
                        <a href="RevistasController?showPDF=<%=misRevistas.get(i).getIdRevista()%>" target="blank"><img src="img/mpdf.png" title="<%=misRevistas.get(i).getTituloRevista()%>" alt="<%=misRevistas.get(i).getTituloRevista()%>"/></a>
                        <p class ="card-text"><%=misRevistas.get(i).getDescripcion()%></p>
                        <!--Ver Revista-->
                        <a href="RevistasController?action=<%=misRevistas.get(i).getIdRevista()%>" class="btn btn-primary">Ver mas</a>
                    </div>
                </div>
                <hr/>
                <br/>
            </center>

            <%
                }
            %>

        </div>
    </center>


    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
