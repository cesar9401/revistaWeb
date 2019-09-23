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
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.RevistaDAO;
import objeto.Revista;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "ControladorRevista", urlPatterns = {"/ControladorRevista"})
public class ControladorRevista extends HttpServlet {
    
    RevistaDAO revistaDAO = new RevistaDAO();
    Revista revista;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorRevista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorRevista at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        //Write your code here
        
        String user = (String) request.getSession().getAttribute("user");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");
        Double cuota = Double.parseDouble(request.getParameter("cuota"));
        boolean reaccion = true?(request.getParameter("reaccion").equals("permitir")):false;
        boolean comentarios = true?(request.getParameter("comentarios").equals("permitir")):false;
        String urlPDF = request.getParameter("archivo");
        revista = new Revista(titulo, categoria, descripcion, cuota, reaccion, comentarios, user);

        File filePDF = new File(urlPDF);
        try{
            byte[] pdf = new byte[(int)filePDF.length()];
            InputStream input = new FileInputStream(filePDF);
            input.read(pdf);
            revista.setArchivoPDF(pdf);
        }catch(Exception ex){
            revista.setArchivoPDF(null);
        }
        
        int id = revistaDAO.getId();
        int edicion = revistaDAO.getEdicion(id);
        revista.setIdRevista(id);
        revista.setEdicion(edicion);
        revista.setBloquear(true);
        
        revistaDAO.setRevista(revista);
        
        
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
