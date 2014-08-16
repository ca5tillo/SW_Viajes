/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viajes.hoteles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lap
 */
public class Hoteles {
    private final static JSONObject JSONbaseHotel = new JSONObject();
    private void cambiardatosBD(String fecha){
        Random habitacioneslibreRandom = new Random();
        java.sql.Connection conexion;
        String baseDatos = "vuelos_SW";
        String usuario = "vuelosusuario";
        String password = "vuelosp";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/"+baseDatos,usuario,password);
        
        Statement s = conexion.createStatement();
        Statement supdate=conexion.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM hotelesBD");
        while(rs.next()){
            int numhabitaciones = rs.getInt("numeroDeHabitaciones");
            int id = rs.getInt("idhotel");
            supdate.executeUpdate("UPDATE `vuelos_SW`.`hotelesBD` SET `numHabitacionesLibres` = '"
                    +habitacioneslibreRandom.nextInt(numhabitaciones-5)+"' WHERE `hotelesBD`.`idhotel` ="+id);
            
            supdate.executeUpdate("UPDATE `vuelos_SW`.`hotelesBD` SET `fecha` = '"+fecha+"'");
        }
        }catch(ClassNotFoundException e ){
            System.out.println("ClassNotFoundException");
        }catch(SQLException e){
        System.out.println("SQLException");
        }
    }
    private boolean buscarHotel(String ubicacion, String fecha){
        boolean consulta = false;
        cambiardatosBD( fecha);
        java.sql.Connection conexion;
        JSONArray lstJsonBase = new JSONArray();
        String baseDatos = "vuelos_SW";
        String usuario = "vuelosusuario";
        String password = "vuelosp";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/"+baseDatos,usuario,password);
            Statement s = conexion.createStatement();
            
            ResultSet rs = s.executeQuery("SELECT * FROM `hotelesBD` WHERE `ubicacion` LIKE '"+ubicacion+"' LIMIT 0,10");
            //omitire la busqueda por fecha ya que siempre coinsidira por la cambiardatosBD(String fecha)
            while(rs.next()){
                consulta = true;
                JSONObject hotel = new JSONObject();
                int idhotel = rs.getInt("idhotel");
                String ubicacionBD = rs.getString("ubicacion");
                String nombre = rs.getString("nombre");
                int numHabitaciones = rs.getInt("numeroDeHabitaciones");
                int habitacionesLibres = rs.getInt("numHabitacionesLibres");
                double costoXdia = rs.getDouble("costoXdia");
                String fechaBD = rs.getString("fecha");
                int estrellas = rs.getInt("estrellas");
                
                hotel.put("idhotel", Integer.toString(idhotel));
                hotel.put("ubicacion",ubicacionBD);
                hotel.put("nombre",nombre);
                hotel.put("numHabitaciones",Integer.toString(numHabitaciones));
                hotel.put("habitacionesLibres",Integer.toString(habitacionesLibres));
                hotel.put("costoXdia",Double.toString(costoXdia));
                hotel.put("fecha",fechaBD);
                hotel.put("estrellas",Integer.toString(estrellas));
                
                lstJsonBase.add(hotel);
            }
            JSONbaseHotel.put("bandera","hoteles");
            JSONbaseHotel.put("lstHoteles",lstJsonBase);
        }catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException en buscarHotel()");
        }catch(SQLException e){
            System.out.println("SQLException en buscarHotel()");
        }
        return consulta;
    }
    private void hotelNoEncontrado() {
        JSONbaseHotel.clear();
        JSONbaseHotel.put("bandera", "hotelNoEncontrado");
    }
    public JSONObject buscarHoteles(String ubicacion,String fecha){
        Hoteles hoteles = new Hoteles();
        JSONbaseHotel.clear();
        if(true != hoteles.buscarHotel(ubicacion, fecha)){
            hotelNoEncontrado();
        }
        return JSONbaseHotel;
    }
    public static void main(String[] args) throws ParseException{
        Hoteles a = new Hoteles();
        
        System.out.print("suma = "+ a.buscarHoteles("Aleania","fecha"));
        
    }
}
