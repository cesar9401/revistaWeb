<%-- 
    Document   : iniciar
    Created on : Sep 14, 2019, 10:33:24 PM
    Author     : cesar31
--%>

<%@page import="objeto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            //Obtener al usuario del servlet
            Usuario tmp =(Usuario) request.getAttribute("usuario");
        %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <title>Inicio - Revistas Web</title>

    </head>
    
        <h1>Bienvenido al sistema</h1>
        <%=tmp.getIdUsuario()%><br/>
        <%=tmp.getEmail()%><br/>
        <img src="ControladorUsuario?idUsuario=<%=tmp.getIdUsuario()%>" width="400"/>            
        
       
    
</html>
