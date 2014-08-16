/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viajes;

import com.viajes.hoteles.Hoteles;
import com.viajes.vuelos.vuelos;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author lap
 */
@WebService(serviceName = "SW_Viajes")
public class SW_Viajes {
    /**
     * 
     * @param origen
     * @param destino
     * @param fecha1
     * @param fecha2
     * @return 
     */
    public String consultarVuelos(String origen, String destino, String fecha1,String fecha2){
        String origens=origen;String destinos=destino;String fechaS=fecha1; String fechaLl = fecha2;
        
        vuelos a =new vuelos();
        String datos = "";
        //"2014-06-25" 
            datos = ""+a.buscarVuelos(origens,destinos,fechaS,fechaLl);
        return datos;
    }
    /**
     * 
     * @param ubicacion
     * @param fecha
     * @return 
     */
    public String consultarHoteles(String ubicacion,String fecha){
        String datos ="";
        Hoteles hoteles = new Hoteles();
        datos = ""+hoteles.buscarHoteles(ubicacion, fecha);
        return  datos;
    }
}
