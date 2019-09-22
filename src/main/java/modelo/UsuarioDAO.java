
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
    
    public UsuarioDAO(){
    }
    
    public void setUsuario(Usuario user){
        Usuario tmp = user;
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
            setUsuario.setBoolean(10, tmp.isEditor());
            setUsuario.executeUpdate();
            conexion.desconectar();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public Usuario getUsuario(String usuario, String password){
        Usuario tmp = null;
        try{
            conexion.conectar();
            String query = "SELECT * FROM usuario WHERE idUsuario = ? AND password = ?";
            PreparedStatement getUsuario = conexion.conectar().prepareStatement(query);
            getUsuario.setString(1, usuario);
            getUsuario.setString(2, password);
            ResultSet r = getUsuario.executeQuery();
            if(r.next()){
                tmp = new Usuario(r.getString(1), r.getString(2), r.getString(3), r.getDate(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getBoolean(10));
                tmp.setFoto(r.getBytes(9));
            }else{
                tmp = null;
            }
        }catch(SQLException ex){
        
        }
        return tmp;
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
