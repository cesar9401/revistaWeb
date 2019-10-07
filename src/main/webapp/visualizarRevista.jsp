<%-- 
    Document   : visualizarRevista
    Created on : Oct 2, 2019, 10:18:36 PM
    Author     : cesar31
--%>

<%@page import="java.util.List"%>
<%@page import="objeto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Revista revista = (Revista) session.getAttribute("revista");
    List<Comentario> comentarios = (List<Comentario>) session.getAttribute("comentarios");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=session.getAttribute("user")%> - <%=revista.getTituloRevista()%> </title>
        <link href="css/estiloRevistas.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="action">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="RevistasController?action=<%=revista.getIdRevista()%>"><%=revista.getTituloRevista()%></a>
                </li>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="inicio.jsp"><%=session.getAttribute("user")%></a>
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

        <br/><br/>
        <figure class="figure">
            <img src="ControladorUsuario?idUsuario=<%=revista.getIdUsuario()%>" class="figure-img img-fluid rounded" alt="<%=revista.getIdUsuario()%>" title="<%=revista.getIdUsuario()%>" width="45%">
            <figcaption class="figure-caption" style="background-color: white; width: 45%">Autor: <%=revista.getIdUsuario()%></figcaption>
        </figure>

        <div width="45%">
            <form>
                <button><img src="img/like.jpg" height="80px" title="Me Gusta" alt="Me Gusta"/></button>
                <button><img src="img/dislike.jpg" height="80px" title="No Me Gusta" alt="No Me Gusta"/></button>   
            </form>
        </div>


        <div class="coments">
            <br/><br/>
            <h1 style="color: navy" align="center"><%=revista.getTituloRevista()%></h1>

            <%
                for (int i = 0; i < comentarios.size(); i++) {
            %>

            <dl class="row">
                <dt class="col-sm-1">
                    <img src="ControladorUsuario?idUsuario=<%=comentarios.get(i).getIdUsuario()%>" height="50"/>    
                </dt>
                <dd class="col-sm-11">
                    <p style="background-color: #B1C4EE"><%=comentarios.get(i).getComentario()%></p>
                </dd>
            </dl>

            <%
                }
            %>

            <dl class="row">
                <dt class="col-sm-1">
                    <img src="ControladorUsuario?idUsuario=<%=session.getAttribute("user")%>" height="50"/>    
                </dt>
                <dd class="col-sm-11">
                    <dl class="row">
                        <dt class="col-sm-11">
                            <input type="text" class="form-control" name="comment" id="comment"/>
                        </dt>
                        <dd class="col-sm-1">
                            <a href="" data-toggle="modal" onclick="enviarTexto()" data-target="#myModal" class="btn btn-primary">Comentar</a> 
                        </dd>
                    </dl>
                </dd>
            </dl>
        </div>

        <!--Modal-->
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
                                <input type="hidden" name="comentario" id="comentario"/>
                                <input type="date" name="fecha" id="fecha"/> <br/><br/>
                                <!--Confirmar Suscripcion-->
                                <button type="submit" name="accion" value="Comentario" class="btn btn-primary">Enviar Comentario</button>   
                            </center>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!--Script para insertar texto en el formulario del modal--->
        <script type="text/javascript">
            function enviarTexto() {
                var texto = document.getElementById("comment").value;
                document.getElementById("comentario").value = texto;
            }
        </script>


        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
