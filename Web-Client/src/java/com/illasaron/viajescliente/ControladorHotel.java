/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.illasaron.viajescliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lp-ub-14
 */
public class ControladorHotel extends HttpServlet {

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
        response.setContentType("text/html;charset=ISO-8859-1");
        RequestDispatcher dispacher;//respuesta
        try (PrintWriter out = response.getWriter()) {
            /*variables que se reciben del jsp
            destinoHotel
            habitaciones
            fechadeLlegada
            fechadeSalida
            */
            /* Cachando datos de las variables */
            String destinoHotel = request.getParameter("destinoHotel");
            String habitaciones = request.getParameter("habitaciones");
            String fechadeLlegada = request.getParameter("fechadeLlegada");
            String fechadeSalida = request.getParameter("fechadeSalida");
            String misdatos = destinoHotel +" - "+habitaciones +" - "+ 
                    fechadeLlegada +" - "+ fechadeSalida;
            String estado = "ok";
            /* FIN Cachando datos de las variables */
            /*crear sesion*/
            dispacher = getServletContext().getRequestDispatcher("/Hotel.jsp");
           // HttpSession sesion = request.getSession(true);
            /*FIN crear sesion*/
            /*operaciones*/
 String errorDatosEntrada = "";
            String hoteles = "";

            
            if (fechadeSalida == null || fechadeSalida.equals("") ||  fechadeSalida.equals("0000-00-00")) {
                String fechamalescrita = "Ingrese las dos fechas  ";
                request.setAttribute("fechamalescrita", fechamalescrita);
                dispacher.forward(request, response);
            }
            /*<--*/
            //esta condicion se creo pensando ennavegadores que 
            //no soportan el calendario 
            if (true != comprovarFormatoFecha(fechadeLlegada)) {
                if (fechadeSalida != null && !fechadeSalida.equals("") && true != comprovarFormatoFecha(fechadeSalida)) {
                    String fechamalescrita = "fecha mal escrita";
                    request.setAttribute("fechamalescrita", fechamalescrita);
                    dispacher.forward(request, response);
                }
            }
            /*-->*/
            //<--
            if (fechadeLlegada.equalsIgnoreCase(fechadeSalida)) {
                String fechamalescrita = "fecha inicio y final son la misma <br> Cambien las fechas";
                request.setAttribute("fechamalescrita", fechamalescrita);
                dispacher.forward(request, response);
            }

            if (!que_fechaFinal_sea_mayor(fechadeLlegada, fechadeSalida)) {
                String fechamalescrita = "La fecha de entrada al hotel no puede ser mayor a la de Salida ";
                request.setAttribute("fechamalescrita", fechamalescrita);
                dispacher.forward(request, response);
            }

            //-->
            int dias = dias_que_estara_en_el_hotel(fechadeLlegada, fechadeSalida);
            int cuartos = Integer.parseInt(habitaciones);

            hoteles = buscarHotel(destinoHotel, fechadeLlegada, dias, cuartos);


            


            
            /*FIN operaciones*/
            /*MANDANDO RESPUESTA*/
            request.setAttribute("hoteles", hoteles);
            request.setAttribute("errorDatosEntrada", errorDatosEntrada);
            
            dispacher.forward(request, response);
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

//esta condicion se creo pensando ennavegadores que 
    //no soportan el calendario 

    private boolean comprovarFormatoFecha(String fecha) {
        boolean a = false;
        Pattern pat = Pattern.compile("^[0-9]{4,4}[-][0-9]{1,2}[-][0-9]{1,2}$");
        Matcher mat = pat.matcher(fecha);
        if (mat.matches()) {
            System.out.println("SI");// formato valido
            a = true;
        } else {
            System.out.println("NO");
        }
        return a;
    }
    /*-->*/
    private boolean que_fechaFinal_sea_mayor(String fechaEntrada, String fechaSalida) {
        boolean dias = false;

        //"2014-06-25"  prototipo de fecha usado
        Calendar calendarfechaEntrada = new GregorianCalendar();
        String[] fechatemp_1 = fechaEntrada.split("-");        //corta la fecha en año- mes- dia

        calendarfechaEntrada.set(Integer.parseInt(fechatemp_1[0]),
                Integer.parseInt(fechatemp_1[1]),
                Integer.parseInt(fechatemp_1[2]), 0, 0, 0);// los cero representan el tiempo 
        // porq calendarfecha lo requiere y se colocan ceros porq no los usare 

        Calendar calendarfechaSalida = new GregorianCalendar();
        String[] fechatemp_2 = fechaSalida.split("-");        //corta la fecha en año- mes- dia

        calendarfechaSalida.set(Integer.parseInt(fechatemp_2[0]),
                Integer.parseInt(fechatemp_2[1]),
                Integer.parseInt(fechatemp_2[2]), 0, 0, 0);
        if (calendarfechaSalida.getTimeInMillis() > calendarfechaEntrada.getTimeInMillis()) {
            dias = true;
        } else {
            dias = false;
        }

        return dias;
    }

    private int dias_que_estara_en_el_hotel(String fechaEntrada, String fechaSalida) {
        int dias = 0;
        Calendar c = Calendar.getInstance();
        //"2014-06-25"  prototipo de fecha usado
        Calendar calendarfechaEntrada = new GregorianCalendar();
        String[] fechatemp_1 = fechaEntrada.split("-");        //corta la fecha en año- mes- dia

        calendarfechaEntrada.set(Integer.parseInt(fechatemp_1[0]),
                Integer.parseInt(fechatemp_1[1]),
                Integer.parseInt(fechatemp_1[2]), 0, 0, 0);// los cero representan el tiempo 
        // porq calendarfecha lo requiere y se colocan ceros porq no los usare 

        Calendar calendarfechaSalida = new GregorianCalendar();
        String[] fechatemp_2 = fechaSalida.split("-");        //corta la fecha en año- mes- dia

        calendarfechaSalida.set(Integer.parseInt(fechatemp_2[0]),
                Integer.parseInt(fechatemp_2[1]),
                Integer.parseInt(fechatemp_2[2]), 0, 0, 0);

        c.setTimeInMillis(calendarfechaSalida.getTime().getTime() - calendarfechaEntrada.getTime().getTime());
        dias = c.get(Calendar.DAY_OF_YEAR);
        return dias;
    }

    private String buscarHotel(String ubicacion, String fecha, int dias, int cuartos) {
        JSONParser parser = new JSONParser();
        String hoteles = "";
        String tem = "" + consultarHoteles(ubicacion, fecha);
        try {
            Object obj = parser.parse(tem);
            JSONObject jsonbase = (JSONObject) obj;
            String bandera = (String) jsonbase.get("bandera");
            if (bandera.equalsIgnoreCase("hoteles")) {
                hoteles = creartabla(tem, dias, cuartos);
            } else if (bandera.equalsIgnoreCase("hotelNoEncontrado")) {
                hoteles = "hoteles no encotrados ";

            } else {
                hoteles = "error en los datos";
            }
        } catch (ParseException e) {
            hoteles = "ERROR EN EL PARSE";
        }
        return hoteles;
    }

    private String creartabla(String JSONbase, int dias, int cuartos) {
        int contadorparaids = 0;
        Double costoTotal = -0.0;
        String tabla = "";
        String datosDelHotel = "";
        String reservar = "";
        String botonComprar = "";
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(JSONbase);
            JSONObject jsonbase = (JSONObject) obj;
            String bandera = (String) jsonbase.get("bandera");
            JSONArray lst = (JSONArray) jsonbase.get("lstHoteles");

            for (Object lstobj : lst) {
                JSONObject hotel = (JSONObject) lstobj;

                String idhotel = (String) hotel.get("idhotel");
                String ubicacion = (String) hotel.get("ubicacion");
                String nombre = (String) hotel.get("nombre");
                String numHabitaciones = (String) hotel.get("numHabitaciones");
                String habitacionesLibres = (String) hotel.get("habitacionesLibres");
                String costoXdia = (String) hotel.get("costoXdia");
                String fecha = (String) hotel.get("fecha");
                String estrellas = (String) hotel.get("estrellas");
                String estrella = "";
                if (Integer.parseInt(habitacionesLibres) >= cuartos) {
                    costoTotal = Double.parseDouble(costoXdia) * dias * cuartos;
                    reservar = "Costo total : $" + costoTotal + "<br>";
                    botonComprar = "                <form method=\"post\" action=\"./ControladorComprar\">"
                            + "                     <TEXTAREA COLS=0 ROWS=0 NAME=\"compra\" style = \"display: none\" >" + hotel
                            + "                     </TEXTAREA> "
                            + "                     <input type=\"submit\" value=\"Reservar\">"
                            + "                </form>";
                } else {
                    reservar = "habitaciones insuficientes lo sentimos <br>";
                    botonComprar = "<br>";
                }

                for (int i = 0; i < Integer.parseInt(estrellas); i++) {
                    estrella += "*";
                }
                tabla += "<table> "
                        + "            <caption>Hotel : " + nombre + "</caption>"
                        + "            <tr>"
                        + "                <th>Calificaciòn</th>"
                        + "                <th>habitaciones Libres</th>"
                        + "                <th>CostoXdia</th>"
                        + "            </tr> "
                        + "            <tr >"
                        + "                <td rowspan=\"2\"><p>" + estrella + " </p></td>"
                        + "                <td rowspan=\"2\"><p>" + habitacionesLibres + "</p></td>"
                        + "                <td><p>$ " + costoXdia + "</p></td>"
                        + "            </tr> "
                        + "            <tr>"
                        + "                <td>"
                        + botonComprar
                        + "                </td>"
                        + "            </tr>"
                        + "            <tr>"
                        + "                <td colspan=\"3\">"
                        + "                    <a class=\"texto\" href=\"javascript:MostrarOcultar('texto" + contadorparaids + "');\"> "
                        + "                        Mostrar / Ocultar Detalles</a>"
                        + "                </td>"
                        + "            </tr>"
                        + ""
                        + "            <tr>"
                        + "                <td colspan=\"3\">"
                        + "                    <div class=\"cp_oculta\" id=\"texto" + contadorparaids + "\"> "
                        + "                        nombre: " + nombre + "<br>"
                        + "                        ubicacion: " + ubicacion + "<br>"
                        + "                        numHabitaciones: " + numHabitaciones + "<br>"
                        + "                        habitacionesLibres: " + habitacionesLibres + "<br>"
                        + "                        costoXdia: " + costoXdia + "<br>"
                        + "                        Del dia : " + fecha + "<br>"
                        + "                        calificacion: " + estrellas + "<br>"
                        + "                        <br>---   ---   ---   ---   ---<br><br>"
                        + "                        # de cuartos pedidos : " + cuartos + "<br>"
                        + "                        Dias que estara en el hotel  : " + dias + "<br>"
                        + reservar
                        + "                        <br>---   ---   ---   ---   ---<br><br>"
                        + "                    </div>"
                        + "                </td>"
                        + "            </tr>"
                        + "        </table><br> ";
                contadorparaids += 1;

            }

        } catch (ParseException e) {
            tabla = "ERROR EN EL PARSE de crear tabla";
        }

        return tabla;
    }

    private static String consultarHoteles(java.lang.String arg0, java.lang.String arg1) {
        com.viajes.SWViajes_Service service = new com.viajes.SWViajes_Service();
        com.viajes.SWViajes port = service.getSWViajesPort();
        return port.consultarHoteles(arg0, arg1);
    }



}
