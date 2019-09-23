
package objeto;

import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class MetodosDePago {
    
    private int tarjetaCredito;
    private String nombre;
    private Date vencimiento;
    private String codigoSeguridad;
    private String idUsuario;

    public MetodosDePago(int tarjetaCredito, String nombre, Date vencimiento, String codigoSeguridad, String idUsuario) {
        this.tarjetaCredito = tarjetaCredito;
        this.nombre = nombre;
        this.vencimiento = vencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.idUsuario = idUsuario;
    }

    public int getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(int tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
