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

        <%
            for (int i = 0; i < revistasUser.size(); i++) {
        %>
        <p><%=revistasUser.get(i).getTituloRevista()%></p>
        <%
            }
        %>



        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
