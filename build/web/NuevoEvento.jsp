<%-- 
    Document   : Entrar.jsp
    Created on : 13-nov-2017, 20:52:02
    Author     : Silvio
--%>

<%@page import="Controlador.ControladorPersona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String contra = "";
    String Usuario = "";

    HttpSession sesion = request.getSession(false);
    HttpSession conra = request.getSession(false);

    // si existe la session 
    if (sesion.getAttribute("Usuario") != null || conra.getAttribute("contrasena") != null) {
        Usuario = (String) sesion.getAttribute("Usuario");
        contra = (String) conra.getAttribute("contrasena");
        if (contra.equals("") == false || Usuario.equals("") == false) {

            ControladorPersona Cpersona = new ControladorPersona();
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
                <a href="index.jsp"  class="brand-logo ">Parques</a>
                </ul>
            </div>
        </nav>
        <h5 class="h5-titulo">Crear Evento</h5>
        <div class="contenedor-crear-evento">
            <div class="row">
                <form class="col s12" action= "Crear-evento"  method="post" enctype="multipart/form-data"  id="crear-evento" name="craar-evento">
                    <div class="input-field col s6">
                    <div class="row">
                        <div class="input-field col s4">
                            <i class="material-icons prefix">title</i>
                            <input id="Titulo" name="Titulo" type="text"  >
                            <label for="Titulo" >Titulo</label>
                        </div>
                        <div class="input-field col s4">
                          <select id="Publico" name="Publico">
                            <option value="" disabled selected>Escoje una opción</option>
                            <option value="Todo Publico">Todo Publico</option>
                            <option value="Adultos">Adultos</option>
                            <option value="Niños">Niños</option>
                          </select>
                          <label>Publico</label>
                        </div>
                        <div class="input-field col s4">
                            <i class="material-icons prefix">access_time</i>
                            <input type="time" name="hora" id="hora">
                            <label for="hora" ></label>
                        </div>
                    </div>
                    <div class="row">
                      <div class="input-field col s4">
                        <select id="TEvento" name="TEvento">
                          <option value="" disabled selected>Escoje una opción</option>
                          <option value="Concierto">Concierto</option>
                          <option value="Musical">Musical</option>
                          <option value="Cuenteros">Cuenteros</option>
                        </select>
                        <label>Tipo De Evento</label>
                      </div>
                      <div class="input-field col s4">
                            <i class="material-icons prefix">date_range</i>
                            <input type="date" name="FechaEvento" id="FechaEvento" >
                            <label for="FechaEvento" ></label>
                      </div>
                      <div class="input-field col s4">
                          <i class="material-icons prefix">person</i>
                          <input type="text" name="usuario" id="usuario" >
                          <label for="usuario" ></label>
                      </div>
                      </div>
                      <div class="row">
                        <div class="input-field col s6">
                          
                              <div class="input-field col s12">
                                <i class="material-icons prefix">mode_edit</i>
                                <textarea id="Detalles"  name="Detalles" id="Detalles" class="materialize-textarea"></textarea>
                                <label for="Detalles">Detalles</label>
                              </div>
                           
                        </div>
                          <div class="input-field col s6">
                          <div class="row">
                              <div class="input-field col s12">
                                <i class="material-icons prefix">location_on</i>
                                <input type="text" name="Parque" id="Parque" >
                                <label for="Parque">Parque</label>
                              </div>
                            </div>
                        </div>
                        </div>
                        </div>
                        <div class="input-field col s6">
                          <div class="row">
                            <div class="center">
                           <img src="Imagenes\placeholder.png" class="img-evento" id="img-defecto" alt="" style="width: 350px;height: 250px;">
                            <output id="list"  ></output>
                            </div>
                            </div>
                          <div class="row">
                              <div class="file-field input-field">
                             <div class="btn">
                               <span>subir imagen</span>
                               <input type="file" id="files" name="files[]">
                             </div>
                             <div class="file-path-wrapper">
                               <input class="file-path validate" type="text">
                             </div>
                           </div>
                            </div>
                        </div>
                     </div>
                     <div class="input-field col s6">
                       <div class="center">
                         <button class="btn waves-effect waves-light" type="submit" name="action">Editar
                          <i class="material-icons right">send</i>
                        </button>
                       </div>
                </form>
            </div>
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
        <script src="Js/jquery.validate.js" ></script>
        <script src="Js/Validar-form-crear-evento.js" ></script>
        <script src="Js/lenguaje-fecha.js" ></script>
    </body>
</html>
