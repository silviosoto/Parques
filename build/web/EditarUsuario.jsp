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

            if (sSubCadena.equals("111") == true || sSubCadena.equals("000") == true) {
                sesion.invalidate();
                response.sendRedirect("Entrar.jsp");
            } else {
                Cpersona.setUser("Practicante");
                Cpersona.setPass("Practicante");
                Cpersona.conectar();
                if (!Cpersona.Autenticacion(Usuario, contra)) {
                    sesion.invalidate();
                    response.sendRedirect("Entrar.jsp");
                }
            }
        } else {
            System.out.println("no se creo la variable de sesion");
            sesion.invalidate();
            response.sendRedirect("Entrar.jsp");
        }
    } else {
        System.out.println("ia tu eso");
        sesion.invalidate();
        response.sendRedirect("Entrar.jsp");
    }
    Persona personas = new Persona();
    Persona Rpersonas = new Persona();

    Cpersona.setUser("Users");
    Cpersona.setPass("Users");
    Cpersona.conectar();
    personas.setUsuario(Usuario);
    Rpersonas = Cpersona.Mostrar(personas);

    String urlimg = "Imagenes/perfil.jpg";
    if (Rpersonas.getFoto().length() != 0) {
            urlimg = Rpersonas.getFoto();
        }
         
    String Sexo = Rpersonas.getSexo();
    String sexoH = "";
    String sexoM = "";
    if (Sexo.equals("H")) {
        sexoH = "checked";
    } else {
        sexoM = "checked";
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
                <a href="index.jsp" class="brand-logo ">Parques</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="EditarUsuario.jsp">Perfil</a></li>
                    <li><a href="index.jsp">Salir</a></li>
                </ul>
            </div>
        </nav>
        <h5 class="center">Editar Usuario</h5>
        <div class="contenedor">
            <div class="row">
                <form class="col s12" action="" method="post" enctype="multipart/form-data" id="Validar-Editar-Usuario" name="Validar-Editar-Usuario">
                    <div class="row">
                        <div class="input-field col s12 ">
                            <img src="<%= urlimg%>" alt="" class="img-editar">
                            <!--<div class="file-field input-field">
                                <div class="btn">
                                    <span>Subir foto</span>
                                    <input type="file" name="archivo" id="archivo" value="<%= urlimg%>">
                                </div>
                                <div class="file-path-wrapper">
                                    <input class="file-path validate" type="text" name="archivo" name="archivo">
                                </div>
                            </div>-->
                            
                            <input  type="file" name="archivo" name="archivo"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="Nombre" name="Nombre" type="text" value="<%=Rpersonas.getNombre()%>" >
                            <label for="icon_prefix" ></label>
                        </div>
                        <div class="input-field col s6">
                            <i class="material-icons prefix">perm_contact_calendar</i>
                            <input type="date" name="fecha" class="timepicker" id="fecha" value="">
                            <label for="fecha" ></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">perm_identity</i>
                            <input id="Usuario" name="Usuario" type="text" value="<%=Rpersonas.getUsuario()%>" >
                            <label for="icon_telephone"></label>
                        </div>


                        <input type="radio" name="sexo" value="H"  id="sexoH"  <%=sexoH%> />
                        <label for="sexoH">Hombre</label>
                        <input type="radio" name="sexo" value="M" id="sexoM"  <%=sexoM%>/>
                        <label for="sexoM">Mujer</label>

                    </div>
                    <div class="row">
                        <div class="center">
                            <button class="btn waves-effect waves-light" type="submit">Editar
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
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
    <script src="Js/Validar-form-editar-user.js" ></script>
    <script src="Js/lenguaje-fecha.js" ></script>
</body>
</html>
