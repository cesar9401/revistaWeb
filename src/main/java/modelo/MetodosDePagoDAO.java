
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objeto.MetodosDePago;

/**
 *
 * @author cesar31
 */
public class MetodosDePagoDAO {
    
    Conexion conexion = new Conexion();
    
    /*
        Metodo para guardar un nuevo metodo de pago en la base de datos, recibe como parametro
        un objeto de tipo MetodosDePago con la informacion necesaria.
    */
    public void setMetodoDePago(MetodosDePago tarjeta){
        try{
            conexion.conectar();
            String query = "INSERT INTO metodosPago(tarjetaCredito, nombre, vencimiento, codigoSeguridad, usuario_idUsuario) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement setMetodo = conexion.conectar().prepareStatement(query);
            setMetodo.setLong(1, tarjeta.getTarjetaCredito());
            setMetodo.setString(2, tarjeta.getNombre());
            setMetodo.setDate(3, tarjeta.getVencimiento());
            setMetodo.setString(4, tarjeta.getCodigoSeguridad());
            setMetodo.setString(5, tarjeta.getIdUsuario());
            setMetodo.executeUpdate();
            conexion.desconectar();
        }catch(SQLException ex){
        
        }
    }
    
    /*
        Metodo para obtener la informacion del metodo de pago del usuario
    */
    public MetodosDePago getMetodoDePago(String idUsuario){
        MetodosDePago tarjeta = null;
        try{
            conexion.conectar();
            String query = "SELECT * FROM metodosPago WHERE usuario_idUsuario = ?";
            PreparedStatement getMetodo = conexion.conectar().prepareStatement(query);
            getMetodo.setString(1, idUsuario);
            ResultSet r = getMetodo.executeQuery();
            if(r.next()){
                tarjeta = new MetodosDePago(r.getLong("tarjetaCredito"), r.getString("nombre"), r.getDate("vencimiento"), r.getString("codigoSeguridad"), idUsuario);
            }
            conexion.desconectar();
        }catch(SQLException ex){
            
        }
        
        return tarjeta;
    }
    
    /*
        Metodo para verficar si determinado usuario ya cuenta con un metodo de pago
    */
    public boolean verificarMetodo(String idUsuario){
        boolean metodo = false;
        try{
            conexion.conectar();
            String query = "SELECT usuario_idUsuario FROM metodosPago WHERE usuario_idUsuario = ?";
            PreparedStatement getMetodo = conexion.conectar().prepareStatement(query);
            getMetodo.setString(1, idUsuario);
            ResultSet r = getMetodo.executeQuery();
            if(r.next()){
                metodo = true;
            }
            conexion.desconectar();
        }catch(SQLException ex){
        
        }
        
        return metodo;
    }
}
