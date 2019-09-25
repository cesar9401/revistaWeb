<%-- 
    Document   : metodoDePago
    Created on : Sep 22, 2019, 12:25:32 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Metodo de Pago </title>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <center>
        <h1>Informacion Metodo de Pago</h1>
        <p>Antes de continuar, necesitamos que ingrese un metodo de pago</p>
        <form action="ControladorRevista" method="post">
            <h1>Metodo de Pago</h1><hr/>

            <label for="nombre">Nombre del titular</label><br/>
            <input name="nombre" id="nombre" placeholder="Como aparece en la tarjeta"/><br/><br/>

            <label for="numero">Número de Tarjeta</label><br/>
            <input name="numero" id="numero" maxlength="16"/><br/><br/>

            <label>Fecha de Expiración</label><br/>
            <label for="mes">Mes </label>
            <input name="mes" id="mes" placeholder="Mes: 01-12" maxlength="2"><br/>
            <label for="year">Año </label>
            <input name="year" id="year" placeholder="Año: 2019 - 2050" maxlength="4"/><br/><br/>

            <label for="codigo">Código de Seguridad</label><br/>
            <input name="codigo" id="codigo" placeholder="3 digitos" maxlength="3"/><br/><br/>

            <button type="submit" name="accion" value="RegistrarMetodo">Añadir Metodo de Pago</button>
            
        </form>
    </center>

</body>
</html>
