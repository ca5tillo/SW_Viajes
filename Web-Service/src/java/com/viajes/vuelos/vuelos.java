/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viajes.vuelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lap
 */
public class vuelos {

    private final static JSONObject JSONbase = new JSONObject();//tendra una lista de vuelo
    private static java.sql.Connection conexion = null;

    static class vuelosDirectosSencillo {

        java.text.DecimalFormat formatoSalidaDecimal = new java.text.DecimalFormat("0.00");//para dos decimales

        public JSONObject getJSon() {
            return JSONbase;
        }
//<--ESCALA

        /**
         * uno{ lst(vielos)[ uno_uno]} dos{ lst2[ temporal]} vuelos_con_escala{
         * Origen, Destino, Costo, Bandera, lst3[ uno_uno temporal]} JSONbase{
         * bandera escalas[ vuelos_con_escala]}
         *
         *
         */
        public boolean vueloDconescala(String origen, String destino, String fecha) {
            boolean a = false;
            JSONParser parser = new JSONParser();
            int count = -1;
            int z = 0;
            String tem_destino = "";
            JSONArray escalas = new JSONArray();
            do {
                try {
                    if (vuelosDirectosSencillos(origen, "", fecha, 2)) {
                        String tem = "" + JSONbase;
                        JSONbase.clear();
                        Object obj = parser.parse(tem);
                        JSONObject uno = (JSONObject) obj;

                        JSONArray lst = (JSONArray) uno.get("listaVuelos");
                        if (count == -1) {
                            count = lst.size();
                        }
                        for (int i = 0; i < lst.size(); i++) {

                            JSONObject uno_uno = (JSONObject) lst.get(i);
                            String neworigen = (String) uno_uno.get("destino");
                            if (vuelosDirectosSencillos(neworigen, destino, fecha, 2)) {
                                //solo ago un transbordo
                                a = true;
                                String tem2 = "" + JSONbase;
                                JSONbase.clear();
                                Object obj2 = parser.parse(tem2);
                                JSONObject dos = (JSONObject) obj2;
                                JSONArray lst2 = (JSONArray) dos.get("listaVuelos");
                                JSONObject temporal = (JSONObject) lst2.get(0);

                                String aa = (String) uno_uno.get("costo");
                                String bb = (String) temporal.get("costo");
                                double costo22 = Double.parseDouble(aa)
                                        + Double.parseDouble(bb);
                                String costo = formatoSalidaDecimal.format(costo22);

                                JSONObject vuelo_con_escala = new JSONObject();

                                JSONArray lst3 = new JSONArray();
                                lst3.add(uno_uno);
                                lst3.add(temporal);
                                vuelo_con_escala.put("listaVuelos", lst3);

                                vuelo_con_escala.put("origen", origen);
                                vuelo_con_escala.put("destino", destino);
                                vuelo_con_escala.put("costo", costo);
                                vuelo_con_escala.put("bandera", "vuelo_con_escala");

                                escalas.add(vuelo_con_escala);
                            }
                        }
                    }
                    z += 1;
                } catch (ParseException e) {
                    System.out.println("Error en el parser de JSON()-> vueloDconescala() " + e);
                }
            } while (z < count);

            JSONbase.put("bandera", "vuelo_con_escala");
            JSONbase.put("listaVuelos", escalas);

            return a;
        }
//--> ESCALA        
//<-- REDONDO

