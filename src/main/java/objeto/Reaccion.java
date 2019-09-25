
package objeto;

import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class Reaccion {
    
    private int idReaccion;
    private int idSuscripcion;
    private boolean tipoReaccion;
    private Date fecha;
    private int edicioRevista;

    public Reaccion(int idReaccion, int idSuscripcion, boolean tipoReaccion, Date fecha, int edicioRevista) {
        this.idReaccion = idReaccion;
        this.idSuscripcion = idSuscripcion;
        this.tipoReaccion = tipoReaccion;
        this.fecha = fecha;
        this.edicioRevista = edicioRevista;
    }

    public int getIdReaccion() {
        return idReaccion;
    }

    public void setIdReaccion(int idReaccion) {
        this.idReaccion = idReaccion;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public boolean isTipoReaccion() {
        return tipoReaccion;
    }

    public void setTipoReaccion(boolean tipoReaccion) {
        this.tipoReaccion = tipoReaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEdicioRevista() {
        return edicioRevista;
    }

    public void setEdicioRevista(int edicioRevista) {
        this.edicioRevista = edicioRevista;
    }
}
