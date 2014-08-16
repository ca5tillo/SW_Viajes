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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lp-ub-14
 */
public class ControladorVuelosD extends HttpServlet {

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
            String origen = request.getParameter("origen");
            String destino = request.getParameter("destino");
            String fechaS = request.getParameter("fechadeSalida");
            String fechallega = request.getParameter("fechallega");//para vuelo redondo el dia que quiere regresar
            String g = origen;
            String misdatos = origen+" - "+destino+" - "+fechaS+" - "+fechallega;
            dispacher = getServletContext().getRequestDispatcher("/Vuelos.jsp");
////            HttpSession sesion = request.getSession(true);
//
            /*<--*/
            //esta condicion se creo pensando ennavegadores que 
            //no soportan el calendario 
            if (true != comprovarFormatoFecha(fechaS)) {
                if (fechallega != null && !fechallega.equals("") && true != comprovarFormatoFecha(fechallega)) {
                    String fechamalescrita = "fecha mal escrita" + fechallega;
                    request.setAttribute("fechamalescrita", fechamalescrita);
                    dispacher.forward(request, response);
                }
            }
            if (fechallega != null && !fechallega.equals("")) {
                if (fechallega.equalsIgnoreCase(fechaS)) {
                    String fechamalescrita = "fecha inicio y final son la misma <br> Cambien las fechas";
                    request.setAttribute("fechamalescrita", fechamalescrita);
                    dispacher.forward(request, response);
                }
            }
            if (fechallega != null && !fechallega.equals("")) {
                if (!que_fechaFinal_sea_mayor(fechaS, fechallega)) {
                    String fechamalescrita = "La fecha en que desea regresar  no puede ser menor a la fecha en laque ha salido  ";
                    request.setAttribute("fechamalescrita", fechamalescrita);
                    dispacher.forward(request, response);
                }
            }

            /*-->*/
            if (origen.equalsIgnoreCase(destino)) {
                misdatos = "El Origen y el Destino son el mismo busqueda rechazada";
                request.setAttribute("misdatos", misdatos);
                dispacher.forward(request, response);
            }
            misdatos = leerJson(consultarVuelos(origen, destino, fechaS, fechallega));
//            misdatos = consultarVuelos(origen, destino, fechaS, fechallega);
//
//            request.setAttribute("origen", origen);
//            request.setAttribute("destino", destino);
//            request.setAttribute("fechaS", fechaS);
//            request.setAttribute("fechallega", fechallega);
//            request.setAttribute("g", g);
            out.println("hola <br>"+misdatos);
            request.setAttribute("misdatos", misdatos);
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
    /*<--*/

    // Es el SW
    private static String consultarVuelos(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) {
        com.viajes.SWViajes_Service service = new com.viajes.SWViajes_Service();
        com.viajes.SWViajes port = service.getSWViajesPort();
        return port.consultarVuelos(arg0, arg1, arg2, arg3);
    }
    /*-->*/
    /*<--*/

    // funcion leerJson(String) revisa la bandera y decide como tiene que extraer los datos 
    // manda el objeto a la funcion sencillo u otra para pasarlo a HTML
    private String leerJson(String JSONbase) {
        JSONParser parser = new JSONParser();
        String tablasDatos = "";
        try {
            String json = "" + JSONbase;
            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;

            String bandera = (String) jsonObject.get("bandera");
            if (bandera.equals("sencillo")) {
                tablasDatos = sencillo(JSONbase);
            } else if (bandera.equals("redondo")) {
                tablasDatos = redondo(JSONbase);
            } else if (bandera.equalsIgnoreCase("vuelo_con_escala")) {
                tablasDatos = vuelo_Directo_con_escala(JSONbase);
            } else if (bandera.equals("vueloNoEncontrado")) {
                tablasDatos = "No se encontraron vuelos";
            } else {
                tablasDatos = "vuelo NO encontrado";
            }
        } catch (ParseException e) {
            System.out.println("Error en el parser de JSON-> " + e);
        }
        return tablasDatos;
    }
    /*-->*/
    /*<--*/

