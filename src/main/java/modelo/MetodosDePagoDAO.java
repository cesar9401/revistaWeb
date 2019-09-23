
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cesar31
 */
public class MetodosDePagoDAO {
    
    Conexion conexion = new Conexion();
    
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
