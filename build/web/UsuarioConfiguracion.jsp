<%-- 
    Document   : Entrar.jsp
    Created on : 13-nov-2017, 20:52:02
    Author     : Silvio
--%>

<%@page import="Modelo.Persona"%>
<%@page import="Controlador.ControladorPersona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    String contra = "";
    String Usuario = "";
    
    HttpSession sesion = request.getSession(false);
    HttpSession conra = request.getSession(false);
    
    // si existe la session 
    ControladorPersona Cpersona = new ControladorPersona();
    Persona  persona  = new Persona();
    persona.setUsuario(Usuario);
    
    if(sesion.getAttribute("Usuario") != null || conra.getAttribute("contrasena") != null){
        Usuario = (String) sesion.getAttribute("Usuario");
        contra = (String) conra.getAttribute("contrasena");
        if (contra.equals("") == false || Usuario.equals("") == false) {
                  
            String sSubCadena = contra.substring(0, 3);

            if (!sSubCadena.equals("000")) {
                response.sendRedirect("Entrar.jsp");
            } else {
                Cpersona.setUser("Admin");
                Cpersona.setPass("Admin");
                Cpersona.conectar();
                if (!Cpersona.Autenticacion(Usuario, contra)) {
                    sesion.invalidate();
                    response.sendRedirect("Entrar.jsp");
                }else{
                    persona.setUsuario(Usuario);
                    persona = Cpersona.Mostrar(persona);
                }
            }
        }else{  
            sesion.invalidate();
            response.sendRedirect("Entrar.jsp");
        }
    }else{
        sesion.invalidate();
        response.sendRedirect("Entrar.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Parques</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="Libs/materialize/css/materialize.min.css">
        <link rel="stylesheet" href="css/Estilos.css">
    </head>
    <body>
        <nav>
            <div class="nav-wrapper">
                <a href="index.jsp" class="brand-logo">Parques</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                     <a class='dropdown-button btn' href='#' data-activates='dropdown1'>Drop !</a>
                        <!-- Dropdown Structure -->
                        <ul id='dropdown1' class='dropdown-content'>
                          <li><a href="#!">one</a></li>
                          <li><a href="#!">two</a></li>
                          <li class="divider"></li>
                          <li><a href="#!">three</a></li>
                          <li><a href="#!"><i class="material-icons">view_module</i>four</a></li>
                          <li><a href="#!"><i class="material-icons">cloud</i>five</a></li>
                        </ul
                </ul>
            </div>
        </nav>
        <div class="contenedor-entrar">
            <h4>Administrador</h4>
            <p>Perfil de: <% out.println(persona.getNombre());%></p>
        </div>
         <footer class="page-footer">
           <div class="footer-copyright">
             <div class="container">
             © 2014 Copyright Text
             <a class="grey-text text-lighten-4 right" href="#!">Ayuda</a>
             </div>
           </div>
         </footer>
        <script src="Libs/materialize/js/jquery-3.2.1.min.js" ></script>
        <script src="Libs/materialize/js/materialize.min.js" ></script>
    </body>
</html>
