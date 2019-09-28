package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objeto.Revista;

/**
 *
 * @author cesar31
 */
public class RevistaDAO {

    Conexion conexion = new Conexion();

    /*
        Metodo para obtener el siguiente id para cada revista en la tabla revista
     */
    public int getId() {
        int id = 1;
        try {
            String query = "SELECT idRevista FROM revista";
            Statement revista = conexion.conectar().createStatement();
            ResultSet r = revista.executeQuery(query);
            while (r.next()) {
                if (id <= r.getInt("idRevista")) {
                    id = r.getInt("idRevista") + 1;
                }
            }
        } catch (SQLException ex) {

        }
        return id;
    }

    /*
        Metodo para obtener la version de la revista, segun la tabla edicionesRevistas
     */
    public int getEdicion(int idRevista) {
        int edicion = 1;
        try {
            conexion.conectar();
            String query = "SELECT edicionRevista FROM edicionesRevistas WHERE revista_idRevista = ?";
            PreparedStatement getEdicion = conexion.conectar().prepareStatement(query);
            getEdicion.setInt(1, idRevista);
            ResultSet r = getEdicion.executeQuery();
            while (r.next()) {
                if (edicion <= r.getInt("edicionRevista")) {
                    edicion = r.getInt("edicionRevista") + 1;
                }
            }
            conexion.desconectar();
        } catch (SQLException ex) {

        }

        return edicion;
    }

