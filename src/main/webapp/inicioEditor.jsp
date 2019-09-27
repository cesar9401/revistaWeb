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
    //Usuario tmp = (Usuario) request.getAttribute("usuario");
    Usuario tmp = (Usuario) session.getAttribute("usuario");
    List<Revista> revistas = (List<Revista>) session.getAttribute("revistas");
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

        <div class="foto">
            <img src="ControladorUsuario?idUsuario=<%=tmp.getIdUsuario()%>" width="350" height="360"/>     
        </div>

        <div class="acciones">
            <h4><a href="ControladorRevista?accion=newRevista">Nueva Revista</a></h4>
            <h4>Metodo de Pago</h4>
            <h4><a href="ControladorUsuario?action=CerrarSesion">Cerrar Sesion</a></h4>
        </div>

        <div class="user">
            <div>
                <h1><%=tmp.getIdUsuario()%></h1>
                <p>Contacto: <%=tmp.getEmail()%></p>
                <p>Nacimiento: <%=tmp.getFechaNac()%></p>
                <p>Nacionalidad: <%=tmp.getUbicacion()%></p>
                <p>Sexo: <%=tmp.getSexo()%></p>
            </div>
            <div class="foto2">
                <img src="img/editor.png" alter="editor" title="editor" height="160px"/>
            </div>
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
            <h2>Mis Revistas </h2>

            <table border="1px">
                <tr>
                    <th>Titulo</th>
                    <th>Categoria</th>
                    <th>Descripcion</th>
                    <th>Edicion</th>
                    <th>Cuota Suscripcion</th>
                </tr>
                <%
                    for (int i = 0; i < revistas.size(); i++) {
                %>
                <tr>
                    <td><%=revistas.get(i).getTituloRevista()%></td>
                    <td><%=revistas.get(i).getCategoria()%> </td>
                    <td><%=revistas.get(i).getDescripcion()%></td>
                    <td><%=revistas.get(i).getEdicion()%></td>
                    <td><%=revistas.get(i).getCuotaSuscripcion()%></td>
                </tr>
                <%
                    }
                %>



            </table>
        </div>

        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
