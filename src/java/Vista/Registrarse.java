/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorPersona;
import Modelo.Persona;
import Seguridad.Seguridad;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Silvio
 */
public class Registrarse extends HttpServlet {

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

        String Nombres = request.getParameter("Nombres");
        String Fnacimiento = request.getParameter("Fnacimiento");
        String email = request.getParameter("email");
        String Sexo = request.getParameter("Sexo");
        String contrsena = request.getParameter("contrsena");
        String Rcontrsena = request.getParameter("Rcontrsena");

        Seguridad seguridad = new Seguridad();

        if (seguridad.ValidarCadenaVacia(Nombres) == false && seguridad.ValidarCadenaVacia(Fnacimiento) == false
                && seguridad.ValidarCadenaVacia(email) == false && seguridad.ValidarCadenaVacia(Sexo) == false
                && seguridad.ValidarCadenaVacia(contrsena) == false && seguridad.ValidarCadenaVacia(Rcontrsena) == false) {

            if (seguridad.ValidarSoloLetras(Nombres) == true) {

                if (seguridad.ValidarNumerosYLetras(contrsena) == true && seguridad.ValidarNumerosYLetras(Rcontrsena) == true) {
                    if (contrsena.equals(Rcontrsena)) {
                        if (Sexo.equals("H") || Sexo.equals("M")) {

                            ControladorPersona Cpersona = new ControladorPersona();
                            Persona persona = new Persona();
                            Cpersona.setUser("Admin");
                            Cpersona.setPass("Admin");

                            String date = Fnacimiento; // YYYY-MM-DD
                            java.sql.Date sqlDate = java.sql.Date.valueOf(date);

                            persona.setNombre(Nombres);
                            persona.setFNacimiento(sqlDate);
                            persona.setSexo(Sexo);
                            persona.setFoto("");
                            persona.setUsuario(email);
                            persona.setContrasena(contrsena);
                            persona.setEstado(contrsena);

                            try {
                                Cpersona.conectar();
                                if (Cpersona.EstadoCuenta(persona) == false) {
                                    if (Cpersona.RegistrarPersona(persona)) {
                                        HttpSession Usuario = request.getSession(true);
                                        Usuario.setAttribute("Usuario", email);
                                        HttpSession contrasenna = request.getSession(true);
                                        contrasenna.setAttribute("contrasena", contrsena);

                                        String sSubCadena = contrsena.substring(0, 3);
                                        if (sSubCadena.equals("000")) {
                                            response.sendRedirect("UsuarioConfiguracion.jsp");
                                        } else {
                                            if (sSubCadena.equals("111")) {
                                                response.sendRedirect("UsuarioUsers.jsp");
                                            } else {
                                                response.sendRedirect("Usuario.jsp");
                                            }
                                        }
                                    } else {
                                        response.sendRedirect("Registrar.jsp");
                                    }
                                } else {
                                    response.sendRedirect("Registrar.jsp");
                                }
                            } catch (SQLException ex) {
                                out.println("Error Al conectarse a la base de datos");
                            }
                        } else {
                            response.sendRedirect("Registrar.jsp");
                        }
                    } else {   
                        response.sendRedirect("Registrar.jsp");
                    }
                } else {
                    response.sendRedirect("Registrar.jsp");
                }
            } else {               
                response.sendRedirect("Registrar.jsp");
            }
        } else {
            response.sendRedirect("Registrar.jsp");
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