        public boolean vueloRedondo(String origen, String destino, String fecha1, String fecha2) {
            boolean a = false;
            JSONParser parser = new JSONParser();
            String JSONida;
            String JSONregreso;
            if (vuelosDirectosSencillos(origen, destino, fecha1, 10)) {
                JSONida = "" + getJSon();
                JSONbase.clear();
                if (vuelosDirectosSencillos(destino, origen, fecha2, 3)) {
                    // invieto origen y destino para el de regreso 
                    a = true;
                    JSONregreso = "" + getJSon();
                    JSONbase.clear();

                    JSONbase.put("bandera", "redondo");
                    JSONbase.put("origen", origen);
                    JSONbase.put("destino", destino);

                    try {
                        //<--
                        String json_ida = "" + JSONida;
                        Object obj = parser.parse(json_ida);
                        JSONObject jsonObject_ida = (JSONObject) obj;
                        JSONArray listaVuelos_ida = (JSONArray) jsonObject_ida.get("listaVuelos");
                        //-->
                        //<--
                        String json_regreso = "" + JSONregreso;
                        Object obj2 = parser.parse(json_regreso);
                        JSONObject jsonObject_regreso = (JSONObject) obj2;
                        JSONArray listaVuelos_regreso = (JSONArray) jsonObject_regreso.get("listaVuelos");
                        //--<

                        if (listaVuelos_ida.size() < listaVuelos_regreso.size()) {
                            crearRedondo(listaVuelos_ida, listaVuelos_regreso);
                        } else if (listaVuelos_ida.size() > listaVuelos_regreso.size()) {
                            crearRedondo(listaVuelos_regreso, listaVuelos_ida);
                        } else if (listaVuelos_ida.size() == listaVuelos_regreso.size()) {
                            crearRedondo(listaVuelos_regreso, listaVuelos_ida);
                        }
//                        crearRedondo(listaVuelos_regreso);

                    } catch (ParseException e) {
                        System.out.println("Error en el parser de JSON-> " + e);
                    }
                }
            }
            return a;
        }

        private void crearRedondo(JSONArray list, JSONArray listmayor) {
            //usare en mas chico 
            JSONArray listaVuelosR = new JSONArray(); // solo un vuelo
            for (int i = 0; i < list.size(); i++) {
                JSONObject JSon_vuelo = new JSONObject();
                JSONArray listaVuelos = new JSONArray(); // solo un vuelo

                JSONObject ida = (JSONObject) list.get(i);
                JSONObject regreso = (JSONObject) listmayor.get(i);

                double costo2 = Double.parseDouble((String) ida.get("costo"))
                        + Double.parseDouble((String) regreso.get("costo"));
                String costo = Double.toString(costo2);

                JSon_vuelo.put("costo", costo);
                listaVuelos.add(ida);
                listaVuelos.add(regreso);
                JSon_vuelo.put("listaVuelos", listaVuelos);
                listaVuelosR.add(JSon_vuelo);
            }
            JSONbase.put("listaVuelos", listaVuelosR);
        }
//--> REDONDO
//<-- DIRECTO

        public boolean vuelosDirectosSencillos(String origen, String destino, String fecha, int anadir_numero_de_dias) {
            vuelosDirectosSencillo w = new vuelosDirectosSencillo();
            boolean datos = false;
            String fecha2 = w.sumardias(fecha, anadir_numero_de_dias);
            if (true == w.vueloDirecto(origen, destino, fecha, fecha2)) {
                datos = true;
            }

            return datos;
        }

        // vuelo directo regresa true si es que encuentra vuelos EN LA BASE DE DATOS
        private boolean vueloDirecto(String origen, String destino, String fecha1, String fecha2) {
            JSONArray listaVuelos = new JSONArray(); // solo un vuelo
            boolean datos = false;
            DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hora = new SimpleDateFormat("HH:mm:ss");
            String baseDatos = "vuelos_SW";
            String usuario = "vuelosusuario";
            String password = "vuelosp";
            System.out.println(origen+ " -- "+destino+"  --  "+fecha1+"  --  "+fecha2);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/" + baseDatos, usuario, password);
                Statement s = conexion.createStatement();
                ResultSet rs = null;
                if (!destino.equalsIgnoreCase("")) {
                    rs = s.executeQuery("SELECT * FROM vuelos WHERE origen = '" + origen + "' AND "
                            + "destino = '" + destino + "' AND "
                            + "fechaS BETWEEN '" + fecha1 + "' AND '" + fecha2 + "' LIMIT 0,10");
                } else {
                    rs = s.executeQuery("SELECT * FROM vuelos WHERE origen = '" + origen + "' AND "
                            + "fechaS BETWEEN '" + fecha1 + "' AND '" + fecha2 + "' LIMIT 0,10");
                }
                if (rs.next() == true) {// si tiene elementos 
                    rs.beforeFirst();  //regreso al principio para no perder ningun registro
                    datos = true;
                    JSONbase.put("bandera", "sencillo");
                    while (rs.next()) {
                        JSONObject JSon_vuelo = new JSONObject();
                        JSon_vuelo.put("id", Integer.toString(rs.getInt("idvuelo")));
                        JSon_vuelo.put("aerolinea", rs.getString("aerolinea"));
                        JSon_vuelo.put("origen", rs.getString("origen"));
                        JSon_vuelo.put("destino", rs.getString("destino"));
                        String fs = fecha.format(rs.getDate("fechaS"));
                        JSon_vuelo.put("fechaS", fs);
                        String hrS = hora.format(rs.getTime("horaS"));
                        JSon_vuelo.put("horaS", hrS);
                        String duracionvuelo = hora.format(rs.getTime("duracionVuelo"));
                        JSon_vuelo.put("duracionVuelo", duracionvuelo);
                        String costo = Double.toString(rs.getDouble("costo"));
                        JSon_vuelo.put("costo", costo);
                        
                        listaVuelos.add(JSon_vuelo);
                    }
                    JSONbase.put("listaVuelos", listaVuelos); // JSON le agrago objets JSON
                    conexion.close();
                }

            } catch (SQLException e) {
                System.out.println("ERROR AL TRATAR DE HACER LA CONSULTA -> " + e);
            } catch (ClassNotFoundException e) {
                System.out.println("NO ENCUENTRA EL CONECTOR MYSQL -> " + e);
            }
            return datos;
        }
//-->DIRECTO
        // sumar dias se usa para ampliar el rango de busqueda entre fechas y ubtener mas opciones de vuelo 
        // en otros dias 

