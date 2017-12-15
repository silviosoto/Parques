/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorEventos;
import Controlador.ControladorParques;
import Controlador.ControladorPersona;
import Controlador.ControladorTipoEvento;
import Modelo.Evento;
import Modelo.Parque;
import Modelo.Persona;
import Modelo.TipoEvento;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RegistrarEvento extends HttpServlet {

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
                if (!item.isFormField()) {
                    File archivo = new File(absoluteDiskPath, item.getName());
                    item.write(archivo);
                    imgs.add("Imabenes-BD/" + item.getName());

                } else {
                    campos.add(item.getString());

                }
            }
        } catch (Exception e) {
            response.getWriter().println(e);
        }

        /* 
        response.getWriter().println(campos.get(0));
        response.getWriter().println(campos.get(1));
        response.getWriter().println(campos.get(2));
        response.getWriter().println(campos.get(3));
        response.getWriter().println(campos.get(4));
        response.getWriter().println(campos.get(5));
        response.getWriter().println(campos.get(6));
        response.getWriter().println(campos.get(7));
    
         */
        Evento evento = new Evento();
        ControladorEventos CEvento = new ControladorEventos();
        java.util.Date date = new java.util.Date();

        long t = date.getTime();
        java.sql.Time sqlTime = java.sql.Time.valueOf(campos.get(2) + ":00");

        String Tevento = campos.get(3);
        String fecha = campos.get(4);

        java.sql.Date sqlDate = java.sql.Date.valueOf(fecha);

        evento.setNombre(campos.get(0));
        evento.setDescripcion(campos.get(6));
        //evento.setPublico(campos.get(1));
        evento.setPublico("nino");
        evento.setDuracion(sqlTime);
        evento.setFoto(imgs.get(0));
        evento.setVoto(0);
        evento.setEstado("Activo");
        evento.setFecha(sqlDate);

        //id del usuario
        Persona persona = new Persona();
        Persona Rpersona = new Persona();
        persona.setUsuario(campos.get(5));
        ControladorPersona cpersona = new ControladorPersona();
        cpersona.setUser("Users");
        cpersona.setPass("Users");
        try {
            cpersona.conectar();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        Rpersona = cpersona.Mostrar(persona);
        evento.setIdPersona(Rpersona.getId());

        //Tipo de Evento
        TipoEvento TEvento = new TipoEvento();
        TipoEvento TEventoR = new TipoEvento();
        ControladorTipoEvento CTEvento = new ControladorTipoEvento();
        CTEvento.setUser("Users");
        CTEvento.setPass("Users");
        try {
            CTEvento.conectar();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        TEvento.setNombre(campos.get(3));

        TEventoR = CTEvento.Buscar(TEvento);
        evento.setIdEvento(TEventoR.getId());

        evento.setIdEvento(TEventoR.getId());

        //obtener el id del parque
        Parque parque = new Parque();
        Parque parqueR = new Parque();
        ControladorParques Parques = new ControladorParques();
        Parques.setUser("Users");
        Parques.setPass("Users");
        try {
            Parques.conectar();
        } catch (SQLException ex) {
            response.getWriter().println("Error");
            Logger.getLogger(RegistrarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        parque.setNombre(campos.get(7));
        parqueR = Parques.Buscar(parque);
        evento.setIdParque(parqueR.getId());

        //response.getWriter().println("parqueid:"+evento.getIdParque());
        response.getWriter().println("Nombre:" + evento.getNombre());
        response.getWriter().println("Descripcion:" + evento.getDescripcion());
        response.getWriter().println("Publico:" + evento.getPublico());
        response.getWriter().println("Duracion:" + evento.getDuracion());
        response.getWriter().println("Foto:" + evento.getFoto());
        response.getWriter().println("Foto:" + evento.getFecha());
        response.getWriter().println("Voto:" + evento.getVoto());
        response.getWriter().println("Estado:" + evento.getEstado());
        response.getWriter().println("parque:" + evento.getIdParque());
        response.getWriter().println("tipo evento:" + evento.getIdEvento());
        response.getWriter().println("persona encargada:" + evento.getIdPersona());
        CEvento.setUser("Users");
        CEvento.setPass("Users");
        try {
            CEvento.conectar();
            if (CEvento.Registrar(evento) == true) {
                response.getWriter().println("Registro exitoso");
                response.sendRedirect("Usuario.jsp");
            } else {
                response.getWriter().println("Error al registrar");
                response.sendRedirect("Usuario.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEvento.class.getName()).log(Level.SEVERE, null, ex);
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
