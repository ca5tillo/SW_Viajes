
package com.viajes;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SW_Viajes", targetNamespace = "http://viajes.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SWViajes {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultarHoteles", targetNamespace = "http://viajes.com/", className = "com.viajes.ConsultarHoteles")
    @ResponseWrapper(localName = "consultarHotelesResponse", targetNamespace = "http://viajes.com/", className = "com.viajes.ConsultarHotelesResponse")
    @Action(input = "http://viajes.com/SW_Viajes/consultarHotelesRequest", output = "http://viajes.com/SW_Viajes/consultarHotelesResponse")
    public String consultarHoteles(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultarVuelos", targetNamespace = "http://viajes.com/", className = "com.viajes.ConsultarVuelos")
    @ResponseWrapper(localName = "consultarVuelosResponse", targetNamespace = "http://viajes.com/", className = "com.viajes.ConsultarVuelosResponse")
    @Action(input = "http://viajes.com/SW_Viajes/consultarVuelosRequest", output = "http://viajes.com/SW_Viajes/consultarVuelosResponse")
    public String consultarVuelos(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

}
