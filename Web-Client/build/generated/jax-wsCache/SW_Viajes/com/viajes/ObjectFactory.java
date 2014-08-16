
package com.viajes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.viajes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsultarHoteles_QNAME = new QName("http://viajes.com/", "consultarHoteles");
    private final static QName _ConsultarVuelosResponse_QNAME = new QName("http://viajes.com/", "consultarVuelosResponse");
    private final static QName _ConsultarHotelesResponse_QNAME = new QName("http://viajes.com/", "consultarHotelesResponse");
    private final static QName _ConsultarVuelos_QNAME = new QName("http://viajes.com/", "consultarVuelos");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.viajes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarVuelosResponse }
     * 
     */
    public ConsultarVuelosResponse createConsultarVuelosResponse() {
        return new ConsultarVuelosResponse();
    }

    /**
     * Create an instance of {@link ConsultarHotelesResponse }
     * 
     */
    public ConsultarHotelesResponse createConsultarHotelesResponse() {
        return new ConsultarHotelesResponse();
    }

    /**
     * Create an instance of {@link ConsultarHoteles }
     * 
     */
    public ConsultarHoteles createConsultarHoteles() {
        return new ConsultarHoteles();
    }

    /**
     * Create an instance of {@link ConsultarVuelos }
     * 
     */
    public ConsultarVuelos createConsultarVuelos() {
        return new ConsultarVuelos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarHoteles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://viajes.com/", name = "consultarHoteles")
    public JAXBElement<ConsultarHoteles> createConsultarHoteles(ConsultarHoteles value) {
        return new JAXBElement<ConsultarHoteles>(_ConsultarHoteles_QNAME, ConsultarHoteles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarVuelosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://viajes.com/", name = "consultarVuelosResponse")
    public JAXBElement<ConsultarVuelosResponse> createConsultarVuelosResponse(ConsultarVuelosResponse value) {
        return new JAXBElement<ConsultarVuelosResponse>(_ConsultarVuelosResponse_QNAME, ConsultarVuelosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarHotelesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://viajes.com/", name = "consultarHotelesResponse")
    public JAXBElement<ConsultarHotelesResponse> createConsultarHotelesResponse(ConsultarHotelesResponse value) {
        return new JAXBElement<ConsultarHotelesResponse>(_ConsultarHotelesResponse_QNAME, ConsultarHotelesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarVuelos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://viajes.com/", name = "consultarVuelos")
    public JAXBElement<ConsultarVuelos> createConsultarVuelos(ConsultarVuelos value) {
        return new JAXBElement<ConsultarVuelos>(_ConsultarVuelos_QNAME, ConsultarVuelos.class, null, value);
    }

}
