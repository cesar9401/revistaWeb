package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import objeto.*;

/**
 *
 * @author cesar31
 */
public class Operaciones {

    Conexion conexion = new Conexion();

    public java.sql.Date getDate(String fecha) throws ParseException {
        java.sql.Date date = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        long fechaLong = formatoFecha.parse(fecha).getTime();
        date = new java.sql.Date(fechaLong);

        return date;
    }

    /*
        Para obntener el idSuscripcion que continua en la tabla suscripcion
    */
    public int getIdSuscripcion(){
        int idSuscripcion = 1;
        try{
            conexion.conectar();
            String query = "SELECT idSuscripcion FROM suscripcion";
            Statement getId = conexion.conectar().createStatement();
            ResultSet r = getId.executeQuery(query);
            while(r.next()){
                if(idSuscripcion <= r.getInt("idSuscripcion")){
                    idSuscripcion = r.getInt("idSuscripcion") + 1;
                }
            }
        }catch(SQLException e){
        
        }
        
        return idSuscripcion;
    }
    
    public int getIdSuscripcionForUser(int idRevista, String idUsuario){
        int idSuscripcion = 0;
        
        try{
            conexion.conectar();
            String query = "SELECT idSuscripcion FROM suscripcion WHERE revista_idRevista = ? AND usuario_idSuscriptor = ?";
            PreparedStatement getId = conexion.conectar().prepareStatement(query);
            getId.setInt(1, idRevista);
            getId.setString(2, idUsuario);
            ResultSet r = getId.executeQuery();
            if(r.next()){
                idSuscripcion = r.getInt("idSuscripcion");
            }
            
            conexion.desconectar();
        }catch(SQLException ex){
        
        }
        
        return idSuscripcion;
    }
    
    
    /*
        Metodo para guardar la informacion de una nueva suscripcion, asi como el registro de pago por la suscripcion
    */
    public void setSuscripcion(int idSuscripcion, double cuotaSuscripcion, double porcentaje, String idUsuario, int idRevista, java.sql.Date date) {

        try {
            conexion.conectar();
            try {
                conexion.conectar().setAutoCommit(false);
                
                //Insertar nueva suscripcion en la tabla suscripcion
                String querySuscripcion = "INSERT INTO suscripcion(idSuscripcion, revista_idRevista, usuario_idSuscriptor, fechaSusc) VALUES(?, ?, ?, ?)";
                PreparedStatement setSuscripcion = conexion.conectar().prepareStatement(querySuscripcion);
                setSuscripcion.setInt(1, idSuscripcion);
                setSuscripcion.setInt(2, idRevista);
                setSuscripcion.setString(3, idUsuario);
                setSuscripcion.setDate(4, date);
                setSuscripcion.executeUpdate();
                
                //Actualizar numero de suscripciones en la tabla revista
                String queryUpdate = "UPDATE revista SET suscripciones = suscripciones + ? WHERE idRevista = ?";
                PreparedStatement setUpdate = conexion.conectar().prepareStatement(queryUpdate);
                setUpdate.setInt(1, 1);
                setUpdate.setInt(2, idRevista);
                setUpdate.executeUpdate();

                //Establecemos cuotas para editor y web
                double cuotaWeb = (cuotaSuscripcion * porcentaje)/100;
                cuotaWeb = Math.round(cuotaWeb*100)/100d;
                double cuotaEditor = cuotaSuscripcion - cuotaWeb;
                
                //Guardar registro de pago en la tabla registroIngresos
                String queryRegistro = "INSERT INTO registroIngresos(suscripcion_idSuscripcion, fechaCobro, cuotaSuscripcion, porcentajeEditor, porcentajePagina) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement setRegistro = conexion.conectar().prepareStatement(queryRegistro);
                setRegistro.setInt(1, idSuscripcion);
                setRegistro.setDate(2, date);
                setRegistro.setDouble(3, cuotaSuscripcion);
                setRegistro.setDouble(4, cuotaEditor);
                setRegistro.setDouble(5, cuotaWeb);
                setRegistro.executeUpdate();
                
                //Actualizar registro de utilidades/ingresos en la tabla registroUtilidades
                String queryUtilidades = "UPDATE registroUtilidades SET totalSuscripcion = totalSuscripcion + ?, totalSuscripcionEditor = totalSuscripcionEditor + ?, totalSuscripcionPagina = totalSuscripcionPagina + ? WHERE revista_idRevista = ?";
                PreparedStatement setUtilidades = conexion.conectar().prepareStatement(queryUtilidades);
                setUtilidades.setDouble(1, cuotaSuscripcion);
                setUtilidades.setDouble(2, cuotaEditor);
                setUtilidades.setDouble(3, cuotaWeb);
                setUtilidades.setInt(4, idRevista);
                setUtilidades.executeUpdate();
                
                conexion.conectar().commit();
                conexion.conectar().setAutoCommit(true);
            } catch (SQLException e) {
                conexion.conectar().rollback();
            }

            conexion.desconectar();
        } catch (SQLException ex) {

        }
    }
    
    /*
        Metodo para obtener los datos para el reporte de Suscripciones.
    */
    public List<Revista> getRevistaForReportes(String idUsuario){
        List<Revista> revistas = new ArrayList<>();
        Revista tmp = null;
        try{
            String query = "SELECT idRevista, tituloRevista, categoria, edicion, cuotaSuscripcion, fechaPubl, suscripciones, usuario_idUsuario, idSuscripcion, usuario_idSuscriptor, fechaSusc FROM revista INNER JOIN suscripcion ON idRevista = revista_idRevista WHERE usuario_idUsuario = ?";
            PreparedStatement getRevista = conexion.conectar().prepareStatement(query);
            getRevista.setString(1, idUsuario);
            ResultSet r = getRevista.executeQuery();
            while(r.next()){
                int idRevista = r.getInt("idRevista");
                String tituloR = r.getString("tituloRevista");
                String categoria = r.getString("categoria");
                int edicion = r.getInt("edicion");
                double cuotaSuscripcion = r.getDouble("cuotaSuscripcion");
                Date fechaPubl = r.getDate("fechaPubl");
                int suscripciones = r.getInt("suscripciones");
                String idEditor = r.getString("usuario_idUsuario");
                int idSuscripcion = r.getInt("idSuscripcion");
                String idSuscriptor = r.getString("usuario_idSuscriptor");
                Date fechaSusc = r.getDate("fechaSusc");
                
                tmp = new Revista(idRevista, tituloR, categoria, edicion, cuotaSuscripcion, fechaPubl, suscripciones, idSuscripcion, idSuscriptor, fechaSusc);
                revistas.add(tmp);
            }
        }catch(SQLException e){
        
        
        }
        
        return revistas;
    }
    
}
