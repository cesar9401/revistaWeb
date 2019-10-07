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
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="action">
            <ul class="nav nav-pills">
                <li class="nav-item">
                    <a class="nav-link" href="inicioEditor.jsp"><%=session.getAttribute("user")%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="ControladorRevista?accion=newRevista">Nueva Revista</a>
                </li>
                <li class="nav-item dropdown">

                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Acciones</a>
                    <div class="dropdown-menu">
                        <!--Enlaces aqui-->

                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="ControladorUsuario?action=CerrarSesion">Cerrar Sesion</a>
                    </div>
                </li>

            </ul>
        </div>

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
    </center>


    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
