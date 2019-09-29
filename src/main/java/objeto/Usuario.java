
package objeto;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author cesar31
 */
public class Usuario {
    private String idUsuario;
    private String email;
    private String nombres;
    private String apellidos;
    private String ubicacion;
    private Date fechaNac;
    private String sexo;
    private String password;
    private String hobbies;
    private String descripcion;
    private InputStream imagen;
    private byte[] foto;
    private boolean editor;

    public Usuario(String idUsuario, String email, String ubicacion, Date fechaNac, String sexo, String password, String hobbies, String descripcion, boolean editor) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.ubicacion = ubicacion;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.password = password;
        this.hobbies = hobbies;
        this.descripcion = descripcion;
        this.editor = editor;
    }

    /*
        Constructor para admins de la Base de Datos
    */
    public Usuario(String idUsuario, String email, String password) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isEditor() {
        return editor;
    }

    public void setEditor(boolean editor) {
        this.editor = editor;
    }
}
