/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import objeto.*;

/**
 *
 * @author cesar31
 */
public class RevistasController extends HttpServlet {

    RevistaDAO revistaDAO = new RevistaDAO();
    MetodosDePagoDAO metodosDePagoDAO = new MetodosDePagoDAO();
    Operaciones operaciones = new Operaciones();

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
            out.println("<title>Servlet RevistasController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RevistasController at " + request.getContextPath() + "</h1>");
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

        //Mostrar pdf, recibe el id de la revista y devuelve el pdf
        try {
            String showPDF = request.getParameter("showPDF");
            int id = Integer.parseInt(showPDF);

            //Obtener revista en PDF
            revistaDAO.getPdf(id, response);
        } catch (NumberFormatException ex) {

        }

        //Para redirigir hacia la pagina visualizarRevista, con la informacion de la revista
        try {
            String action = request.getParameter("action");
            int idRevista = Integer.parseInt(action);
            Revista revista = revistaDAO.getRevistaById(idRevista);
            request.getSession().setAttribute("revista", revista);

            //Insertar listado de comentarios aquí
            List<Comentario> comentarios = revistaDAO.getComentarios(idRevista);
            request.getSession().setAttribute("comentarios", comentarios);
            response.sendRedirect("visualizarRevista.jsp");
            //request.getRequestDispatcher("visualizarRevista.jsp").forward(request, response);
        } catch (NumberFormatException ex) {

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
        processRequest(request, response);
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