    /*
        Metodo para enviar la revista en formato pdf asi como su informacion a la base de datos
     */
    public void setRevista(Revista revista) {
        try {
            conexion.conectar();
            try {
                conexion.conectar().setAutoCommit(false);
                String query = "INSERT INTO revista(idRevista, tituloRevista, archivoPdf, categoria, descripcion, edicion, cuotaSuscripcion, "
                        + "reaccion, comentarios, bloquear, usuario_idUsuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement setRevista = conexion.conectar().prepareStatement(query);
                setRevista.setInt(1, revista.getIdRevista());
                setRevista.setString(2, revista.getTituloRevista());
                //setRevista.setBytes(3, revista.getArchivoPDF());
                setRevista.setBlob(3, revista.getRevistaPDF());
                setRevista.setString(4, revista.getCategoria());
                setRevista.setString(5, revista.getDescripcion());
                setRevista.setInt(6, revista.getEdicion());
                setRevista.setDouble(7, revista.getCuotaSuscripcion());
                setRevista.setBoolean(8, revista.isReaccion());
                setRevista.setBoolean(9, revista.isComentarios());
                setRevista.setBoolean(10, revista.isBloquear());
                setRevista.setString(11, revista.getIdUsuario());
                setRevista.executeUpdate();

                //Guardar la edicion de la revista en la tabla edicionesRevistas
                int idEdicion = getidEdicion();
                String queryEdicion = "INSERT INTO edicionesRevistas(idEdicion, revista_idRevista, edicionRevista, archivoPdf) VALUES(?, ?, ?, ?)";
                PreparedStatement setEdicion = conexion.conectar().prepareStatement(queryEdicion);
                setEdicion.setInt(1, idEdicion);
                setEdicion.setInt(2, revista.getIdRevista());
                setEdicion.setInt(3, revista.getEdicion());
                //setEdicion.setBytes(4, revista.getArchivoPDF());
                setEdicion.setBlob(4, revista.getRevistaPDF());
                setEdicion.executeUpdate();

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
        Metodo para devolver un List<Revista> con todas las revistas de algun editor, recibe como parametro el idUsuario
        del editor.
     */
    public List<Revista> getRevistasByEditor(String idUsuario) {
        List<Revista> revistas = new ArrayList<>();
        Revista tmp;
        try {
            String query = "SELECT * FROM revista WHERE usuario_idUsuario = ?";
            PreparedStatement getRevistas = conexion.conectar().prepareStatement(query);
            getRevistas.setString(1, idUsuario);
            ResultSet r = getRevistas.executeQuery();
            while (r.next()) {
                //write your code here
                int id = r.getInt("idRevista");
                String titulo = r.getString("tituloRevista");
                byte[] pdf = r.getBytes("archivoPdf");
                String categoria = r.getString("categoria");
                String descripcion = r.getString("descripcion");
                int edicion = r.getInt("edicion");
                double cuota = r.getDouble("cuotaSuscripcion");
                boolean reaccion = r.getBoolean("reaccion");
                boolean comentarios = r.getBoolean("comentarios");
                java.sql.Date date = r.getDate("fechaPubl");
                int suscripciones = r.getInt("suscripciones");
                boolean bloquear = r.getBoolean("bloquear");
                String user = r.getString("usuario_idUsuario");

                tmp = new Revista(titulo, categoria, descripcion, cuota, reaccion, comentarios, user);
                tmp.setIdRevista(id);
                tmp.setArchivoPDF(pdf);
                tmp.setEdicion(edicion);
                tmp.setFechaPublicacion(date);
                tmp.setSuscripciones(suscripciones);
                tmp.setBloquear(bloquear);

                revistas.add(tmp);
            }
        } catch (SQLException ex) {

        }

        return revistas;
    }

    public List<Revista> getRevistas() {
        List<Revista> revistas = new ArrayList<>();
        Revista tmp;
        try {
            String query = "SELECT * FROM revista";
            Statement getRevistas = conexion.conectar().createStatement();
            ResultSet r = getRevistas.executeQuery(query);
            while (r.next()) {
                //write your code here
                int id = r.getInt("idRevista");
                String titulo = r.getString("tituloRevista");
                byte[] pdf = r.getBytes("archivoPdf");
                String categoria = r.getString("categoria");
                String descripcion = r.getString("descripcion");
                int edicion = r.getInt("edicion");
                double cuota = r.getDouble("cuotaSuscripcion");
                boolean reaccion = r.getBoolean("reaccion");
                boolean comentarios = r.getBoolean("comentarios");
                java.sql.Date date = r.getDate("fechaPubl");
                int suscripciones = r.getInt("suscripciones");
                boolean bloquear = r.getBoolean("bloquear");
                String user = r.getString("usuario_idUsuario");

                tmp = new Revista(titulo, categoria, descripcion, cuota, reaccion, comentarios, user);
                tmp.setIdRevista(id);
                tmp.setArchivoPDF(pdf);
                tmp.setEdicion(edicion);
                tmp.setFechaPublicacion(date);
                tmp.setSuscripciones(suscripciones);
                tmp.setBloquear(bloquear);

                revistas.add(tmp);
            }
        } catch (SQLException ex) {

        }

        return revistas;
    }

    public Revista getRevistaById(int idRevista) {
        Revista tmp = null;
        try {
            String query = "SELECT * FROM revista WHERE idRevista = ?";
            PreparedStatement getRevista = conexion.conectar().prepareStatement(query);
            getRevista.setInt(1, idRevista);
            ResultSet r = getRevista.executeQuery();
            if (r.next()) {
                int id = r.getInt("idRevista");
                String titulo = r.getString("tituloRevista");
                byte[] pdf = r.getBytes("archivoPdf");
                String categoria = r.getString("categoria");
                String descripcion = r.getString("descripcion");
                int edicion = r.getInt("edicion");
                double cuota = r.getDouble("cuotaSuscripcion");
                boolean reaccion = r.getBoolean("reaccion");
                boolean comentarios = r.getBoolean("comentarios");
                java.sql.Date date = r.getDate("fechaPubl");
                int suscripciones = r.getInt("suscripciones");
                boolean bloquear = r.getBoolean("bloquear");
                String user = r.getString("usuario_idUsuario");

                tmp = new Revista(titulo, categoria, descripcion, cuota, reaccion, comentarios, user);
                tmp.setIdRevista(id);
                tmp.setArchivoPDF(pdf);
                tmp.setEdicion(edicion);
                tmp.setFechaPublicacion(date);
                tmp.setSuscripciones(suscripciones);
                tmp.setBloquear(bloquear);

            }
        } catch (SQLException ex) {

        }

        return tmp;
    }

    /*
        Metodo para obtener el idCorrespondiente para cada version de cada revista en la tabla edicionesRevistas
     */
    public int getidEdicion() {
        int idEdicion = 1;
        try {
            conexion.conectar();
            String query = "SELECT idEdicion FROM edicionesRevistas";
            PreparedStatement getEdicion = conexion.conectar().prepareStatement(query);
            ResultSet r = getEdicion.executeQuery();
            while (r.next()) {
                if (idEdicion <= r.getInt("idEdicion")) {
                    idEdicion = r.getInt("idEdicion") + 1;
                }
            }
            conexion.desconectar();
        } catch (SQLException ex) {

        }

        return idEdicion;
    }
}
