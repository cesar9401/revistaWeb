<%-- 
    Document   : nuevaRevista
    Created on : Sep 21, 2019, 7:34:02 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Revista - <%=session.getAttribute("user")%></title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <form action="ControladorRevista" method="post" enctype="multipart/form-data">
            <h1>Nueva Revista</h1><hr/>
            <label for="titulo">Titulo</label><br/>
            <input name="titulo" id="titulo"/><br/><br/>
            
            <label for="categoria" id="categoria">Categoria</label><br/>
            <input name="categoria" id="categoria"/><br/><br/>
            
            <label for="descripcion">Descripcion</label><br/>
            <textarea id="descripcion" name="descripcion"></textarea><br/><br/>
            
            <label for="cuota">Cuota Suscripcion</label><br/>
            <input name="cuota" id="cuota"/><br/><br/>
            
            <label for="reaccion">Reacciones</label>
            <select name="reaccion">
                <option value="permitir">Permitir</option>
                <option value="bloquear">Bloquear</option>
            </select><br/><br/>
            
            <label for="comentarios">Comentarios</label>
            <select name="comentarios">
                <option value="permitir">Permitir</option>
                <option value="bloquera">Bloquear</option>
            </select><br/><br/>
            
            <label for="archivo">Archivo PDF</label><br/>
            <input type="file" name="archivo" id="archivo"/><br/><br/>
            
            <button type="submit" name="accion" value="NuevaRevista">Enviar Revista</button>
        </form>
        <hr/>
        <a href="inicioEditor.jsp">Inicio</a>
    </center>

</body>
</html>
