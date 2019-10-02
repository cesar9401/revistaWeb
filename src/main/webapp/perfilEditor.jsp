
<%@page import="objeto.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario editor = (Usuario) session.getAttribute("editor");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=session.getAttribute("user") %></title>
    </head>
    <body>
        <h1><%=editor.getIdUsuario() %></h1>
    </body>
</html>
