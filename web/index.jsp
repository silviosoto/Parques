<%-- 
    Document   : index
    Created on : 13-nov-2017, 20:51:21
    Author     : Silvio
--%>

<%@page import="Modelo.Evento"%>
<%@page import="Controlador.ControladorEventos"%>
<%
    
    ControladorEventos CEvento = new ControladorEventos();
        CEvento.setUser("Practicante");
        CEvento.setPass("Practicante");
        CEvento.conectar();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Parques</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

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
       <div class="contenedor">
            <div class="row">
                <div id="test-swipe-1" class="col s12">
                    <% for (Evento listaEventos : CEvento.Mostrar()) {
                    %>
                    <div class="col s3">
                        <div class="card">
                            <div class="card-image waves-effect waves-block waves-light">
                                <img class="activator" src="<%=listaEventos.getFoto()%>">
                            </div>
                            <div class="card-content">
                                <span class="card-title activator grey-text text-darken-4"><%= listaEventos.getNombre()%><i class="material-icons right">more_vert</i></span>
                                <p><a href="#">Detalle</a></p>
                            </div>
                            <div class="card-reveal">
                                <span class="card-title grey-text text-darken-4">Detalles<i class="material-icons right">close</i></span>
                                <p><%=listaEventos.getDescripcion()%></p>
                            </div>
                        </div>
                    </div>
                    <%
                }%>
                </div>
            </div> 
        </div>
        <footer class="page-footer footer-azul" >
            <div class="footer-copyright footer-azul-copy ">
                <div class="container ">
                    © 2014 Copyright Text
                    <a class="grey-text text-lighten-4 right" href="#!">Ayuda</a>
                </div>
            </div>
        </footer>
        <script src="Libs/materialize/js/jquery-3.2.1.min.js" ></script>
        <script src="Libs/materialize/js/materialize.min.js" ></script>

    </body>
</html>
