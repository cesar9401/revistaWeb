
package objeto;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class Revista implements Serializable{
    
    private int idRevista;
    private String tituloRevista;
    private byte[] archivoPDF;
    private InputStream revistaPDF;
    private String categoria;
    private String descripcion;
    private int edicion;
    private double cuotaSuscripcion;
    private boolean reaccion;
    private boolean comentarios;
    private Date fechaPublicacion;
    private int suscripciones;
    private boolean bloquear;
    private String idUsuario;
    
    //Datos para sucripciones
    private int idSuscripcion;
    private String idSuscriptor;
    private Date fechaSuscripcion;
    
    //Constructor para Reporte de suscripciones
    public Revista(int idRevista, String tituloRevista, String categoria, int edicion, double cuotaSuscripcion, Date fechaPublicacion, int suscripciones, int idSuscripcion, String idSuscriptor, Date fechaSuscripcion) {
        this.idRevista = idRevista;
        this.tituloRevista = tituloRevista;
        this.categoria = categoria;
        this.edicion = edicion;
        this.cuotaSuscripcion = cuotaSuscripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.suscripciones = suscripciones;
        this.idSuscripcion = idSuscripcion;
        this.idSuscriptor = idSuscriptor;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    //Constructor para revista
    public Revista(String tituloRevista, String categoria, String descripcion, double cuotaSuscripcion, boolean reaccion, boolean comentarios, String idUsuario) {
        this.tituloRevista = tituloRevista;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.cuotaSuscripcion = cuotaSuscripcion;
        this.reaccion = reaccion;
        this.comentarios = comentarios;
        this.idUsuario = idUsuario;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getTituloRevista() {
        return tituloRevista;
    }

    public void setTituloRevista(String tituloRevista) {
        this.tituloRevista = tituloRevista;
    }

    public byte[] getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(byte[] archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

    public InputStream getRevistaPDF() {
        return revistaPDF;
    }

    public void setRevistaPDF(InputStream revistaPDF) {
        this.revistaPDF = revistaPDF;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public double getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }

    public void setCuotaSuscripcion(double cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }

    public boolean isReaccion() {
        return reaccion;
    }

    public void setReaccion(boolean reaccion) {
        this.reaccion = reaccion;
    }

    public boolean isComentarios() {
        return comentarios;
    }

    public void setComentarios(boolean comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(int suscripciones) {
        this.suscripciones = suscripciones;
    }

    public boolean isBloquear() {
        return bloquear;
    }

    public void setBloquear(boolean bloquear) {
        this.bloquear = bloquear;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(String idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }
}