        private String sumardias(String fecha1, int anadir_numero_de_dias) {
            //"2014-06-25"  prototipo de fecha usado
            Calendar calendarfecha = new GregorianCalendar();
            String[] fechatemp = fecha1.split("-");        //corta la fecha en año- mes- dia

            calendarfecha.set(Integer.parseInt(fechatemp[0]),
                    Integer.parseInt(fechatemp[1]),
                    Integer.parseInt(fechatemp[2]), 0, 0, 0);// los cero representan el tiempo 
            // porq calendarfecha lo requiere y se colocan ceros porq no los usare 

            calendarfecha.add(Calendar.DAY_OF_MONTH, anadir_numero_de_dias);        //sumar 13 dias
            int año = calendarfecha.get(Calendar.YEAR);
            int mes = calendarfecha.get(Calendar.MONTH);
            int dia = calendarfecha.get(Calendar.DAY_OF_MONTH);

            return Integer.toString(año) + "-" + Integer.toString(mes) + "-" + Integer.toString(dia);
        }

    }

    public JSONObject buscarVuelos(String origen, String destino, String fecha1, String fecha2) {
        vuelosDirectosSencillo v = new vuelosDirectosSencillo();
        JSONbase.clear();
        if (fecha2 == null || fecha2.equalsIgnoreCase("") || fecha2.equalsIgnoreCase("0000-00-00")) {//vuelo directo SENCILLO
            if (true != v.vuelosDirectosSencillos(origen, destino, fecha1, 10)) {//si devuelve falso deve de buscar vuelos con esala
                // falta buscar con escala
                System.out.println("he entrado a un vuelo que tiene que ser con escala \n"
                        + v.vueloDconescala(origen, destino, fecha1));

                if (true != v.vueloDconescala(origen, destino, fecha1)) {
                    vueloNoEncontrado();
                }
//                vueloNoEncontrado();//si tampoco encuentra vuelos con escalas
            }
        } else {// VUELO REDONDO 
            if (true != v.vueloRedondo(origen, destino, fecha1, fecha2)) {
                vueloNoEncontrado();
            }

//            vueloNoEncontrado();//ya que aun no tengo algo que responda a dos fechas 
        }

        return JSONbase;
    }

    private void vueloNoEncontrado() {
        JSONbase.clear();
        JSONbase.put("bandera", "vueloNoEncontrado");
    }

    public static void main(String[] args) {
        vuelos v = new vuelos();

        String fecha1 = "2014-06-25";//Ete es el formato de fecha de la mayoria de paises latino americanos 

        System.out.println(v.buscarVuelos("Albania", "Alemania", "2014-07-01", ""));

//        String str="12.35 ";
//double numero=Double.parseDouble(str);
//double c,b=2.02;
//c= numero +b;
//System.out.println("str     "+c);
    }

}
