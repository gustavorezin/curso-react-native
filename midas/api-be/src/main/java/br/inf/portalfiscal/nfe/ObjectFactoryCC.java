//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.08 at 09:52:05 AM BRT 
//


package br.inf.portalfiscal.nfe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.inf.portalfiscal.nfe package. 
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
public class ObjectFactoryCC {

	private final static QName _NFE_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "envEvento");
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.inf.portalfiscal.nfe
     * 
     */
    public ObjectFactoryCC() {
    }

    /**
     * Create an instance of {@link TretEvento }
     * 
     */
    public TretEventoCC createTretEvento() {
        return new TretEventoCC();
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
     * Create an instance of {@link TEnvEvento }
     * 
     */
    public TEnvEvento createTEnvEvento() {
        return new TEnvEvento();
    }

    /**
     * Create an instance of {@link TRetEnvEvento }
     * 
     */
    public TRetEnvEvento createTRetEnvEvento() {
        return new TRetEnvEvento();
    }

    /**
     * Create an instance of {@link TProcEvento }
     * 
     */
    public TProcEvento createTProcEvento() {
        return new TProcEvento();
    }

    /**
     * Create an instance of {@link TretEvento.InfEvento }
     * 
     */
    public TretEventoCC.InfEvento createTretEventoInfEvento() {
        return new TretEventoCC.InfEvento();
    }

    /**
     * Create an instance of {@link TEvento.InfEvento.DetEvento }
     * 
     */
    public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
        return new TEvento.InfEvento.DetEvento();
    }

    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "envEvento")
	public JAXBElement<TEnvEventoCC> createEnvEvento(TEnvEventoCC value) {
		return new JAXBElement<TEnvEventoCC>(_NFE_QNAME, TEnvEventoCC.class, null, value);
	}

}