    // funcion sencillo(String)  genera una tabla en HTML con los datos del JSON 
    // Recibe datos de leerJson(String) 
    private String sencillo(String JSONbase) {
        int contadorparaids = 0;
        String tablasDatos = "";
        String tem = "";
        JSONParser parser = new JSONParser();
        try {
            JSONObject JSONparaLaCompra = new JSONObject();
            String json = "" + JSONbase;
            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;

            String bandera = (String) jsonObject.get("bandera");
            JSONArray list = (JSONArray) jsonObject.get("listaVuelos");

            for (Object obj2 : list) {
                JSONObject jsonObject2 = (JSONObject) obj2;
                JSONparaLaCompra = jsonObject2;
                JSONparaLaCompra.put("bandera", "sencillo");

                tablasDatos += "<table> "
                        + "            <caption>Vuelo : " + bandera + "</caption>"
                        + "            <tr>"
                        + "                <th>Origen</th>"
                        + "                <th>Destino</th>"
                        + "                <th>Costo</th>"
                        + "            </tr> "
                        + "            <tr >"
                        + "                <td rowspan=\"2\"><p>" + jsonObject2.get("origen") + " </p></td>"
                        + "                <td rowspan=\"2\"><p>" + jsonObject2.get("destino") + "</p></td>"
                        + "                <td><p>$ " + jsonObject2.get("costo") + "</p></td>"
                        + "            </tr> "
                        + "            <tr>"
                        + "                <td>"
                        + "                <form method=\"post\" action=\"./ControladorComprar\">"
                        + "                     <TEXTAREA COLS=0 ROWS=0 NAME=\"compra\" style = \"display: none\" >" + JSONparaLaCompra
                        + "                     </TEXTAREA> "
                        + "                     <input type=\"submit\" value=\"comprar\">"
                        + "                </form>"
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
                        + "                        Aerolinea: " + jsonObject2.get("aerolinea") + "<br>"
                        + "                        Fecha del vuelo: " + jsonObject2.get("fechaS") + "<br>"
                        + "                        Hora del vuelo: " + jsonObject2.get("horaS") + "<br>"
                        + "                        duracion del Vuelo: " + jsonObject2.get("duracionVuelo") + " Hrs"
                        + "                    </div>"
                        + "                </td>"
                        + "            </tr>"
                        + "        </table><br> ";
                contadorparaids += 1;
            }

        } catch (ParseException e) {
            System.out.println("Error en el parser de JSON-> " + e);
        }
        return tablasDatos;
    }
    /*-->*/

    private String redondo(String JSONbase) {
        int contadorparaids = 0;
        String tablasDatos = "";
        String tem = "";
        JSONParser parser = new JSONParser();
        try {
            JSONObject JSONparaLaCompra = new JSONObject();
            String json = "" + JSONbase;
            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            //<--   Json base  qieten origen, destino, bandera, Array
            String bandera = (String) jsonObject.get("bandera");
            String origen = (String) jsonObject.get("origen");
            String destino = (String) jsonObject.get("destino");

            JSONArray list = (JSONArray) jsonObject.get("listaVuelos");
            //-->    list es un arreglo de objetos con : costos, Array de dos elementos V_ida, V_regreso
            for (Object obj2 : list) {
                //<--  objeto vuelo redondo
                JSONObject viajeRedondo = (JSONObject) obj2;
                String costo = (String) viajeRedondo.get("costo");
                JSONparaLaCompra = viajeRedondo;
                JSONparaLaCompra.put("bandera", "redondo");// se le añade la bandera 
                JSONArray listVredondo = (JSONArray) viajeRedondo.get("listaVuelos");
                // listVredondo tiene 2 objetos vuelo de ida y regreso

                JSONObject viajeR_ida = (JSONObject) listVredondo.get(1);
                JSONObject viajeR_regreso = (JSONObject) listVredondo.get(0);
                //-->
                // tengo: jsonObject [viajeRedondo [viajeR_ida, viajeR_regreso] ]

                tablasDatos += "<table> "
                        + "            <caption>Vuelo : " + bandera + "</caption>"
                        + "            <tr>"
                        + "                <th>Origen</th>"
                        + "                <th>Destino</th>"
                        + "                <th>Costo</th>"
                        + "            </tr> "
                        + "            <tr >"
                        + "                <td rowspan=\"2\"><p>" + origen + " </p></td>"
                        + "                <td rowspan=\"2\"><p>" + destino + "</p></td>"
                        + "                <td><p>$ " + costo + "</p></td>"
                        + "            </tr> "
                        + "            <tr>"
                        + "                <td>"
                        + "                <form method=\"post\" action=\"./ControladorComprar\">"
                        + "                     <TEXTAREA COLS=0 ROWS=0 NAME=\"compra\" style = \"display: none\" >" + JSONparaLaCompra
                        + "                     </TEXTAREA> "
                        + "                     <input type=\"submit\" value=\"comprar\">"
                        + "                </form>"
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
                        + "                        Origen: " + viajeR_ida.get("origen") + "<br>"
                        + "                        Destino: " + viajeR_ida.get("destino") + "<br>"
                        + "                        Aerolinea: " + viajeR_ida.get("aerolinea") + "<br>"
                        + "                        Fecha del vuelo: " + viajeR_ida.get("fechaS") + "<br>"
                        + "                        Hora del vuelo: " + viajeR_ida.get("horaS") + "<br>"
                        + "                        Duracion del Vuelo: " + viajeR_ida.get("duracionVuelo") + " Hrs<br>"
                        + "                        Costo: " + viajeR_ida.get("costo") + "<br>"
                        + "                        <br>---   ---   ---   ---   ---<br><br>"
                        + "                        Origen: " + viajeR_regreso.get("origen") + "<br>"
                        + "                        Destino: " + viajeR_regreso.get("destino") + "<br>"
                        + "                        Aerolinea: " + viajeR_regreso.get("aerolinea") + "<br>"
                        + "                        Fecha del vuelo: " + viajeR_regreso.get("fechaS") + "<br>"
                        + "                        Hora del vuelo: " + viajeR_regreso.get("horaS") + "<br>"
                        + "                        Duracion del Vuelo: " + viajeR_regreso.get("duracionVuelo") + " Hrs<br>"
                        + "                        Costo: " + viajeR_regreso.get("costo") + "<br><br>"
                        + "                    </div>"
                        + "                </td>"
                        + "            </tr>"
                        + "        </table><br> ";
                contadorparaids += 1;
            }

        } catch (ParseException e) {
            System.out.println("Error en el parser de JSON-> " + e);
        }
        return tablasDatos;
    }

