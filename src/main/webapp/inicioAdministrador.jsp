<%-- 
    Document   : inicioAdministrador
    Created on : Sep 26, 2019, 9:54:21 PM
    Author     : cesar31
--%>

<%@page import="java.util.List"%>
<%@page import="objeto.Revista"%>
<%@page import="objeto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario admin = (Usuario) session.getAttribute("admin");
    List<Revista> revistas = (List<Revista>) session.getAttribute("revistas");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <title>Admin - <%=admin.getIdUsuario()%></title>
    </head>
    <body>
    <center>
        <h1>Administrador</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">UsuarioAdmin</th>
                    <th scope="col">email</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row"><%=admin.getIdUsuario()%></th>
                    <td><%=admin.getEmail()%></td>
                </tr>
            </tbody>
        </table>
        <br/>
        <hr/>
        <div>
            <h1>Revistas por Revisar</h1>
            <table class="table table-striped table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Titulo</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Descripcion</th>
                        <th scope="col">Edicion</th>
                        <th scope="col">Cuota</th>
                        <th scope="col">Reacciones</th>
                        <th scope="col">Comentarios</th>
                        <th scope="col">Editor</th>
                        <th scope="col">Procesar</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (int i = 0; i < revistas.size(); i++) {
                    %>
                    <tr>
                        <th scope="row"><%=revistas.get(i).getIdRevista() %></th>
                        <td><a href="RevistasController?showPDF=<%=revistas.get(i).getIdRevista()%>" target="_blank"><%=revistas.get(i).getTituloRevista()%></a></td>
                        <td><%=revistas.get(i).getCategoria()%></td>
                        <td><%=revistas.get(i).getDescripcion()%></td>
                        <td><%=revistas.get(i).getEdicion()%></td>
                        <td><%=revistas.get(i).getCuotaSuscripcion()%></td>
                        <td><%=revistas.get(i).isReaccion()%></td>
                        <td><%=revistas.get(i).isComentarios()%></td>
                        <td><%=revistas.get(i).getIdUsuario()%></td>
                        <td><a href="ControladorRevista?accion=<%=revistas.get(i).getIdRevista()%>" title="<%=revistas.get(i).getTituloRevista()%>">Procesar</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <br/>
        <hr/>
        <h4><a href="ControladorUsuario?action=CerrarSesion">Cerrar Sesion</a></h4>
        <hr/>
    </center>







    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
