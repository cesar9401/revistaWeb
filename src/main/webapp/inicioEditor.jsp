<%-- 
    Document   : inicioEditor
    Created on : Sep 21, 2019, 4:23:21 PM
    Author     : cesar31
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="objeto.Revista"%>
<%@page import="objeto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Obtener al usuario del servlet
    Usuario tmp = (Usuario) session.getAttribute("usuario");
    List<Revista> revistas = (List<Revista>) session.getAttribute("revistas");
    List<Revista> reporteRevistas = (List<Revista>) session.getAttribute("reporteRevistas");
    session.setAttribute("user", tmp.getIdUsuario());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio - <%=tmp.getIdUsuario()%></title>
        <link href="css/estiloInicioEditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="action">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="inicioEditor.jsp"><%=session.getAttribute("user")%></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="ControladorRevista?accion=newRevista">Nueva Revista</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Acciones</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target=".bd-example-modal-xl">Reporte Suscripciones</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="ControladorUsuario?action=CerrarSesion">Cerrar Sesion</a>
                    </div>
                </li>
            </ul>
        </div>


        <div class="foto">
            <img src="ControladorUsuario?idUsuario=<%=tmp.getIdUsuario()%>" width="350" height="360"/>     
        </div>

        <div class="user">
            <h1><%=tmp.getIdUsuario()%></h1>
            <p>Nombre: <%=tmp.getNombres() + " " + tmp.getApellidos()%></p>
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

        <div class="ListRevistas">
            <h2 align="center">Mis Revistas</h2><hr/>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Titulo</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Edicion</th>
                        <th scope="col">Cuota Suscripcion</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Suscriptores</th>
                        <th scope="col">ArchivoPDF</th>
                        <th scope="col" rowspan="3">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < revistas.size(); i++) {
                    %>
                    <tr>
                        <th scope="row"><%=i + 1%></th>
                        <td><%=revistas.get(i).getTituloRevista()%></td>
                        <td><%=revistas.get(i).getCategoria()%></td>
                        <td><%=revistas.get(i).getEdicion()%></td>
                        <td><%=revistas.get(i).getCuotaSuscripcion()%></td>
                        <%if (revistas.get(i).isBloquear()) {
                        %>
                        <td>Bloqueado</td>
                        <%} else {
                        %>
                        <td>Activo</td>
                        <%
                            }
                        %>
                        <td><%=revistas.get(i).getSuscripciones()%></td>
                        <td><a href="RevistasController?showPDF=<%=revistas.get(i).getIdRevista()%>" target="blank"><img src="img/mpdf.png" title="<%=revistas.get(i).getTituloRevista()%>" alt="<%=revistas.get(i).getTituloRevista()%>"/></a></td>
                        <td><img src="img/nuevo.png"/></td>
                        <td><img src="img/editar.png"/></td>
                        <td><img src="img/delete.png"/></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>


        <!--Modal Reporte de Suscripciones-->
        <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Reporte de Suscripciones</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <table class="table table-striped table-dark">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Titulo</th>
                                <th scope="col">Edicion</th>
                                <th scope="col">Cuota Suscripcion</th>
                                <th scope="col">Suscriptor</th>
                                <th scope="col">Fecha Suscripcion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (int i = 0; i < reporteRevistas.size(); i++) {
                            %>
                            <tr>
                                <th scope="row"><%=i + 1%></th>
                                <td><%=reporteRevistas.get(i).getTituloRevista()%></td>
                                <td><%=reporteRevistas.get(i).getEdicion()%></td>
                                <td><%=reporteRevistas.get(i).getCuotaSuscripcion()%></td>
                                <td><%=reporteRevistas.get(i).getIdSuscriptor()%></td>
                                <td><%=reporteRevistas.get(i).getFechaSuscripcion()%></td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>



        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
