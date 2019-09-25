
package objeto;

import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class Comentario {
    
    private int idComentario;
    private int idSuscripcion;
    private String comentario;
    private Date fecha;
    private int idRecursivo;
    private int edicionRevista;

    public Comentario(int idComentario, int idSuscripcion, String comentario, Date fecha, int idRecursivo, int edicionRevista) {
        this.idComentario = idComentario;
        this.idSuscripcion = idSuscripcion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.idRecursivo = idRecursivo;
        this.edicionRevista = edicionRevista;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdRecursivo() {
        return idRecursivo;
    }

    public void setIdRecursivo(int idRecursivo) {
        this.idRecursivo = idRecursivo;
    }

    public int getEdicionRevista() {
        return edicionRevista;
    }

    public void setEdicionRevista(int edicionRevista) {
        this.edicionRevista = edicionRevista;
    }
}
