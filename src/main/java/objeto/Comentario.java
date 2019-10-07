package objeto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class Comentario implements Serializable{

    private int idComentario;
    private int idSuscripcion;
    private int idRevista;
    private String idUsuario;
    private String comentario;
    private Date date;

    public Comentario(int idSuscripcion, String comentario, int idRevista, String idUsuario, Date date) {
        this.idSuscripcion = idSuscripcion;
        this.comentario = comentario;
        this.idRevista = idRevista;
        this.idUsuario = idUsuario;
        this.date = date;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idSuscripcion=" + idSuscripcion + ", comentario=" + comentario + ", idRevista=" + idRevista + ", idUsuario=" + idUsuario + ", date=" + date + '}';
    }
}
