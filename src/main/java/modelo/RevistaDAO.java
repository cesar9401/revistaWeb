package modelo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import objeto.*;

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
            conexion.desconectar();
        } catch (SQLException ex) {

        }
        return id;
    }

    public int getIdRegistroUtilidades() {
        int idUtilidades = 1;
        try {
            conexion.conectar();
            String query = "SELECT idRegistroUtilidades FROM registroUtilidades";
            Statement registroUtilidades = conexion.conectar().createStatement();
            ResultSet r = registroUtilidades.executeQuery(query);
            while (r.next()) {
                if (idUtilidades <= r.getInt("idRegistroUtilidades")) {
                    idUtilidades = r.getInt("idRegistroUtilidades") + 1;
                }
            }

            conexion.desconectar();
        } catch (SQLException ex) {

        }

        return idUtilidades;
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

    /*
        Metodo para obtener un listado con todas las revistas en la base de datos
     */
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

    /*
        Metodo para obtener una revista segun el idRevista, devuelve un objeto del tipo Revista
     */
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
            Statement getEdicion = conexion.conectar().createStatement();
            ResultSet r = getEdicion.executeQuery(query);
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

    /*
        Metodo para regresar el archivo pdf que solicite el usuario y poder leerlo en pantalla
     */
    public void getPdf(int idRevista, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        InputStream inputStream = null;

        try {
            conexion.conectar();
            String query = "SELECT archivoPdf FROM revista WHERE idRevista = ?";
            PreparedStatement getPdf = conexion.conectar().prepareStatement(query);
            getPdf.setInt(1, idRevista);
            ResultSet r = getPdf.executeQuery();
            if (r.next()) {
                inputStream = new ByteArrayInputStream(r.getBytes("archivoPdf"));
            }
            int tamañoInput = inputStream.available();
            byte[] datosPDF = new byte[tamañoInput];
            inputStream.read(datosPDF, 0, tamañoInput);

            response.getOutputStream().write(datosPDF);
            inputStream.close();
            getPdf.close();
            r.close();
            conexion.desconectar();
        } catch (SQLException ex) {

        }
    }

    /*
        Metodo para procesar revistas, el administrador establece el porcentaje que le corresponde al editor
        y al dueño de la pagina asi como tambien la cuota para mantenimiento de la pagina
     */
    public void setProcesarRevista(Revista revista, Double porcentaje, Double cuotaDia, int idUtilidades) {
        try {
            conexion.conectar();
            try {
                conexion.conectar().setAutoCommit(false);
                String queryRevista = "UPDATE revista SET fechaPubl = ?, bloquear = ? WHERE idRevista = ?";
                PreparedStatement updateRevista = conexion.conectar().prepareStatement(queryRevista);
                updateRevista.setDate(1, revista.getFechaPublicacion());
                updateRevista.setBoolean(2, revista.isBloquear());
                updateRevista.setInt(3, revista.getIdRevista());
                updateRevista.executeUpdate();

                String queryUtilidades = "INSERT INTO registroUtilidades(idRegistroUtilidades, revista_idRevista, porcentaje, costoDia) VALUES(?, ?, ?, ?)";
                PreparedStatement setRegistro = conexion.conectar().prepareStatement(queryUtilidades);
                setRegistro.setInt(1, idUtilidades);
                setRegistro.setInt(2, revista.getIdRevista());
                setRegistro.setDouble(3, porcentaje);
                setRegistro.setDouble(4, cuotaDia);
                setRegistro.executeUpdate();

                String queryEdiciones = "UPDATE edicionesRevistas SET fechaPublicacion = ? WHERE revista_idRevista = ? AND edicionRevista = ?";
                PreparedStatement updateEdiciones = conexion.conectar().prepareCall(queryEdiciones);
                updateEdiciones.setDate(1, revista.getFechaPublicacion());
                updateEdiciones.setInt(2, revista.getIdRevista());
                updateEdiciones.setInt(3, revista.getEdicion());
                updateEdiciones.executeUpdate();

                conexion.conectar().commit();
                conexion.conectar().setAutoCommit(true);
            } catch (SQLException e) {
                conexion.conectar().rollback();
                System.out.println("error haciendo rollback");
            }
            conexion.desconectar();
        } catch (SQLException ex) {

        }
    }

    /*
        Obtener el porcentaje que le corresponde al dueño de la paginaWeb
     */
    public double getPorcentaje(int idRevista) {
        double porcentaje = 0;
        try {
            conexion.conectar();
            String query = "SELECT porcentaje from registroUtilidades WHERE revista_idRevista = ?";
            PreparedStatement getPorcentaje = conexion.conectar().prepareStatement(query);
            getPorcentaje.setInt(1, idRevista);
            ResultSet r = getPorcentaje.executeQuery();
            if (r.next()) {
                porcentaje = r.getDouble("porcentaje");
            }
            conexion.desconectar();
        } catch (SQLException ex) {

        }

        return porcentaje;
    }

    public List<Integer> getIdRevistas(String idUsuario) {
        List<Integer> idSuscripciones = new ArrayList<>();
        try {
            conexion.conectar();
            String query = "SELECT revista_idRevista FROM suscripcion WHERE usuario_idSuscriptor = ?";
            PreparedStatement getId = conexion.conectar().prepareStatement(query);
            getId.setString(1, idUsuario);
            ResultSet r = getId.executeQuery();
            while (r.next()) {
                int id = r.getInt("revista_idRevista");
                idSuscripciones.add(id);
            }

            conexion.desconectar();
        } catch (SQLException e) {

        }

        return idSuscripciones;
    }

    /*
        Metodo para obtener las revistas a las que este o no suscrito el usuario, recibe dos parametros
        el idUsuario y el booleano suscrito para determinar si desea las revistas a las que esta sucrito o no.
     */
    public List<Revista> getRevistasForUser(String idUsuario, boolean suscrito) {
        List<Revista> revistas = new ArrayList<>();

        List<Integer> suscripciones = getIdRevistas(idUsuario);
        List<Revista> totalRevistas = getRevistas();
        if (!suscripciones.isEmpty()) {
            for (int i = 0; i < totalRevistas.size(); i++) {
                int contador = 0;
                for (int j = 0; j < suscripciones.size(); j++) {

                    //Revistas donde idUsuario no este suscrito
                    if (!suscrito) {
                        if (totalRevistas.get(i).getIdRevista() != suscripciones.get(j)) {
                            contador++;
                        }
                        if (contador == suscripciones.size()) {
                            revistas.add(totalRevistas.get(i));
                        }
                    }

                    //Revistas donde idUsuario esta suscrito
                    if (totalRevistas.get(i).getIdRevista() == suscripciones.get(j) && suscrito) {
                        revistas.add(totalRevistas.get(i));
                        break;
                    }
                }
            }
        }

        if (suscripciones.isEmpty() && !suscrito) {
            revistas.addAll(totalRevistas);
        }

        return revistas;
    }

    public void setComentario(Comentario comentario) {
        try {
            conexion.conectar();
            String query = "INSERT INTO comentario(suscripcion_idSuscripcion, idRevista, idUsuario, comentario, fechaComentario) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement setComentarios = conexion.conectar().prepareStatement(query);
            setComentarios.setInt(1, comentario.getIdSuscripcion());
            setComentarios.setInt(2, comentario.getIdRevista());
            setComentarios.setString(3, comentario.getIdUsuario());
            setComentarios.setString(4, comentario.getComentario());
            setComentarios.setDate(5, comentario.getDate());
            setComentarios.executeUpdate();
            
            conexion.desconectar();
        } catch (SQLException ex) {

        }

    }

    public List<Comentario> getComentarios(int idMagazine) {
        List<Comentario> comentarios = new ArrayList<>();
        Comentario tmp = null;
        try {
            conexion.conectar();
            String query = "SELECT * FROM comentario WHERE idRevista = ?";
            PreparedStatement getComentarios = conexion.conectar().prepareStatement(query);
            getComentarios.setInt(1, idMagazine);
            ResultSet r = getComentarios.executeQuery();
            while(r.next()){
                int idComentario = r.getInt("idComentario");
                int idSuscripcion = r.getInt("suscripcion_idSuscripcion");
                int idRevista = r.getInt("idRevista");
                String idUsuario = r.getString("idUsuario");
                String comentario = r.getString("comentario");
                Date date = r.getDate("fechaComentario");
                
                tmp = new Comentario(idSuscripcion, comentario, idRevista, idUsuario, date);
                tmp.setIdComentario(idComentario);
                
                comentarios.add(tmp);
            }

            conexion.desconectar();
        } catch (SQLException ex) {

        }

        return comentarios;
    }
}
