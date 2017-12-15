/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorPersona;
import Modelo.Persona;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Silvio
 */
public class EditarUsuario extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        FileItemFactory file = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(file);

        ArrayList<String> campos = new ArrayList<>();
        ArrayList<String> imgs = new ArrayList<>();
        try {
            List items = sfu.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);
                String relativeWebPath = "Imabenes-BD/";
                String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
                //Con esta linea se puede ver la ruta en donde se quiere que se guarden los archivos (imagenes)
                //response.getWriter().println(getServletContext().getRealPath(relativeWebPath));

                if (!item.isFormField()) {
                    File archivo = new File(getServletContext().getRealPath(relativeWebPath) + item.getName());
                    item.write(archivo);
                    //Linea para decirle a la base de datos donde se guardo la imagen 
                    imgs.add("Imabenes-BD/" + item.getName());
                } else {
                    campos.add(item.getString());

                }
            }
        } catch (Exception e) {
            response.getWriter().println(e);
        }

        boolean consulta = false;
        Persona personas = new Persona();

        String date = campos.get(1); // YYYY-MM-DD
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        ControladorPersona Cpersona = new ControladorPersona();

        personas.setNombre(campos.get(0));
        personas.setFNacimiento(sqlDate);
        personas.setUsuario(campos.get(2));
        personas.setSexo(campos.get(3));
        personas.setFoto(imgs.get(0));

        response.getWriter().println(personas.getNombre());
        response.getWriter().println(personas.getFNacimiento());
        response.getWriter().println(personas.getUsuario());
        response.getWriter().println(personas.getSexo());
        response.getWriter().println(personas.getFoto());

        Cpersona.setUser("Users");
        Cpersona.setPass("Users");

        try {
            Cpersona.conectar();
            consulta = Cpersona.Editar(personas);
            if (consulta == true) {
                // response.sendRedirect("EditarUsuario.jsp");
                response.getWriter().println("Inserción exitosa");
            }

        } catch (SQLException ex) {
            response.getWriter().println(ex);
            // response.sendRedirect("Usuario.jsp");

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
