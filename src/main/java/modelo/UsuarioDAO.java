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

    public UsuarioDAO() {
    }

    /*
        Metodo para ingresar un usuario a la base de datos, recibe como parametro un objeto de
        tipo Usuario con la informacion necesaria
     */
    public void setUsuario(Usuario user) {
        Usuario tmp = user;
        try {
            conexion.conectar();
            String query = "INSERT INTO usuario (idUsuario, email, nombres, apellidos, ubicacion, fechaNac, sexo, password, hobbies, descripcion, foto, editor) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement setUsuario = conexion.conectar().prepareStatement(query);
            setUsuario.setString(1, tmp.getIdUsuario());
            setUsuario.setString(2, tmp.getEmail());
            setUsuario.setString(3, tmp.getNombres());
            setUsuario.setString(4, tmp.getApellidos());
            setUsuario.setString(5, tmp.getUbicacion());
            setUsuario.setDate(6, tmp.getFechaNac());
            setUsuario.setString(7, tmp.getSexo());
            setUsuario.setString(8, tmp.getPassword());
            setUsuario.setString(9, tmp.getHobbies());
            setUsuario.setString(10, tmp.getDescripcion());
            //setUsuario.setBytes(11, tmp.getFoto());
            setUsuario.setBlob(11, tmp.getImagen());
            setUsuario.setBoolean(12, tmp.isEditor());
            setUsuario.executeUpdate();
            conexion.desconectar();
        } catch (SQLException ex) {

        }
    }

    /*
        Metodo para ingresar un administrador a la base de datos, recibe un objeto de tipo Usuario como parametro
     */
    public void setAdministrador(Usuario admin) {
        try {
            conexion.conectar();
            String query = "INSERT INTO administrador(usuarioAdmin, password, email) VALUES(?, ?, ?)";
            PreparedStatement setAdmin = conexion.conectar().prepareStatement(query);
            setAdmin.setString(1, admin.getIdUsuario());
            setAdmin.setString(2, admin.getPassword());
            setAdmin.setString(3, admin.getEmail());
            setAdmin.executeUpdate();
            conexion.desconectar();
        } catch (SQLException ex) {

        }
    }

    public Usuario getAdministrador(String idUsuario, String password) {
        Usuario admin = null;
        try {
            String query = "SELECT * FROM administrador WHERE usuarioAdmin = ? AND password = ?";
            PreparedStatement getAdmin = conexion.conectar().prepareStatement(query);
            getAdmin.setString(1, idUsuario);
            getAdmin.setString(2,password);
            ResultSet r = getAdmin.executeQuery();
            if (r.next()) {
                admin = new Usuario(r.getString("usuarioAdmin"), r.getString("email"), r.getString("password"));
            }
        } catch (SQLException ex) {

        }

        return admin;
    }

    /*
        Metodo para obtener la informacion de un usuario, recibe los parametros usuario y password
        y devuelve un objeto de tipo Usuario.
     */
    public Usuario getUsuario(String usuario, String password) {
        Usuario tmp = null;
        try {
            conexion.conectar();
            String query = "SELECT * FROM usuario WHERE idUsuario = ? AND password = ?";
            PreparedStatement getUsuario = conexion.conectar().prepareStatement(query);
            getUsuario.setString(1, usuario);
            getUsuario.setString(2, password);
            ResultSet r = getUsuario.executeQuery();
            if (r.next()) {
                tmp = new Usuario(r.getString("idUsuario"), r.getString("email"), r.getString("ubicacion"), r.getDate("fechaNac"), r.getString("sexo"), r.getString("password"), r.getString("hobbies"), r.getString("descripcion"), r.getBoolean("editor"));
                tmp.setFoto(r.getBytes("foto"));
                tmp.setNombres(r.getString("nombres"));
                tmp.setApellidos(r.getString("apellidos"));
            } else {
                tmp = null;
            }
        } catch (SQLException ex) {

        }
        return tmp;
    }

    /*
        Metodo para obtener la fotografia de perfil de algun usuario, recibe como parametros el idUsuario
        y un objeto de tipo response para poder imprimir sobre la pagina jsp/html.
     */
    public void getImg(String idUsuario, HttpServletResponse response) {
        String query = "SELECT * FROM usuario WHERE idUsuario = ?";
        response.setContentType("image/*");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            conexion.conectar();
            PreparedStatement getImg = conexion.conectar().prepareStatement(query);
            getImg.setString(1, idUsuario);
            ResultSet r = getImg.executeQuery();
            if (r.next()) {
                inputStream = new ByteArrayInputStream(r.getBytes("foto"));
            }

            try {
                outputStream = response.getOutputStream();
            } catch (Exception e) {

            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
            conexion.desconectar();
        } catch (Exception ex) {

        }
    }
}
