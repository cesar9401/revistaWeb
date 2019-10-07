<%-- 
    Document   : buscarRevistas
    Created on : Sep 25, 2019, 11:37:43 AM
    Author     : cesar31
--%>

<%@page import="java.util.List"%>
<%@page import="objeto.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Revista> revistasUser = (List<Revista>) session.getAttribute("revistasUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Revistas - <%=session.getAttribute("user")%></title>
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
                    <a class="nav-link" href="ControladorRevista?accion=buscarEditores">Editores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="ControladorRevista?accion=buscarRevistas">Revistas</a>
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
        <h1 align="center">Revistas</h1>
        <hr/>


        <%
            for (int i = 0; i < revistasUser.size(); i++) {
        %>

    <center>
        <div class="card" style="width: 60%;">
            <div class="card-body">
                <h5 class="card-title"><%=revistasUser.get(i).getTituloRevista()%></h5>
                <h6 class="card-subtitle mb-2 text-muted"><%=revistasUser.get(i).getIdUsuario()%></h6>
                <img src="ControladorUsuario?idUsuario=<%=revistasUser.get(i).getIdUsuario()%>" class="align-self-start mr-3" alt="..." height="60px"/>
                <p class ="card-text"><%=revistasUser.get(i).getDescripcion()%></p>
                <p class="card-text mb-2 text-muted"><strong>Fecha de Publicacion: </strong><%=revistasUser.get(i).getFechaPublicacion()%></p>
                <p class="card-text mb-2 text-muted"><strong>Suscripciones: </strong><%=revistasUser.get(i).getSuscripciones()%></p>

                <!--
                <a href="RevistasController?action=<%=revistasUser.get(i).getIdRevista()%>" class="btn btn-primary">Ver mas</a>
                -->
                <!--Abrir modal para fecha-->
                <a href="#" data-toggle="modal" onclick="selRevista('<%=revistasUser.get(i).getIdRevista()%>', '<%=revistasUser.get(i).getCuotaSuscripcion()%>')" data-target="#myModal" class="btn btn-primary">Suscribirme</a>
            </div>
        </div>
        <hr/>
        <br/>
    </center>
    <br/>

    <%
        }
    %>

    <!--Modal para establecer fecha y recibir idRevista y cuota de la revista-->
    <div class="modal" tabindex="-1" role="dialog" id="myModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="ControladorRevista" method="post">
                        <center>
                            <!-- Para enviar el id de la revista-->
                            <input type="hidden" name="idMagazine" id="idMagazine"/>
                            <input type="hidden" name="cuota" id="cuota"/>
                            <input type="date" name="fecha" id="fecha"/> <br/><br/>
                            <!--Confirmar Suscripcion-->
                            <button type="submit" name="accion" value="Suscribirme" class="btn btn-primary">Suscribirme</button>   
                        </center>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!--Script para manejar las variables del modal-->
    <script>
        selRevista = function (idRevista, cuotaSuscripcion) {
            $('#idMagazine').val(idRevista);
            $('#cuota').val(cuotaSuscripcion);
        };
    </script>




    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