    private String vuelo_Directo_con_escala(String JSONbase) {
        String tablasDatos = "";
        JSONParser parser = new JSONParser();
        JSONObject JSONparaLaCompra;
        int contadorparaids = 0;
        try {
            Object obj = parser.parse(JSONbase);
            JSONObject jsonbase = (JSONObject) obj;
            JSONArray lstJsonBase = (JSONArray) jsonbase.get("listaVuelos");
            for (Object vueloCescala : lstJsonBase) {
                JSONObject vueloConEscala = (JSONObject) vueloCescala;
                JSONparaLaCompra = vueloConEscala;
                JSONparaLaCompra.put("bandera", "vuelo_con_escala");// se le añade la bandera
                String origen = (String) vueloConEscala.get("origen");
                String destino = (String) vueloConEscala.get("destino");
                String costo = (String) vueloConEscala.get("costo");
                JSONArray lstVueloConEscala = (JSONArray) vueloConEscala.get("listaVuelos");
                String DatosDeLosVuelosEscala = "";
                for (Object listaVuelosEscalaXviaje : lstVueloConEscala) {
                    JSONObject objEscalasDelVuelo = (JSONObject) listaVuelosEscalaXviaje;
                    String origen_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("origen");
                    String destino_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("destino");
                    String aerolinea_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("aerolinea");
                    String fechaS_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("fechaS");
                    String horaS_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("horaS");
                    String duracionVuelo_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("duracionVuelo");
                    String costo_objEscalasDelVuelo = (String) objEscalasDelVuelo.get("costo");
                    DatosDeLosVuelosEscala
                            += "                        Origen: " + origen_objEscalasDelVuelo + "<br>"
                            + "                        Destino: " + destino_objEscalasDelVuelo + "<br>"
                            + "                        Aerolinea: " + aerolinea_objEscalasDelVuelo + "<br>"
                            + "                        Fecha del vuelo: " + fechaS_objEscalasDelVuelo + "<br>"
                            + "                        Hora del vuelo: " + horaS_objEscalasDelVuelo + "<br>"
                            + "                        Duracion del Vuelo: " + duracionVuelo_objEscalasDelVuelo + " Hrs<br>"
                            + "                        Costo: " + costo_objEscalasDelVuelo + "<br>"
                            + "                        <br>---   ---   ---   ---   ---<br><br>";

                }
                tablasDatos += "<table> "
                        + "            <caption>Vuelo : " + "Vuelos Con Escalas" + "</caption>"
                        + "            <tr>"
                        + "                <th>Origen</th>"
                        + "                <th>Destino</th>"
                        + "                <th>Costo</th>"
                        + "            </tr> "
                        + "            <tr >"
                        + "                <td rowspan=\"2\"><p>" + origen + " </p></td>"
                        + "                <td rowspan=\"2\"><p>" + destino + "</p></td>"
                        + "                <td><p>$ " + costo + "</p></td>"
                        + "            </tr> "
                        + "            <tr>"
                        + "                <td>"
                        + "                <form method=\"post\" action=\"./ControladorComprar\">"
                        + "                     <TEXTAREA COLS=0 ROWS=0 NAME=\"compra\" style = \"display: none\" >" + JSONparaLaCompra
                        + "                     </TEXTAREA> "
                        + "                     <input type=\"submit\" value=\"comprar\">"
                        + "                </form>"
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
                        + DatosDeLosVuelosEscala
                        + "                    </div>"
                        + "                </td>"
                        + "            </tr>"
                        + "        </table><br> ";
                contadorparaids += 1;
            }

        } catch (ParseException e) {
            tablasDatos = "error en el parse de vuelo_Directo_con_escala";
        }
        return tablasDatos;
    }

}
