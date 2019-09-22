<%-- 
    Document   : inicioEditor
    Created on : Sep 21, 2019, 4:23:21 PM
    Author     : cesar31
--%>

<%@page import="objeto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Obtener al usuario del servlet
    Usuario tmp = (Usuario) request.getAttribute("usuario");
    session.setAttribute("user", tmp.getIdUsuario());
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio - <%=tmp.getIdUsuario()%></title>
        <link href="css/estiloInicioEditor.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="foto">
            <img src="ControladorUsuario?idUsuario=<%=tmp.getIdUsuario()%>" width="350" height="350"/>     
        </div>

        <div class="info">
            <h4><a href="nuevaRevista.jsp">Nueva Revista</a></h4>
            <h4>Mis Revistas</h4>
            <h4>Metodo de Pago</h4>
            
        </div>

        <div class="user">
            <h1><%=tmp.getIdUsuario()%></h1>
            <p>Contacto: <%=tmp.getEmail()%></p>
            <p>Nacimiento: <%=tmp.getFechaNac()%></p>
            <p>Nacionalidad: <%=tmp.getUbicacion()%></p>
            <p>Sexo: <%=tmp.getSexo()%></p>
        </div>

    </body>
</html>
