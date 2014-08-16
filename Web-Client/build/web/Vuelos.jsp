<%-- 
    Document   : Vuelos
    Created on : 29-jun-2014, 12:25:05
    Author     : lap
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
    <%
        HttpSession sesion = request.getSession();    
        String misdatos=(String)request.getAttribute("misdatos");
        String fechamalescrita=(String)request.getAttribute("fechamalescrita");
     
    %>
<head>
	<meta charset="iso-8859-1">
	<meta name="descripcion" content="ejemplo de html5">
	<meta name="keywords" content= "html5, css3, javascript">
	<title> MG </title>
	<link rel= "stylesheet" href="misestilos.css">
        <script type="text/javascript">

            function mostrarReferencia(){
                //Si la opcion con id Conocido_1 (dentro del documento > formulario con name tipodevuelo >     y a la vez dentro del array de Conocido) esta activada
                if (document.tipodevuelo.Conocido[1].checked === true) {
                    //muestra (cambiando la propiedad display del estilo) el div con id 'fecha_regresodiv'
                    document.getElementById('fecha_regresodiv').style.display='none';
                    
                    //por el contrario, si no esta seleccionada
                } else {
                    //oculta el div con id 'fecha_regresodiv'
                    document.getElementById('fecha_regresodiv').style.display='block';
                    
                }
            }
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
                            <li style=" box-shadow: inset 3px 3px 10px "> <a href="./Vuelos.jsp">Vuelos</a> </li>
                            <li><a href="./Hotel.jsp">Hoteles </a></li>
                            <li> Vuelos+Hotel </li>
			</ul>
		</nav>
		<section id="seccion">
			<article>
				<header>
					<hgroup>
                                            <h2>Busca tu vuelo</h2><br>
                                            <h3>Tipo de vuelo:</h3><br>
                                                <form action="<?=$_SERVER['PHP_SELF']?>" method="post" name="tipodevuelo" >                              
                                                    <input type="radio" name="Conocido" value="Redondo" id="Conocido_0" onclick="mostrarReferencia();" checked/>Redondo
                                                    <input type="radio" name="Conocido" value="Sencillo" id="Conocido_1" onclick="mostrarReferencia();" />Sencillo
                                                </form><br>                                                
					</hgroup>
				</header>                         
                                        <form method="post" action="./ControladorVuelosD" >
                                           
                                            Origen<br>
                                            <input type="search" name ="origen" value="Albania" required><br><br>
                                            Destino<br>
                                            <input type="search" name ="destino" value="Alemania" required><br><br> 
                                            Salida<br>
                                            <input type="date" name="fechadeSalida" step="1" min="2014-06-01" max="2015-12-31" value="2014-07-01"><br><br>
                                            <div id="fecha_regresodiv" style="display:block;">
                                                Regreso<br>
                                                <input type="date" name="fechallega" step="1" min="2014-06-01" max="2015-12-31" value="0000-00-00"><br><br>
                                            </div>                                                                                   
                                <footer>
                                            <input type="submit"value="buscar">
                                        </form>
				</footer>
			</article>
		</section>
                <aside id="columna">
                    <blockquote>
                        
                        <%if (fechamalescrita != null) {%>
                            <%=fechamalescrita%>
                        <%}%>
                        <%if (misdatos != null) {%>
                            <%=misdatos%>
                        <%}%>
                        
                    </blockquote>
                    <blockquote><br></blockquote>
                </aside>
		<footer id ="pie">
			derechos reservados &copy; 2014-2016
		</footer>
	</div>
</body>
</html>

