<%-- 
    Document   : Entrar.jsp
    Created on : 13-nov-2017, 20:52:02
    Author     : Silvio
--%>

<%@page import="Modelo.Persona"%>
<%@page import="Controlador.ControladorPersona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contra = "";
    String Usuario = "";

    HttpSession sesion = request.getSession(false);
    HttpSession conra = request.getSession(false);
 ControladorPersona Cpersona = new ControladorPersona();
    // si existe la session
    
    if (sesion.getAttribute("Usuario") != null || conra.getAttribute("contrasena") != null) {
        Usuario = (String) sesion.getAttribute("Usuario");
        contra = (String) conra.getAttribute("contrasena");
        if (contra.equals("") == false || Usuario.equals("") == false) {

           
            String sSubCadena = contra.substring(0, 3);

            if (!sSubCadena.equals("111")) {
                sesion.invalidate();
                response.sendRedirect("Entrar.jsp");
            } else {
                Cpersona.setUser("Users");
                Cpersona.setPass("Users");
                Cpersona.conectar();
                if (!Cpersona.Autenticacion(Usuario, contra)) {
                    sesion.invalidate();
                    response.sendRedirect("Entrar.jsp");
                }
            }
        } else {
            sesion.invalidate();
            response.sendRedirect("Entrar.jsp");
        }
    } else {
        sesion.invalidate();
        response.sendRedirect("Entrar.jsp");
    }
    Persona personas = new Persona();
    Persona Rpersonas = new Persona();
    
        Cpersona.setUser("Users");
        Cpersona.setPass("Users");
        Cpersona.conectar();
        personas.setUsuario(Usuario);
       Rpersonas= Cpersona.Mostrar(personas);
        
%>

<!DOCTYPE html>
<html lang="en">
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
                    <li><a href="index.jsp">Salir</a></li>
                </ul>
            </div>
        </nav>
        <div class="contenedor">
        <ul class="collection with-header">
        <li class="collection-header"><h4>Usuario: <%=Rpersonas.getUsuario() %></h4></li>
        <li class="collection-item"><div>Crear Eventos<a href="NuevoEvento.jsp" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        <li class="collection-item"><div>Editar Perfil<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div></li>
        </ul>
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
