
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author cesar31
 */
public class Operaciones {
    
    public java.sql.Date getDate(String fecha) throws ParseException{
        java.sql.Date date = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        long fechaLong = formatoFecha.parse(fecha).getTime();
        date = new java.sql.Date(fechaLong);
        
        return date;
    }
    
}
