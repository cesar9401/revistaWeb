<%-- 
    Document   : buscarRevistas
    Created on : Sep 25, 2019, 11:37:43 AM
    Author     : cesar31
--%>

<%@page import="java.util.List"%>
<%@page import="objeto.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Revista> revistas = (List<Revista>) session.getAttribute("revistasUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Revistas - <%=session.getAttribute("user")%></title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <%
            for (int i = 0; i < revistas.size(); i++) {
                    %>
                    <p><%=revistas.get(i).getTituloRevista() %></p>
                    <p><%=revistas.get(i).isBloquear() %></p>
                    <%
                }
        %>
        
    </body>
</html>
