/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ContrladorLoginIP;
import Controlador.ControladorPersona;
import Modelo.Logs;
import Modelo.Persona;
import java.text.SimpleDateFormat;
import Seguridad.Seguridad;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Silvio
 */
public class Login extends HttpServlet {

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
        
        String Correo = request.getParameter("Correo");
        String contrasena = request.getParameter("contrasena");

        Seguridad seguridad = new Seguridad();
        
        String ip = null; // IP del cliente
        String host = null; // Host del cliente
        
        ip = request.getRemoteAddr();
        host = request.getRemoteHost();
        
        
        ContrladorLoginIP cLoginIP = new ContrladorLoginIP();
        
        // para Obtener la fecha de hoy 
        Date fecha = new Date();
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        
        java.util.Date Date = new java.util.Date();
        long t = Date.getTime();
        java.sql.Time sqlTime =  java.sql.Time.valueOf("20:30:20") ;
       
        Date fechaHoy = new Date();
        java.sql.Date fechaHoySQL = new java.sql.Date(fecha.getTime());
        
        // obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        
        java.sql.Time HoraTimeSql =  java.sql.Time.valueOf(hourFormat.format(fecha));
        System.out.println(HoraTimeSql);
        
        
        
        if (seguridad.ValidarCadenaVacia(Correo) == false && seguridad.ValidarCadenaVacia(contrasena) == false) {
            Persona persona = new Persona();
            persona.setUsuario(Correo);
            ControladorPersona Cpersona = new ControladorPersona();
            String sSubCadena = contrasena.substring(0, 3);
            
            Logs logs = new Logs();
            logs.setIdUsuario(1);
            logs.setLogin(Correo);
            logs.setFecha(fechaSQL);
            logs.setHora(HoraTimeSql);
            logs.setIpMaquina(ip);
            logs.setIpServidor(host);
            ContrladorLoginIP cLoginIp = new ContrladorLoginIP();
            cLoginIp.setUser("Admin");
            cLoginIp.setPass("Admin");
            
            //La contraseña del Administrador del uauario Admin en la BD commienza con 3 ceros
            // 3 ceros para cuando el usuario es un Admistrador
            if (sSubCadena.equals("000")){
                Cpersona.setUser("Admin");
                Cpersona.setPass("Admin");
                try {
                    Cpersona.conectar();
                    if (Cpersona.Autenticacion(Correo, contrasena) == true && Cpersona.EstadoCuenta(persona) == true ) {
                        HttpSession Usuario = request.getSession(true);
                        Usuario.setAttribute("Usuario", Correo);
                        HttpSession contrasenna = request.getSession(true);
                        contrasenna.setAttribute("contrasena", contrasena);
                        //Fecha de hoy 
                        cLoginIp.conectar();
                        cLoginIp.RegistrarLogin(logs);
                        response.sendRedirect("UsuarioConfiguracion.jsp");
                    } else {
                        response.sendRedirect("Entrar.jsp");
                    }
                } catch (SQLException ex) {
                    out.println("Hay un problema para la base de datos");
                }
            } else {
                //3 unos para cuando el usuario es un Users
                if (sSubCadena.equals("111")) {
                    Cpersona.setUser("Users");
                    Cpersona.setPass("Users");
                    try {
                        Cpersona.conectar();
                        if (Cpersona.Autenticacion(Correo, contrasena)) {
                            HttpSession Usuario = request.getSession(true);
                            Usuario.setAttribute("Usuario", Correo);
                            HttpSession contrasenna = request.getSession(true);
                            contrasenna.setAttribute("contrasena", contrasena);
                            cLoginIp.RegistrarLogin(logs);
                            response.sendRedirect("UsuarioUsers.jsp");
                        } else {
                            response.sendRedirect("Entrar.jsp");
                        }
                    } catch (SQLException ex) {
                        out.println("Hay un problema para la base de datos");
                    }
                } else {
                    
                    Cpersona.setUser("Practicante");
                    Cpersona.setPass("Practicante");
                    try {
                        Cpersona.conectar();
                        if (Cpersona.Autenticacion(Correo, contrasena)) {
                            HttpSession Usuario = request.getSession(true);
                            Usuario.setAttribute("Usuario", Correo);
                            HttpSession contrasenna = request.getSession(true);
                            contrasenna.setAttribute("contrasena", contrasena);
                            response.sendRedirect("Usuario.jsp");
                            
                        } else {
                            out.println(Cpersona.Autenticacion(Correo, contrasena));
                            response.sendRedirect("Entrar.jsp");
                        }
                    } catch (SQLException ex) {
                         response.sendRedirect("Entrar.jsp");
                    }
                    
                }
            }
        } else {
            response.sendRedirect("Entrar.jsp");
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
