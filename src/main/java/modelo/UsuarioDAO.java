
package modelo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import objeto.Usuario;

/**
 *
 * @author cesar31
 */
public class UsuarioDAO {
    
    private Conexion conexion = new Conexion();
    private Usuario tmp;
    
    public UsuarioDAO(Usuario tmp){
        this.tmp = tmp;
    }
    
    public void setUsuario(){
        try{
            conexion.conectar();
            String query = "INSERT INTO usuario (idUsuario, email, ubicacion, fechaNac, sexo, password, hobbies, descripcion, foto, editor) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement setUsuario = conexion.conectar().prepareStatement(query);
            setUsuario.setString(1, tmp.getIdUsuario());
            setUsuario.setString(2, tmp.getEmail());
            setUsuario.setString(3, tmp.getUbicacion());
            setUsuario.setDate(4, tmp.getFechaNac());
            setUsuario.setString(5, tmp.getSexo());
            setUsuario.setString(6, tmp.getPassword());
            setUsuario.setString(7, tmp.getHobbies());
            setUsuario.setString(8, tmp.getDescripcion());
            setUsuario.setBytes(9, tmp.getFoto());
            setUsuario.setBoolean(10, false);
            setUsuario.executeUpdate();
            conexion.desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void listarImg(String idUsuario, HttpServletResponse response){
        String query = "SELECT * FROM usuario WHERE idUsuario = ?";
        response.setContentType("image/*");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            conexion.conectar();
            PreparedStatement setImg = conexion.conectar().prepareStatement(query);
            setImg.setString(1, idUsuario);
            ResultSet r = setImg.executeQuery();
            if(r.next()){
                inputStream = new ByteArrayInputStream(r.getBytes("foto"));
            }
            
            try{
                outputStream = response.getOutputStream();
            }catch(Exception e){
                
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while((i=bufferedInputStream.read()) != -1){
                bufferedOutputStream.write(i);
            }
            conexion.desconectar();
        }catch(Exception ex){
        
        }
    }
}
