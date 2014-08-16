<%-- 
    Document   : prue
    Created on : 03-jul-2014, 1:59:15
    Author     : lap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        //funcion javascript en la cabecera del documento
<script type="text/javascript">

function mostrarReferencia(){
//Si la opcion con id Conocido_1 (dentro del documento > formulario con name fcontacto >     y a la vez dentro del array de Conocido) esta activada
if (document.fcontacto.Conocido[1].checked === true) {
//muestra (cambiando la propiedad display del estilo) el div con id 'desdeotro'
document.getElementById('desdeotro').style.display='block';
//por el contrario, si no esta seleccionada
} else {
//oculta el div con id 'desdeotro'
document.getElementById('desdeotro').style.display='none';
}
}

</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        //se le asigna un name al formulario de contacto.
<form action="<?=$_SERVER['PHP_SELF']?>" method="post" name="fcontacto" >
//inputs



//div oculto

                                        <form method="post" action="./ControladorVuelosD" >
<p>A través de donde nos has conocido:<br />
//importante llamar a la función
<input type="radio" name="Conocido" value="Google" id="Conocido_0" onclick="mostrarReferencia();" checked/> Google
<input type="radio" name="Conocido" value="Otros" id="Conocido_1" onclick="mostrarReferencia();" /> Otros
</p>                                            
                                            
                                            Origen<br>
                                            <input type="text" name ="origen" value=""><br><br>
                                            Destino<br>
                                            <input type="text" name ="destino" value="" ><br><br> 
                                            Salida<br>
                                            <input type="date" name="fechadeSalida" step="1" min="2014-06-01" max="2015-12-31" value="2014-06-01"><br><br>
<div id="desdeotro" style="display:none;">
<p>Referencia de la oferta:</p>
<p><input type="text" name="otro" class="input" /></p>
</div>                                          
                                            <input type="submit"value="buscar">
                                        </form>
    </body>
</html>
