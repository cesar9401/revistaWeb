/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.*;
import objeto.*;

/**
 *
 * @author cesar31
 */
@WebServlet(name = "ControladorRevista", urlPatterns = {"/ControladorRevista"})
@MultipartConfig(maxFileSize = 16177215)
public class ControladorRevista extends HttpServlet {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
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

        String action = request.getParameter("accion");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String user = (String) request.getSession().getAttribute("user");
        switch (action) {
            case "newRevista":
                if (metodosDePagoDAO.verificarMetodo(user)) {
                    request.getRequestDispatcher("nuevaRevista.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("metodoDePago.jsp").forward(request, response);
                }
                break;

            case "buscarRevistas":
                List<Revista> revistas = revistaDAO.getRevistasForUser(user, false);
                for (int i = 0; i < revistas.size(); i++) {
                    if (revistas.get(i).isBloquear()) {
                        revistas.remove(i);
                        i = i - 1;
                    }
                }
                request.getSession().setAttribute("revistasUser", revistas);
                request.getRequestDispatcher("buscarRevistas.jsp").forward(request, response);
                break;

            case "buscarEditores":
                List<Usuario> editores = usuarioDAO.getUsuarios(true);
                request.getSession().setAttribute("editores", editores);
                request.getRequestDispatcher("buscarEditores.jsp").forward(request, response);
                break;

            case "inicio":
                if (usuario.isEditor()) {
                    request.getRequestDispatcher("inicioEditor.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                }
                break;

            default:
                if (!action.equals("")) {
                    int idRevista = Integer.parseInt(action);
                    Revista revista = revistaDAO.getRevistaById(idRevista);
                    request.getSession().setAttribute("revista", revista);
                    request.getRequestDispatcher("procesarRevista.jsp").forward(request, response);
                }
                break;

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
        //Write your code here

        String action = request.getParameter("accion");
        String user = (String) request.getSession().getAttribute("user");
        switch (action) {
            case "NuevaRevista":
                NuevaRevista(request, response);
                break;

            case "RegistrarMetodo":
                RegistarMetodo(request, response);
                break;

            case "ProcesarRevista":
                ProcesarRevista(request, response);
                break;

            case "Suscribirme":
                if (metodosDePagoDAO.verificarMetodo(user)) {
                    setSuscripcion(request, response);
                } else {
                    response.sendRedirect("metodoDePago.jsp");
                }
                break;

            case "Comentario":
                //Comentario aqui
                setComentario(request, response);
                break;

            default:
                break;
        }
    }

    public void NuevaRevista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = (String) request.getSession().getAttribute("user");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");
        Double cuota = Double.parseDouble(request.getParameter("cuota"));
        boolean reaccion = true ? (request.getParameter("reaccion").equals("permitir")) : false;
        boolean comentarios = true ? (request.getParameter("comentarios").equals("permitir")) : false;
        Revista revista = new Revista(titulo, categoria, descripcion, cuota, reaccion, comentarios, user);

//        String urlPDF = request.getParameter("archivo");
//        File filePDF = new File(urlPDF);
        InputStream inputStream = null;
        try {
            Part filePart = request.getPart("archivo");
            inputStream = filePart.getInputStream();
            revista.setRevistaPDF(inputStream);
//            byte[] pdf = new byte[(int) filePDF.length()];
//            InputStream input = new FileInputStream(filePDF);
//            input.read(pdf);
//            revista.setArchivoPDF(pdf);
        } catch (Exception ex) {
//            revista.setArchivoPDF(null);
        }
        int id = revistaDAO.getId();
        int edicion = revistaDAO.getEdicion(id);
        revista.setIdRevista(id);
        revista.setEdicion(edicion);
        revista.setBloquear(true);

        if (metodosDePagoDAO.verificarMetodo(user)) {
            revistaDAO.setRevista(revista);
            List<Revista> revistas = revistaDAO.getRevistasByEditor(user);
            request.getSession().setAttribute("revistas", revistas);
            //request.getRequestDispatcher("inicioEditor.jsp").forward(request, response);
            response.sendRedirect("inicioEditor.jsp");
        } else {
            request.getRequestDispatcher("metodoDePago.jsp").forward(request, response);
        }
    }

    public void RegistarMetodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String num = request.getParameter("numero");
        long numero = 0;
        try {
            numero = Long.parseLong(num);
        } catch (NumberFormatException ex) {

        }
        int day = 1;
        int month = Integer.parseInt(request.getParameter("mes"));
        int year = Integer.parseInt(request.getParameter("year"));
        Calendar fecha = Calendar.getInstance();
        fecha.set(year, month - 1, day);
        java.sql.Date date = new java.sql.Date(fecha.getTimeInMillis());
        String codigo = request.getParameter("codigo");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        MetodosDePago tarjeta = new MetodosDePago(numero, nombre, date, codigo, usuario.getIdUsuario());
        metodosDePagoDAO.setMetodoDePago(tarjeta);

        if (usuario.isEditor()) {
            response.sendRedirect("inicioEditor.jsp");
        } else {
            response.sendRedirect("inicio.jsp");
        }
    }

    public void ProcesarRevista(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Revista revista = (Revista) request.getSession().getAttribute("revista");
        Double cuotaDia = Double.parseDouble(request.getParameter("cuota"));
        Double porcentaje = Double.parseDouble(request.getParameter("porcentaje"));
        String fecha = request.getParameter("fecha");
        java.sql.Date date = null;
        try {
            date = operaciones.getDate(fecha);
        } catch (ParseException ex) {
        }

        revista.setBloquear(false);
        revista.setFechaPublicacion(date);
        int idUtilidades = revistaDAO.getIdRegistroUtilidades();
        revistaDAO.setProcesarRevista(revista, porcentaje, cuotaDia, idUtilidades);

        //write your code here
        request.getSession().removeAttribute("revista");
        request.getSession().removeAttribute("revistas");

        List<Revista> revistas = revistaDAO.getRevistas();
        for (int i = 0; i < revistas.size(); i++) {
            if (!revistas.get(i).isBloquear()) {
                revistas.remove(i);
                i = i - 1;
            }
        }
        request.getSession().setAttribute("revistas", revistas);
        response.sendRedirect("inicioAdministrador.jsp");
    }

    public void setSuscripcion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String user = (String) request.getSession().getAttribute("user");
        int idRevista = Integer.parseInt(request.getParameter("idMagazine"));
        double cuotaSuscripcion = Double.parseDouble(request.getParameter("cuota"));
        String fecha = request.getParameter("fecha");
        java.sql.Date date = null;
        try {
            date = operaciones.getDate(fecha);
        } catch (ParseException ex) {

        }

        int idSuscripcion = operaciones.getIdSuscripcion();
        double porcentaje = revistaDAO.getPorcentaje(idRevista);
        operaciones.setSuscripcion(idSuscripcion, cuotaSuscripcion, porcentaje, user, idRevista, date);
        List<Revista> misRevistas = revistaDAO.getRevistasForUser(user, true);
        request.getSession().setAttribute("misRevistas", misRevistas);

        response.sendRedirect("inicio.jsp");
        //request.getRequestDispatcher("inicio.jsp").forward(request, response);
    }

    private void setComentario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Revista revista = (Revista) request.getSession().getAttribute("revista");
        String usuario = (String) request.getSession().getAttribute("user");
        String comment = request.getParameter("comentario");
        String fecha = request.getParameter("fecha");
        java.sql.Date date = null;
        try {
            date = operaciones.getDate(fecha);
        } catch (ParseException ex) {

        }
        int idSuscripcion = operaciones.getIdSuscripcionForUser(revista.getIdRevista(), usuario);
        Comentario comentario = new Comentario(idSuscripcion, comment, revista.getIdRevista(), usuario, date);
        System.out.println(comentario.toString());
        revistaDAO.setComentario(comentario);
        List<Comentario> comentarios = revistaDAO.getComentarios(revista.getIdRevista());
        request.getSession().setAttribute("comentarios", comentarios);
        response.sendRedirect("visualizarRevista.jsp");
    }
}
