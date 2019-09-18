/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UsuarioDAO;
import objeto.Usuario;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {
    
    Usuario tmp;
    

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
        
        UsuarioDAO usr = new UsuarioDAO(null);
        String idUsuario = request.getParameter("idUsuario");
        usr.listarImg(idUsuario, response);
        
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
        processRequest(request, response);
        
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
        
        tmp = new Usuario(usuario, email, nacionalidad, fechaNac, sexo, pass, hobbies, descripcion, false);
        
        File file = new File(urlFoto);
        try{
            byte[] foto = new byte[(int) file.length()];
            InputStream input = new FileInputStream(file);
            input.read(foto);
            tmp.setFoto(foto);
        }catch(IOException ex){
            tmp.setFoto(null);
        }
        
        
        UsuarioDAO modeloUsuario = new UsuarioDAO(tmp);
        modeloUsuario.setUsuario();

        //Agregar usuario tmp al request
        request.setAttribute("usuario", tmp);
        request.getRequestDispatcher("inicio.jsp").forward(request, response);

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
