/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.RevistaDAO;
import modelo.UsuarioDAO;
import objeto.Revista;
import objeto.Usuario;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

    UsuarioDAO usr = new UsuarioDAO();
    RevistaDAO revista = new RevistaDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idUsuario = request.getParameter("idUsuario");
            usr.listarImg(idUsuario, response);
        } catch (IllegalStateException ex) {

        }

        try {
            String action = request.getParameter("action");
            switch (action) {
                case "CerrarSesion":
                    HttpSession session = request.getSession();
                    session.invalidate();
                    response.sendRedirect("index.jsp");
                    break;

                default:
                    break;
            }

        } catch (NullPointerException ex) {

        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
            Todos los botones tiene como name="accion", pero depende del valor de value, se enfocan en acciones como
            Registrar, IniciarSesion. etc.
         */
        String accion = request.getParameter("accion");

        if (accion.equals("RegistrarUser") || accion.equals("RegistrarEditor")) {
            String usuario = request.getParameter("usuario");
            String email = request.getParameter("email");
            int month = Integer.parseInt(request.getParameter("mes")) - 1;
            int day = Integer.parseInt(request.getParameter("dia"));
            int year = Integer.parseInt(request.getParameter("year"));
            Calendar fecha = Calendar.getInstance();
            fecha.set(year, month, day);
            java.sql.Date fechaNac = new java.sql.Date(fecha.getTimeInMillis());
            String nacionalidad = request.getParameter("nacionalidad");
            String sexo = request.getParameter("sexo");
            String descripcion = request.getParameter("descripcion");
            String hobbies = request.getParameter("hobbies");
            String urlFoto = request.getParameter("fotografia");

            String pass = request.getParameter("pass");
            Usuario tmp = new Usuario(usuario, email, nacionalidad, fechaNac, sexo, pass, hobbies, descripcion, false);

            File file = new File(urlFoto);
            try {
                byte[] foto = new byte[(int) file.length()];
                InputStream input = new FileInputStream(file);
                input.read(foto);
                tmp.setFoto(foto);
            } catch (Exception ex) {
                tmp.setFoto(null);
            }

            //Agregar usuario tmp al request
            if (accion.equals("RegistrarEditor")) {
                tmp.setEditor(true);
                request.setAttribute("usuario", tmp);
                request.getRequestDispatcher("inicioEditor.jsp").forward(request, response);

            } else {
                request.setAttribute("usuario", tmp);
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            }

            //Guardar al usuario tmp en la base de datos
            usr.setUsuario(tmp);
        }

        if (accion.equals("Iniciar")) {
            String user = request.getParameter("usuario");
            String password = request.getParameter("pass");

            //Se envian los datos idUsuario y password a usuarioDAO para verificar si se encuentra registrado en la base
            //de datos
            Usuario tmp = usr.getUsuario(user, password);
            if (tmp != null) {
                //Agregar al usuario tmp al request
                if (tmp.isEditor()) {
                    //request.setAttribute("usuario", tmp);
                    List<Revista> revistas = revista.getRevistas(user);
                    request.getSession().setAttribute("usuario", tmp);
                    request.getSession().setAttribute("revistas", revistas);
                    request.getRequestDispatcher("inicioEditor.jsp").forward(request, response);

                } else {
                    //request.setAttribute("usuario", tmp);
                    request.getSession().setAttribute("usuario", tmp);
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                }

            } else {
                //Si tmp = null, redireccionar a la pagina de inicio de Sesion
                request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
