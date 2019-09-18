
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cesar31
 */
public class Conexion {
    
    private Connection connection = null;
    private final String USER = "cesar9401";
    private final String PASS = "Huevos94C";
    private final String URL = "jdbc:mysql://localhost:3306/revistaWeb";
    
    
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(URL, USER, PASS);
            System.out.println("conectado: " + connection.getCatalog());
        }catch(SQLException ex){
            System.out.println("Fallo la conexion");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
    public void desconectar(){
        try{
            connection.close();
            System.out.println("Desconectado: " + connection.getCatalog());
        }catch(SQLException ex){
            System.out.println("No se pudo cerrar la conexion");
        }
    }
}
