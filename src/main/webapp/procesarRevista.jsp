<%-- 
    Document   : procesarRevista
    Created on : Sep 26, 2019, 11:44:33 PM
    Author     : cesar31
--%>

<%@page import="objeto.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Revista revista = (Revista) session.getAttribute("revista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/estiloProcesarRevista.css" rel="stylesheet" type="text/css"/>
        <br/>
        <title>Procesar - <%=revista.getTituloRevista()%></title>
    </head>
    <body>

        <h1 align="center">Procesar Revista: <%=revista.getTituloRevista()%></h1>
        <br/>
        <form class="formulario" action="ControladorRevista" method="post">
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">idRevista</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getIdRevista()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Titulo Revista</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getTituloRevista()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Categoria</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getCategoria()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Descripcion</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getDescripcion()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Edicion</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getEdicion()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Cuota de Suscripción</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getCuotaSuscripcion()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Reacciones</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.isReaccion()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Comentarios</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.isComentarios()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="colFormLabelLg" class="col-sm-2 col-form-label col-form-label-lg">Editor</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" placeholder="<%=revista.getIdUsuario()%>" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label for="cuota" class="col-sm-2 col-form-label col-form-label-lg">Cuota por día</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" name="cuota" placeholder="Cuota por dia"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="porcentaje" class="col-sm-2 col-form-label col-form-label-lg">% Por Suscripcio(Pagina)</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" id="colFormLabelLg" name="porcentaje" placeholder="Porcentaje 0 - 100"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="fecha" class="col-sm-2 col-form-label col-form-label-lg">Fecha de Publicacion</label>
                <div class="col-sm-10">
                    <input class="form-control form-control-lg" type="date" id="colFormLabelLg" name="fecha"/>
                </div>
            </div>

            <hr/>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-2" name="accion" value="ProcesarRevista">Procesar</button>
            </div>
            <hr/>
        </form>


        <script src="js/jquery-3.3.1.slim.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
