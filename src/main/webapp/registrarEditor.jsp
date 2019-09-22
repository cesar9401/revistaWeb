<%-- 
    Document   : registrarEditor
    Created on : Sep 21, 2019, 1:11:00 PM
    Author     : cesar31
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Editor</title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <h1>Registrarme</h1>
        <form action="ControladorUsuario" method="post">
            <label for="usuario">Usuario:</label><br/>
            <input id="usuario" name="usuario"/><br/><br/>

            <label for="email">Email:</label><br/>
            <input type="email" id="email" name="email"/><br/><br/>

            <label for="fecha">Fecha de Nacimiento</label><br/><br/>
            <label for="mes">Mes</label>
            <select name="mes">
                <%for (int i = 0; i < 12; i++) {
                %>
                <option>  <%=i + 1%> </option>
                <%
                    }
                %>
            </select>

            <label for="dia">Dia</label>
            <select name="dia">
                <%for (int i = 1; i <= 31; i++) {
                %>
                <option><%=i%></option>
                <%
                    }
                %>
            </select>

            <label for="year">Año</label>
            <select name="year">
                <%  Calendar hoy = Calendar.getInstance();
                    int year = hoy.get(Calendar.YEAR);
                    for (int i = year - 80; i <= year - 18; i++) {
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select><br/><br/>

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
            <input id="fotografia" name="fotografia"/><br/><br/>

            <label for="pass">Contraseña</label><br/>
            <input id="pass" name="pass" type="password"/><br/><br/>

            <button type="submit" name="accion" value="RegistrarEditor">Registrarme</button>
        </form>
    </center>
</body>
</html>
