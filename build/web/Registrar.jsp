<%-- 
    Document   : Registrar
    Created on : 13-nov-2017, 20:52:30
    Author     : Silvio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li><a href="Registrar.jsp">Registrarse</a></li>
                    <li><a href="Entrar.jsp">Entrar</a></li>
                </ul>
            </div>
        </nav>
        <div class="contenedor-from-regitrar-usr ">
            <br>
            <h4 id= "registrate">Registrate</h4>
            <br>
            <div class="row">
                <form class="col s12" action = "RegistrarUsuario"  method="post" name="RegistrarUsuario" >
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="Nombres" name="Nombres" type="text" class="validate">
                            <label for="Nombres">Nombres</label>
                        </div>
                        <div class="input-field col s6">
                            <input  id="Fnacimiento" name="Fnacimiento" type="date" class="validate">
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="email" name="email" type="email" class="validate">
                            <label for="email">Email</label>
                        </div>
                        <p>
                            <input value = "H" name="Sexo" type="radio" id="test1" class="Sexo" checked = "checked" />
                            <label for="test1">Hombre</label>
                            <input value = "M" name="Sexo" type="radio" id="test2" id="Sexo" />
                            <label for="test2">Mujer</label>
                        </p>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="contrsena" name="contrsena" type="password" class="validate">
                            <label for="contrsena">Contraseña</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="Rcontrsena" name = "Rcontrsena" type="password" class="validate">
                            <label for="Rcontrsena">Repetir Contrsena</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Enviar
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                        <div class="input-field col s6">
                            <button class="btn waves-effect waves-light" type="resert" name="action">Limpiar            
                            </button>
                        </div> 
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
    </body>
</html>