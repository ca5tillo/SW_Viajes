
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession sesion = request.getSession();
        String hoteles = (String) request.getAttribute("hoteles");
        String errorDatosEntrada = (String) request.getAttribute("errorDatosEntrada");
        String fechamalescrita = (String) request.getAttribute("fechamalescrita");
    %>
    <head>
        <meta charset="iso-8859-1">
        <meta name="descripcion" content="ejemplo de html5">
        <meta name="keywords" content= "html5, css3, javascript">
        <title> MG </title>
        <link rel= "stylesheet" href="misestilos.css">
        <script type="text/javascript">

            document.write('<style type="text/css">div.cp_oculta{display: none;}</style>');
            function MostrarOcultar(capa, enlace)
            {
                if (document.getElementById)
                {
                    var aux = document.getElementById(capa).style;
                    aux.display = aux.display ? "" : "block";
                }
            }
        </script>
    </head>
    <body>
        <div id ="agrupar">
            <header id= "cabecera">
                <h1>Listo para Viajar</h1>
            </header>
            <nav id="menu">
                <ul>
                    <li> <a href="./index.html">principal </a></li>
                    <li> <a href="./Vuelos.jsp">Vuelos</a> </li>
                    <li style="box-shadow: inset 3px 3px 10px "> <a href="./Hotel.jsp">Hoteles </a></li>
                    <li> Vuelos+Hotel </li>
                </ul>
            </nav>
            <section id="seccion">
                <article>
                    <header>
                        <hgroup>
                            <h2>Busca tu Hotel</h2><br>
                        </hgroup>
                    </header>
                    <form method="post" action="./ControladorHotel" >
                        Destino/Hotel<br>
                        <input type="search" name ="destinoHotel" value="Alemania" required><br><br> 
                        # De Habitaciones<br>
                        <input type="number" name="habitaciones" id="habitaciones" min="1" max="10" required><br><br>
                        Llegada<br>
                        <input type="date" name="fechadeLlegada" step="1" min="2014-06-01" max="2015-12-31" value=""><br><br>
                        Salida<br>
                        <input type="date" name="fechadeSalida" step="1" min="2014-06-01" max="2015-12-31" value=""><br><br>

                        <footer>
                            <input type="submit"value="buscar">
                            </form>
                        </footer>
                </article>

            </section>
            <aside id="columna">
                <blockquote>
                    <%if (hoteles != null) {%>
                    <%=hoteles%><%}%>
                    <%if (fechamalescrita != null) {%>   <%=fechamalescrita%>   <%}%>
                
                </blockquote>
            </aside>
            <footer id ="pie">
                derechos reservados &copy; 2013-2014
            </footer>
        </div>
    </body>
</html>
