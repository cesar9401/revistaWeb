<%-- 
    Document   : registroUsuario
    Created on : Sep 13, 2019, 11:26:44 PM
    Author     : cesar31
--%>


<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>Registrarme</title>
    </head>
    <body>
        <a href="index.jsp" title="Inicio"><img src="img/index.png" width="100" alt="Inicio" title="Inicio"/></a>

    <center>

        <h1>Registrarme</h1>
        <form action="ControladorUsuario" method="post" enctype="multipart/form-data">
            <label for="nombre">Nombres</label><br/>
            <input id="nombre" name="nombre"/><br/><br/>
            
            <label for="apellido">Apellidos</label><br/>
            <input id="apellido" name="apellido"/><br/><br/>
            
            <label for="usuario">Usuario</label><br/>
            <input id="usuario" name="usuario"/><br/><br/>

            <label for="email">Email</label><br/>
            <input type="email" id="email" name="email"/><br/><br/>
            
            <label for="tipoCuenta">Tipo de Cuenta </label>
            <select name="tipoCuenta">
                <option>Usuario</option>
                <option>Editor</option>
            </select><br/><br/>

            <label for="fecha">Fecha de Nacimiento</label><br/>
            <input type="date" id="fecha" name="fecha"/><br/><br/>

            <label for="nacionalidad">Nacionalidad</label><br/>
            <input id="nacionalidad" name="nacionalidad"/><br/><br/>

            <label for="sexo">Sexo</label>
            <select name="sexo">
                <option value="masculino">Masculino</option>
                <option value="femenino">Feminino</option>
            </select><br/><br/>

            <label for="descripcion">Descripcion</label><br/>
            <textarea id="descripcion" name="descripcion" ></textarea><br/><br/>

            <label for="hobbies">Hobbies</label><br/>
            <textarea id="hobbies" name="hobbies" ></textarea><br/><br/>

            <label for="fotografia">Fotografia</label><br/>
            <input type="file" id="fotografia" name="fotografia"/><br/><br/>

            <label for="pass">Contraseña</label><br/>
            <input id="pass" name="pass" type="password"/><br/><br/>

            <button type="submit" name="accion" value="Registrar">Registrame</button>
        </form>
            <hr/>
            <a href="index.jsp">Inicio</a>
    </center>
    
    

    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
