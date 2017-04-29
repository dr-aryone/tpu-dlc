/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo_web;

import Testing.Testing;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modulo_indexacion.Libro;
import modulo_vectorial.GestorConsulta;

/**
 *
 * @author filardo
 */
@WebServlet(name = "servletPrueba", urlPatterns = {"/servletPrueba"})
public class servletPrueba extends HttpServlet {
    
    GestorConsulta gc = new GestorConsulta();

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
        processRequest(request, response);
        
        // Obtengo el parámetro del recurso campoBusqueda, lo guardo en un String
        String consulta = request.getParameter("campoBusqueda");
       
        
        List<Libro> libros = gc.resolverConsulta(consulta);
        String s = "";
        String t[] = new String[10];
       
        
        for (Libro libro : libros) {
            s += libro.getDescripcion() + "\n";
            libro.createPreview();
            s += libro.getPreview() + "\n";
           
            /*try {
                libro.createPreview();
                System.out.println(libro.getPreview());
                System.out.println("");
            } catch (IOException ex) {
                Logger.getLogger(Testing.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        
        // Seteo el resultadoConsulta con lo que le paso. Este atributo luego
        // lo uso como quiero en el cliente: en un h1, p, tabla, lo que sea
        request.setAttribute("resultadoConsulta", s);
        // forwardeo la request a otro archivo
        request.getRequestDispatcher("resultados.jsp").forward(request, response);
        
        // otra forma de hacerlo es con la sesión
        //request.getSession().setAttribute("resultadoConsulta", consulta);
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
