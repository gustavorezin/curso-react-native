//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.17 at 05:15:09 PM BRT 
//


package br.inf.portalfiscal.mdfe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.mdfe package. 
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
public class ObjectFactory_sit {

    private final static QName _ConsSitMDFe_QNAME = new QName("http://www.portalfiscal.inf.br/mdfe", "consSitMDFe");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.mdfe
     * 
     */
    public ObjectFactory_sit() {
    }

    /**
     * Create an instance of {@link TRetEvento }
     * 
     */
    public TRetEvento createTRetEvento() {
        return new TRetEvento();
    }

    /**
     * Create an instance of {@link TProtMDFe }
     * 
     */
    public TProtMDFe createTProtMDFe() {
        return new TProtMDFe();
    }

    /**
     * Create an instance of {@link TEvento }
     * 
     */
    public TEvento createTEvento() {
        return new TEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento }
     * 
     */
    public TEvento.InfEvento createTEventoInfEvento() {
        return new TEvento.InfEvento();
    }

    /**
     * Create an instance of {@link TConsSitMDFe }
     * 
     */
    public TConsSitMDFe createTConsSitMDFe() {
        return new TConsSitMDFe();
    }

    /**
     * Create an instance of {@link TConsReciMDFe }
     * 
     */
    public TConsReciMDFe createTConsReciMDFe() {
        return new TConsReciMDFe();
    }

    /**
     * Create an instance of {@link TRetConsSitMDFe }
     * 
     */
    public TRetConsSitMDFe createTRetConsSitMDFe() {
        return new TRetConsSitMDFe();
    }

    /**
     * Create an instance of {@link TRetConsReciMDFe }
     * 
     */
    public TRetConsReciMDFe createTRetConsReciMDFe() {
        return new TRetConsReciMDFe();
    }

    /**
     * Create an instance of {@link TProcEvento_old }
     * 
     */
    public TProcEvento_old createTProcEvento() {
        return new TProcEvento_old();
    }

    /**
     * Create an instance of {@link TRetEvento.InfEvento }
     * 
     */
    public TRetEvento.InfEvento createTRetEventoInfEvento() {
        return new TRetEvento.InfEvento();
    }

    /**
     * Create an instance of {@link TProtMDFe.InfProt }
     * 
     */
    public TProtMDFe.InfProt createTProtMDFeInfProt() {
        return new TProtMDFe.InfProt();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento }
     * 
     */
    public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
        return new TEvento.InfEvento.DetEvento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TConsSitMDFe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/mdfe", name = "consSitMDFe")
    public static JAXBElement<TConsSitMDFe> createConsSitMDFe(TConsSitMDFe value) {
        return new JAXBElement<TConsSitMDFe>(_ConsSitMDFe_QNAME, TConsSitMDFe.class, null, value);
    }

}
